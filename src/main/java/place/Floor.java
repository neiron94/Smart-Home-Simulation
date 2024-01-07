package place;

import event.Event;

import java.util.ArrayList;
import java.util.List;

public class Floor implements EventDestination, Comparable<Floor> {
    private final int id;
    private final List<Room> rooms;
    private final List<Event> events;

    public Floor(int id) {
        this.id = id;
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

    public void addEvent(Event event) {
        events.add(event);
    }

    @Override
    public String toString() {
        return String.format("Floor_%d", id);
    }

    @Override
    public void deleteEvent(Event event) {
        events.remove(event); // TODO For use by event solver
    }

    @Override
    public int compareTo(Floor floor) {
        return Integer.compare(this.id, floor.id);
    }
}
