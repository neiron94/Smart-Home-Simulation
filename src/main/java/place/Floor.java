package place;

import event.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Place in home topology which contains rooms.
 */
public class Floor implements EventDestination {
    private final int id;
    private final List<Room> rooms;
    private final List<Event> events;

    /**
     * Creates new floor.
     * @param id id of the floor, should be unique
     */
    public Floor(int id) {
        this.id = id;
        rooms = new ArrayList<>();
        events = new ArrayList<>();
    }

    /**
     * Deletes event from this floor.
     * @param event event to delete
     * @return true if floor contained the specified event
     */
    @Override
    public boolean deleteEvent(Event event) {
        return events.remove(event);
    }

    /**
     * Adds event to this floor.
     * @param event event to add
     */
    @Override
    public void addEvent(Event event) {
        events.add(event);
    }

    /**
     * Adds room to this floor.
     * @param room room to add
     */
    public void addRoom(Room room) {
        rooms.add(room);
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public int getId() {
        return id;
    }

    public List<Event> getEvents() {
        return events;
    }

    @Override
    public String toString() {
        return String.format("Floor_%d", id);
    }
}
