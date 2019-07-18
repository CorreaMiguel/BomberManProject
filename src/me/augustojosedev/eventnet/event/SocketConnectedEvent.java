package me.augustojosedev.eventnet.event;


import java.io.Serializable;

public class SocketConnectedEvent extends DefaultEvent {

    protected final Serializable source;

    public SocketConnectedEvent(Serializable source) {
        this.source = source;
    }

    public Serializable getSource() {
        return source;
    }

}
