/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package consumer.device.common.entertainment;

import java.time.Duration;

/**
 * Song for stereo system.
 * @param author author of the song
 * @param album album of the song
 * @param name name of the song
 * @param genre genre of the song
 * @param duration duration of the song
 */
public record Song(String author, String album, String name, SongGenre genre, Duration duration) {
    @Override
    public String toString() {
        return name;
    }
}
