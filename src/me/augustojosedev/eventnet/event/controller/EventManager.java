package me.augustojosedev.eventnet.event.controller;

import me.augustojosedev.eventnet.net.EventSocket;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import me.augustojosedev.eventnet.event.Event;

public class EventManager<ClientSource extends Serializable> {

    private final List<EventListener> eventListeners = new ArrayList<>();
    private boolean throwing = false;

    public void throwEvent(Event event, EventSocket<ClientSource> socket) {
        throwing = true;
        int size = this.eventListeners.size();
        EventListener[] eventListeners = new EventListener[size];
        for (int i = 0; i < size; i++) {
            eventListeners[i] = this.eventListeners.get(i);
        }
        throwing = false;
        for (EventListener eventListener : eventListeners) {
            if (eventListener != null) {
                for (Method method : eventListener.getClass().getMethods()) {
                    if (method.isAnnotationPresent(EventHandler.class)) {
                        try {
                            if (method.getParameterCount() == 1 && method.getParameterTypes()[0].isInstance(event)) {
                                method.invoke(eventListener, new Object[]{event});
                            } else if (method.getParameterCount() == 2) {
                                if (method.getParameterTypes()[0].isInstance(event) && method.getParameterTypes()[1].isInstance(socket)) {
                                    method.invoke(eventListener, new Object[]{event, socket});
                                } else if (method.getParameterTypes()[1].isInstance(event) && method.getParameterTypes()[0].isInstance(socket)) {
                                    method.invoke(eventListener, new Object[]{socket, event});
                                }
                            } else {
                            }
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                    }
                }
            }
        }
    }

    public boolean addEventListener(EventListener e) {
        while (throwing) {
            //waiting...
        }
        return eventListeners.add(e);
    }

    public boolean removeEventListener(EventListener e) {
        while (throwing) {
            //waiting...
        }
        return eventListeners.remove(e);
    }

    public void clearEventListeners() {
        while (throwing) {
            //waiting...
        }
        eventListeners.clear();
    }

}