package consumer.device.common.entertainment;

import java.time.LocalTime;
import java.util.NoSuchElementException;

public class EntertainmentFactory {
    public Song createSong(String author, String album, String name, String songGenre, int duration) {
        SongGenre genre;

        try {
            genre = SongGenre.valueOf(songGenre);
        } catch (IllegalArgumentException e) {
            throw new NoSuchElementException("Invalid Gender");
        }

        return new Song(author, album, name, genre, LocalTime.of(0, duration));
    }

    public Video createVideo(String name, String description, String videoPlatform, int duration) {
        VideoPlatform platform;

        try {
            platform = VideoPlatform.valueOf(videoPlatform);
        } catch (IllegalArgumentException e) {
            throw new NoSuchElementException("Invalid Gender");
        }

        return new Video(name, description, platform, LocalTime.of(0, duration));
    }

    public Game createGame(String name, String description, String gameGenre) {
        GameGenre genre;

        try {
            genre = GameGenre.valueOf(gameGenre);
        } catch (IllegalArgumentException e) {
            throw new NoSuchElementException("Invalid Gender");
        }

        return new Game(name, description, genre);
    }
}
