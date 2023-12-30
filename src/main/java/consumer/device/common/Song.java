package consumer.device.common;

import java.time.LocalTime;

public record Song(String author, String album, String name, SongGenre genre, LocalTime duration) {}
