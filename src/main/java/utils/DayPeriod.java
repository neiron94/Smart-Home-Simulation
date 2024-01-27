/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package utils;

import java.time.LocalTime;

/**
 * Represents day periods.
 */
public enum DayPeriod {
    MORNING("Morning", LocalTime.of(6, 0), LocalTime.of(11, 59)),
    DAY("Day", LocalTime.of(12, 0), LocalTime.of(17, 59)),
    EVENING("Evening", LocalTime.of(18, 0), LocalTime.of(23, 59)),
    NIGHT("Night", LocalTime.of(0, 0), LocalTime.of(5, 59));

    private final String description;
    private final LocalTime start;
    private final LocalTime end;

    DayPeriod(String description, LocalTime start, LocalTime end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return description;
    }
}
