package consumer.device.common.entertainment;

import utils.HelpFunctions;
import java.util.*;

/**
 * Service which contains simulation of database of entertainment objects (video/game/song).
 * Also contains some useful functions for entertainment objects.
 */
public class EntertainmentService {
    /**
     * Service which contains simulation of database of games.
     * Also contains some useful functions for game objects.
     */
    public static class GameService {
        private final static List<Game> games = new ArrayList<>(); // Simulation of database with games

        /**
         * Add game to "database".
         * @param game game to add
         */
        public static void addGame(Game game) {
            games.add(game);
        }

        /**
         * Get random game from "database".
         * @return optional with random game
         */
        public static Optional<Game> getRandomGame() {
            return HelpFunctions.getRandomObject(games);
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
         * @param song song to add
         */
        public static void addSong(Song song) {
            songs.add(song);
        }

        /**
         * Get random song from "database".
         * @return optional with random song
         */
        public static Optional<Song> getRandomSong() {
            return HelpFunctions.getRandomObject(songs);
        }

        /**
         * Add playlists to "database".
         */
        public static void createPlaylists() {
            for (int i = 0; i < new Random().nextInt(3) + 3; ++i) { // Randomly generate playlists
                List<Song> playlist = new ArrayList<>();
                for (int j = 0; j < new Random().nextInt(3) + 5; ++j) AudioService.getRandomSong().ifPresent(playlist::add);
                playlists.add(playlist);
            }
        }

        /**
         * Get random playlist from "database".
         * @return optional with random playlist
         */
        public static Optional<List<Song>> getRandomPlaylist() {
            return HelpFunctions.getRandomObject(playlists);
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
         * @param video video to add
         */
        public static void addVideo(Video video) {
            shows.add(video);
        }

        /**
         * Get random video from "database".
         * @return optional with random video
         */
        public static Optional<Video> getRandomVideo() {
            return HelpFunctions.getRandomObject(shows);
        }
    }
}
