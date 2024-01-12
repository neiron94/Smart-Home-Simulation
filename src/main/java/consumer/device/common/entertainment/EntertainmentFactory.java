package consumer.device.common.entertainment;

import utils.HelpFunctions;

import java.time.Duration;
import java.time.LocalTime;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Factory for creating entertainment objects (song/game/video).
 * Is used when entertainment objects are read from configuration file.
 */
public class EntertainmentFactory {

    /**
     * Create song. Used in configuration reading.
     * @param author author of the song
     * @param album album of the song
     * @param name name of the song
     * @param songGenre genre of the song
     * @param duration duration of the song
     * @return new song
     */
    public Optional<Song> createSong(String author, String album, String name, String songGenre, int duration) {
        SongGenre genre;

        try {
            genre = SongGenre.valueOf(songGenre);
            return Optional.of(new Song(author, album, name, genre, Duration.ofMinutes(duration)));
        } catch (IllegalArgumentException e) {
            HelpFunctions.logger.warn(String.format("Invalid song genre '%s'. Song '%s' wasn't created", songGenre, name));
            return Optional.empty();
        }
    }

    /**
     * Create video. Used in configuration reading.
     * @param name name of the video
     * @param description description of the video
     * @param videoPlatform platform of the video
     * @param duration duration of the video
     * @return new video
     */
    public Optional<Video> createVideo(String name, String description, String videoPlatform, int duration) {
        VideoPlatform platform;

        try {
            platform = VideoPlatform.valueOf(videoPlatform);
            return Optional.of(new Video(name, description, platform, Duration.ofMinutes(duration)));
        } catch (IllegalArgumentException e) {
            HelpFunctions.logger.warn(String.format("Invalid video platform '%s'. Video '%s' wasn't created", videoPlatform, name));
            return Optional.empty();
        }
    }

    /**
     * Create game. Used in configuration reading.
     * @param name name of the game
     * @param description description of the game
     * @param gameGenre genre of the game
     * @return new game
     */
    public Optional<Game> createGame(String name, String description, String gameGenre) {
        GameGenre genre;

        try {
            genre = GameGenre.valueOf(gameGenre);
            return Optional.of(new Game(name, description, genre));
        } catch (IllegalArgumentException e) {
            HelpFunctions.logger.warn(String.format("Invalid game genre '%s'. Game '%s' wasn't created", gameGenre, name));
            return Optional.empty();
        }
    }
}
