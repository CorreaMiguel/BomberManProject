package me.augustojosedev.eventnet.net;

import java.io.Closeable;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import me.augustojosedev.eventnet.event.ConnectionInviteEvent;
import me.augustojosedev.eventnet.event.DisconnectedEvent;
import me.augustojosedev.eventnet.event.Event;
import me.augustojosedev.eventnet.event.controller.EventHandler;
import me.augustojosedev.eventnet.event.controller.EventListener;
import me.augustojosedev.eventnet.event.controller.EventManager;
import me.augustojosedev.eventnet.event.GetConnectionEvent;
import me.augustojosedev.eventnet.event.GetDisconnectionEvent;

public class EventClient<ClientSource extends Serializable> implements EventListener, Closeable {

    private final EventManager<ClientSource> eventManager;
    private final EventSocket<ClientSource> socket;
    private final ClientSource source;
    private boolean online = true;

    public EventClient(String adress, int port, ClientSource source) throws IOException {
        this.eventManager = new EventManager<>();
        this.source = source;
        this.eventManager.addEventListener(this);
        this.socket = new EventSocket<>(new Socket(adress, port), eventManager);
        this.socket.setSource(source);
    }

    public EventSocket<ClientSource> getSocket() {
        return socket;
    }

    public ClientSource getSource() {
        return source;
    }

    public boolean addEventListener(EventListener e) {
        return eventManager.addEventListener(e);
    }

    public boolean removeEventListener(EventListener e) {
        return eventManager.removeEventListener(e);
    }

    public void sendEvent(Event e) throws IOException {
        socket.sendEvent(e);
    }

    public boolean isOnline() {
        return online && socket.isOnline();
    }

    @Override
    public void close() {
        online = false;
        try {
            socket.sendEvent(new GetDisconnectionEvent());
        } catch (IOException ex) {
        }
        socket.close();
        eventManager.clearEventListeners();
    }

    public EventManager<ClientSource> getEventManager() {
        return eventManager;
    }

    @EventHandler
    public void onConnectionInviteEvent(ConnectionInviteEvent event) {
        if (socket != null && socket.isOnline()) {
            try {
                socket.sendEvent(new GetConnectionEvent(source));
            } catch (IOException e) {
                close();
            }
        }
    }

    @EventHandler
    public void onDisconnectedEvent(DisconnectedEvent event) {
        if (socket != null && socket.isOnline()) {
            socket.close();
            online = false;
        }
    }

}
