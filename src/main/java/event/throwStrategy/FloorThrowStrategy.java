/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package event.throwStrategy;

import event.Event;

/**
 * Throws event to a floor, where creator of event is located.
 */
public class FloorThrowStrategy implements EventThrowStrategy {
    @Override
    public void throwEvent(Event event) {
        event.getOrigin().getFloor().addEvent(event);
    }
}
