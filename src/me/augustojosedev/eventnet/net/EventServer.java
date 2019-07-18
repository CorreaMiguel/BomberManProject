package me.augustojosedev.eventnet.net;

import java.io.Closeable;
import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import me.augustojosedev.eventnet.event.controller.EventManager;
import me.augustojosedev.eventnet.event.DisconnectedEvent;
import me.augustojosedev.eventnet.event.ConnectedEvent;
import me.augustojosedev.eventnet.event.ConnectionInviteEvent;
import me.augustojosedev.eventnet.event.Event;
import me.augustojosedev.eventnet.event.controller.EventHandler;
import me.augustojosedev.eventnet.event.controller.EventListener;
import me.augustojosedev.eventnet.event.GetConnectionEvent;
import me.augustojosedev.eventnet.event.GetDisconnectionEvent;
import me.augustojosedev.eventnet.event.RedirectableEvent;
import me.augustojosedev.eventnet.event.SocketConnectedEvent;
import me.augustojosedev.eventnet.event.SocketDisconnectedEvent;

public class EventServer<ClientSource extends Serializable> extends Thread implements EventListener, Closeable {

    private final List<EventSocket<ClientSource>> sockets = new ArrayList<>();
    private final ServerSocket serverSocket;
    private final EventManager<ClientSource> eventManager;
    private boolean online = true;

    public EventServer(int port) throws IOException {
        this.eventManager = new EventManager<>();
        this.serverSocket = new ServerSocket(port);
        this.eventManager.addEventListener(this);
        start();
    }

    public List<EventSocket<ClientSource>> getSockets() {
        return sockets;
    }

    @Override
    public void run() {
        while (online = online && !serverSocket.isClosed()) {
            Socket socket = null;
            EventSocket<ClientSource> client = null;
            try {
                socket = serverSocket.accept();
                client = new EventSocket<>(socket, eventManager);
                client.sendEvent(new ConnectionInviteEvent());
            } catch (IOException ex) {
            }
        }
    }

    public void sendEvent(Event event) throws IOException {
        for (EventSocket<ClientSource> socket : sockets) {
            if (socket != null && socket.isOnline()) {
                socket.sendEvent(event);
            }
        }
    }

    public void sendEvent(Event event, ClientSource source) throws IOException {
        for (EventSocket<ClientSource> socket : sockets) {
            if (socket != null && socket.isOnline() && socket.getSource().equals(source)) {
                socket.sendEvent(event);
            }
        }
    }

    public EventManager<ClientSource> getEventManager() {
        return eventManager;
    }

    public boolean addEventListener(EventListener e) {
        return eventManager.addEventListener(e);
    }

    public boolean removeEventListener(EventListener e) {
        return eventManager.removeEventListener(e);
    }

    public boolean isOnline() {
        return online;
    }

    @EventHandler
    public void onGetConnectionEvent(GetConnectionEvent event, EventSocket<ClientSource> socket) {
        if (socket != null && socket.isOnline()) {
            try {
                if (event.getSource() != null) {
                    for (int i = 0; i < sockets.size(); i++) {
                        if (sockets.get(i).getSource().equals(event.getSource())) {
                            socket.sendEvent(new DeniedConnectionEvent("Cliente identico já conectado"));
                            socket.close();
                            return;
                        }
                    }
                    socket.setSource((ClientSource) event.getSource());
                    socket.sendEvent(new ConnectedEvent());
                    sockets.add(socket);
                    sendEvent(new SocketConnectedEvent(socket.getSource()));
                    eventManager.throwEvent(new SocketConnectedEvent(socket.getSource()), socket);
                }
            } catch (IOException ex) {
            }
        }
    }

    @EventHandler
    public void onGetDisconnectionEvent(GetDisconnectionEvent event, EventSocket<ClientSource> socket) {
        if (socket != null && socket.isOnline()) {
            socket.close();
        }
    }

    @EventHandler
    public void onRedirectableEvent(RedirectableEvent event, EventSocket<ClientSource> socket) {
        try {
            event.setSender(socket.getSource());
            sendEvent(event);
        } catch (IOException ex) {
        }
    }

    @EventHandler
    public void onDisconnectedEvent(DisconnectedEvent event, EventSocket<ClientSource> socket) {
        try {
            sockets.remove(socket);
            sendEvent(new SocketDisconnectedEvent(socket.getSource()));
            eventManager.throwEvent(new SocketDisconnectedEvent(socket.getSource()), socket);
        } catch (IOException ex) {
        }
    }

    @Override
    public void close() {
        online = false;
        interrupt();
        EventSocket<ClientSource>[] sockets = new EventSocket[this.sockets.size()];
        for (int i = 0; i < this.sockets.size(); i++) {
            sockets[i] = this.sockets.get(i);
        }
        for (EventSocket<ClientSource> socket : sockets) {
            socket.close();
        }
        try {
            serverSocket.close();
        } catch (IOException ex) {
        }
        eventManager.clearEventListeners();
        this.sockets.clear();
    }

}
