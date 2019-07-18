package me.augustojosedev.eventnet.event;

import java.io.Serializable;

public abstract class RedirectableEvent extends Event {

    protected Serializable sender=null;

    public void setSender(Serializable sender) {
        this.sender = sender;
    }

    public Serializable getSender() {
        return sender;
    }

}
