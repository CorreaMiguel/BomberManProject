package me.augustojosedev.eventnet.event;


import java.io.Serializable;

public class GetConnectionEvent extends DefaultEvent {

    protected final Serializable source;

    public GetConnectionEvent(Serializable source) {
        this.source = source;
    }

    public Serializable getSource() {
        return source;
    }
}
