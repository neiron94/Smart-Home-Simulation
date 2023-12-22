package place;

import java.util.ArrayList;
import java.util.List;

public class Floor implements EventDestination {
    private static int id = 1;

    private final int floorId;
    private final List<Room> rooms;
    private List<Event> events; // TODO Control after creating Event class

    public Floor() {
        floorId = id++;
        rooms = new ArrayList<>();
        events = new ArrayList<>();
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public List<Event> getEvents() {
        return events;
    }

    public void addEvent(Event event) { // TODO Control after creating Event class
        events.add(event);
    }

    @Override
    public String toString() {
        return "Floor_" + floorId;
    }

    @Override
    public void deleteEvent(Event event) { // TODO Control after creating Event class
        events.remove(event); // TODO For use by event solver
    }
}
