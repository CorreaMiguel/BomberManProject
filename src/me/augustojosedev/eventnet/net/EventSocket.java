package me.augustojosedev.eventnet.net;

import java.io.Closeable;
import me.augustojosedev.eventnet.event.Event;
import me.augustojosedev.eventnet.event.controller.EventManager;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import me.augustojosedev.eventnet.event.DisconnectedEvent;

public class EventSocket<ClientSource extends Serializable> extends Thread implements Closeable {

    private ClientSource source;
    private final Socket socket;
    private final EventManager<ClientSource> eventManager;
    private final ObjectOutputStream out;
    private final ObjectInputStream in;
    private boolean online = true;

    public EventSocket(Socket socket, EventManager<ClientSource> eventManager) throws IOException {
        this.socket = socket;
        this.eventManager = eventManager;
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());
        start();
    }


    @Override
    public void run() {
        while (online) {
            Object object;
            try {
                object = in.readObject();
                if (object instanceof Event) {
                    Event event = (Event) object;
                    eventManager.throwEvent(event, this);
                }
            } catch (IOException | ClassNotFoundException ex) {
                close();
            }
        }
    }

    public void sendEvent(Event e) throws IOException {
        if (online && e != null) {
            out.writeObject(e);
        }
    }

    public boolean isOnline() {
        return online;
    }

    @Override
    public void close() {
        if (online) {
            online = false;
            eventManager.throwEvent(new DisconnectedEvent("A conexão foi encerrada."), this);
            interrupt();
            try {
                in.close();
            } catch (IOException ex) {
            }
            try {
                out.close();
            } catch (IOException ex) {
            }
            try {
                socket.close();
            } catch (IOException ex) {
            }
        }
    }

    public ClientSource getSource() {
        return source;
    }

    public void setSource(ClientSource source) {
        this.source = source;
    }

}
