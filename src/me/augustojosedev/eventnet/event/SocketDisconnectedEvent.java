package me.augustojosedev.eventnet.event;


import java.io.Serializable;

public class SocketDisconnectedEvent extends DefaultEvent {

    protected final Serializable source;

    public SocketDisconnectedEvent(Serializable source) {
        this.source = source;
    }

    public Serializable getSource() {
        return source;
    }
}
