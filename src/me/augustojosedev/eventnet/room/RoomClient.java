package me.augustojosedev.eventnet.room;

import java.io.Serializable;

public class RoomClient<ClientSource extends Serializable> implements Serializable {

    private final ClientSource clientSource;
    private final short room;

    public RoomClient(ClientSource clientSource, short room) {
        this.clientSource = clientSource;
        this.room = room;
    }

    public ClientSource getClientSource() {
        return clientSource;
    }

    public short getRoom() {
        return room;
    }
}
