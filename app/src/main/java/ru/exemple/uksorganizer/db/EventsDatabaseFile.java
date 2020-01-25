package ru.exemple.uksorganizer.db;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import ru.exemple.uksorganizer.model.Event;
import ru.exemple.uksorganizer.ui.EventActivity;

//TODO Сделать реализацию через Serialisable

// HUI
public class EventsDatabaseFile implements EventsDatabase{

    EventActivity eventActivity;

    @Override
    public List<Event> getAllEvents() {
        return null;
    }

    @Override
    public void addEvent(Event event) {

        event = eventActivity.getEvent();
        try {

            FileOutputStream fileOutputStream = new FileOutputStream("event_1");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(event);
            objectOutputStream.close();

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }



    }

    @Override
    public void update(Event event) {

    }
}
