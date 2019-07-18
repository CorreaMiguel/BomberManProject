package me.augustojosedev.eventnet.event;

public class DisconnectedEvent extends DefaultEvent {

    protected final String reason;

    public DisconnectedEvent(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

}
