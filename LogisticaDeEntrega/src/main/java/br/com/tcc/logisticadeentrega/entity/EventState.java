package br.com.tcc.logisticadeentrega.entity;

import java.util.ArrayList;
import java.util.List;

public class EventState {

    private List<Event> events;

    public List<Event> getEvents() {
        return events;
    }

    public void setEvent(List<Event> events) {
        this.events = events;
    }

    public void add(Event event) {
        if (null == events) {
            this.events = new ArrayList<>();
        }
        events.add(event);

    }
}
