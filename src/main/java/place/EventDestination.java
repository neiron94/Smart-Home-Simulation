package place;

import event.Event;

public interface EventDestination {
    boolean deleteEvent(Event event);
    void addEvent(Event event);
}
