package place;

import event.Event;

public interface EventDestination {
    void deleteEvent(Event event);
    void addEvent(Event event);
}
