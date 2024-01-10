package consumer.device.common.entertainment;

import java.time.Duration;

/**
 * Song for stereo system.
 * @param author Author of the song.
 * @param album Album of the song.
 * @param name Name of the song.
 * @param genre Genre of the song.
 * @param duration Duration of the song.
 */
public record Song(String author, String album, String name, SongGenre genre, Duration duration) {}
