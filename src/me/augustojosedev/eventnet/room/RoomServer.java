package me.augustojosedev.eventnet.room;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import me.augustojosedev.eventnet.event.Event;
import me.augustojosedev.eventnet.event.controller.EventHandler;
import me.augustojosedev.eventnet.event.controller.EventListener;
import me.augustojosedev.eventnet.event.controller.EventManager;
import me.augustojosedev.eventnet.net.EventServer;
import me.augustojosedev.eventnet.net.EventSocket;

public class RoomServer<ClientSource extends Serializable> {

    private final EventServer<RoomClient<ClientSource>> server;

    public RoomServer(int port) throws IOException {
        server = new EventServer<>(port);
    }

    @EventHandler
    public void onRoomEvent(RoomEvent event) throws IOException {
        sendEvent(event);
    }

    public List<EventSocket<RoomClient<ClientSource>>> getSockets() {
        return server.getSockets();
    }

    public void sendEvent(RoomEvent event) throws IOException {
        for (int i = 0; i < server.getSockets().size(); i++) {
            EventSocket<RoomClient<ClientSource>> socket = server.getSockets().get(i);
            if (socket.getSource().getRoom() == event.getRoom()) {
                socket.sendEvent(event);
            }
        }
    }

    public void sendEvent(Event event, RoomClient<ClientSource> source) throws IOException {
        server.sendEvent(event, source);
    }

    public EventManager<RoomClient<ClientSource>> getEventManager() {
        return server.getEventManager();
    }

    public boolean addEventListener(EventListener e) {
        return server.addEventListener(e);
    }

    public boolean removeEventListener(EventListener e) {
        return server.removeEventListener(e);
    }

    public boolean isOnline() {
        return server.isOnline();
    }

    public void close() {
        server.close();
    }

}
