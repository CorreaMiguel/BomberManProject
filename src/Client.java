
import java.io.IOException;
import me.augustojosedev.eventnet.event.Event;
import me.augustojosedev.eventnet.event.controller.EventHandler;
import me.augustojosedev.eventnet.event.controller.EventListener;
import me.augustojosedev.eventnet.net.EventClient;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author migue
 */
public class Client implements EventListener {

    private EventClient<Player> eventClient;

    public Client(String IP, int port, Player player) throws IOException {
        this.eventClient = new EventClient<>(IP, port, player);
        eventClient.addEventListener(this);
    }

    public void sendEvent(Event event) {
        try {
            eventClient.sendEvent(event);
        } catch (IOException ex) {
            System.out.println("Deu erro:" + ex.getMessage());
        }
    }

    @EventHandler
    public void onEvent(Event event) {
        System.out.println("Evento :)");
    }

    public void close() {
        eventClient.close();
    }
}
