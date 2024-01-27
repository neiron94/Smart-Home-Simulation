/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package event.throwStrategy;

import event.Event;

/**
 * Strategy for throwing events.
 */
public interface EventThrowStrategy {
    void throwEvent(Event event);
}
