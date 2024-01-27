/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package consumer.device.common.entertainment;

/**
 * Genres for songs.
 */
public enum SongGenre {
    ROCK("Rock"),
    CLASSIC("Classic"),
    HIP_HOP("Hip Hop"),
    JAZZ("Jazz"),
    ELECTRONIC("Electronic"),
    REGGAE("Reggae"),
    POP("Pop");

    private final String name;

    SongGenre(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
