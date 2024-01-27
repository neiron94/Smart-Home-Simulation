/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package place;

import event.Event;

/**
 * Places in which event can be thrown.
 */
public interface EventDestination {

    /**
     * Deletes event from this place.
     * @param event event to delete
     * @return true if place contained the specified event
     */
    boolean deleteEvent(Event event);

    /**
     * Adds event to this place.
     * @param event event to add
     */
    void addEvent(Event event);
}
