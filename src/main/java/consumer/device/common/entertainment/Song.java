package consumer.device.common.entertainment;

import java.time.Duration;

public record Song(String author, String album, String name, SongGenre genre, Duration duration) {}
