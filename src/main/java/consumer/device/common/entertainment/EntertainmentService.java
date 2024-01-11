package consumer.device.common.entertainment;

import utils.ConfigurationReader;
import utils.HelpFunctions;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * Service which contains simulation of database of entertainment objects (video/game/song).
 * Also contains some useful functions for entertainment objects.
 */
public class EntertainmentService {

    // Read entertainment objects from json files
    static {
        ConfigurationReader.readContentConfig();

        for (int i = 0; i < new Random().nextInt(3) + 3; ++i) { // Randomly generate playlists
            List<Song> playlist = new ArrayList<>();
            for (int j = 0; j < new Random().nextInt(3) + 5; ++j) playlist.add(AudioService.getRandomSong());
            AudioService.addPlaylist(playlist);
        }
    }

    /**
     * Service which contains simulation of database of games.
     * Also contains some useful functions for game objects.
     */
    public static class GameService {
        private final static List<Game> games = new ArrayList<>(); // Simulation of database with games

        /**
         * Add game to "database".
         * @param game Game to add.
         */
        public static void addGame(Game game) {
            games.add(game);
        }

        /**
         * Get random game from "database".
         * @return Random game.
         */
        public static Game getRandomGame() {
            return HelpFunctions.getRandomObject(games).orElseThrow(NoSuchElementException::new);
        }
    }

    /**
     * Service which contains simulation of database of songs.
     * Also contains some useful functions for song objects.
     */
    public static class AudioService {
        private final static List<Song> songs = new ArrayList<>(); // Simulation of database with songs
        private final static List<List<Song>> playlists = new ArrayList<>(); // Simulation of database with playlists

        /**
         * Add song to "database".
         * @param song Song to add.
         */
        public static void addSong(Song song) {
            songs.add(song);
        }

        /**
         * Get random song from "database".
         * @return Random song.
         */
        public static Song getRandomSong() {
            return HelpFunctions.getRandomObject(songs).orElseThrow(NoSuchElementException::new);
        }

        /**
         * Add playlist to "database".
         * @param playlist Playlist to add.
         */
        public static void addPlaylist(List<Song> playlist) {
            playlists.add(playlist);
        }

        /**
         * Get random playlist from "database".
         * @return Random playlist.
         */
        public static List<Song> getRandomPlaylist() {
            return HelpFunctions.getRandomObject(playlists).orElseThrow(NoSuchElementException::new);
        }
    }

    /**
     * Service which contains simulation of database of videos.
     * Also contains some useful functions for video objects.
     */
    public static class VideoService {
        private final static List<Video> shows = new ArrayList<>(); // Simulation of database with videos

        /**
         * Add video to "database".
         * @param video Video to add.
         */
        public static void addVideo(Video video) {
            shows.add(video);
        }

        /**
         * Get random video from "database".
         * @return Random video.
         */
        public static Video getRandomVideo() {
            return HelpFunctions.getRandomObject(shows).orElseThrow(NoSuchElementException::new);
        }
    }
}
