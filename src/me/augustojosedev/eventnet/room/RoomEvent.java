package me.augustojosedev.eventnet.room;

import me.augustojosedev.eventnet.event.Event;

public class RoomEvent extends Event {

    protected final short room;

    public RoomEvent(short room) {
        this.room = room;
    }

    public short getRoom() {
        return room;
    }


}
