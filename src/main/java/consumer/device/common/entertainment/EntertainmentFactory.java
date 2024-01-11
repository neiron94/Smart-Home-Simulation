package consumer.device.common.entertainment;

import java.time.Duration;
import java.time.LocalTime;
import java.util.NoSuchElementException;

/**
 * Factory for creating entertainment objects (song/game/video).
 * Is used when entertainment objects are read from configuration file.
 */
public class EntertainmentFactory {

    /**
     * Create song. Used in configuration reading.
     * @param author Author of the song.
     * @param album Album of the song.
     * @param name Name of the song.
     * @param songGenre Genre of the song.
     * @param duration Duration of the song.
     * @return New song.
     */
    public Song createSong(String author, String album, String name, String songGenre, int duration) {
        SongGenre genre;

        try {
            genre = SongGenre.valueOf(songGenre);
        } catch (IllegalArgumentException e) {
            throw new NoSuchElementException("Invalid Genre");
        }

        return new Song(author, album, name, genre, Duration.ofMinutes(duration));
    }

    /**
     * Create video. Used in configuration reading.
     * @param name Name of the video.
     * @param description Description of the video.
     * @param videoPlatform Platform of the video.
     * @param duration Duration of the video.
     * @return New video.
     */
    public Video createVideo(String name, String description, String videoPlatform, int duration) {
        VideoPlatform platform;

        try {
            platform = VideoPlatform.valueOf(videoPlatform);
        } catch (IllegalArgumentException e) {
            throw new NoSuchElementException("Invalid Genre");
        }

        return new Video(name, description, platform, Duration.ofMinutes(duration));
    }

    /**
     * Create game. Used in configuration reading.
     * @param name Name of the game.
     * @param description Description of the game.
     * @param gameGenre Genre of the game.
     * @return New game.
     */
    public Game createGame(String name, String description, String gameGenre) {
        GameGenre genre;

        try {
            genre = GameGenre.valueOf(gameGenre);
        } catch (IllegalArgumentException e) {
            throw new NoSuchElementException("Invalid Genre");
        }

        return new Game(name, description, genre);
    }
}
