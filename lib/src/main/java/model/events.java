package model;

import java.util.ArrayList;

/**
 * Created by camer on 3/5/2018.
 */

public class events extends Model {

    private ArrayList<event> events = new ArrayList<>();

    public events(){}

    public event[] getEvents() {
        return (event[]) events.toArray();
    }

    public void setEvents(ArrayList<event> events) {
        this.events = events;
    }

    public void addEvent(event event){
        events.add(event);

    }

}
