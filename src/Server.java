
import java.io.IOException;
import me.augustojosedev.eventnet.event.Event;
import me.augustojosedev.eventnet.event.controller.EventHandler;
import me.augustojosedev.eventnet.event.controller.EventListener;
import me.augustojosedev.eventnet.net.EventServer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author migue
 */
public class Server implements EventListener {
    private EventServer<Player> eventServer;

    public Server(int port) throws IOException {
        this.eventServer = new EventServer<>(port);
        eventServer.addEventListener(this);
    }
    public void sendEvent(Event event){
        try {
            eventServer.sendEvent(event);
        } catch (IOException ex) {
            System.out.println("Deu erro:"+ ex.getMessage());
        }
    }
    @EventHandler
    public void onEvent (Event event){
        System.out.println("Evento :)");
    }
    public void close(){
        eventServer.close();
    }
}
