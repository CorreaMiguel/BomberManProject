package me.augustojosedev.eventnet.net;

import me.augustojosedev.eventnet.event.DefaultEvent;

public class DeniedConnectionEvent extends DefaultEvent {

    protected final String reason;

    public DeniedConnectionEvent(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

}
