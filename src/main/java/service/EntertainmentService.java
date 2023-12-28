package service;

import consumer.device.common.Game;
import consumer.device.common.Song;
import consumer.device.common.Video;
import java.util.Random;

import java.util.ArrayList;
import java.util.List;

public class EntertainmentService {
    public static class GameService {
        private final static List<Game> games = new ArrayList<>(); // simulation of database of games TODO - should be preloaded

        public static void addGame(Game game) {
            games.add(game);
        }

        public static Game getRandomGame() {
            return getRandomObject(games);
        }
    }

    public static class AudioService {
        private final static List<Song> songs = new ArrayList<>(); // simulation of database of songs  TODO - should be preloaded
        private final static List<List<Song>> playlists = new ArrayList<>(); // simulation of database of playlists TODO - should be precreated

        public static void addSong(Song song) {
            songs.add(song);
        }

        public static Song getRandomSong() {
            return getRandomObject(songs);
        }

        public static void addPlaylist(List<Song> playlist) {
            // TODO
        }

        public static List<Song> getRandomPlaylist() {
            return getRandomObject(playlists);
        }
    }

    public static class VideoService {
        private final static List<Video> shows = new ArrayList<>();    // simulation of database of videos TODO - should be preloaded

        public static void addVideo(Video video) {
            shows.add(video);
        }

        public static Video getRandomVideo() {
            return getRandomObject(shows);
        }
    }

    private static <T> T getRandomObject(List<T> array) {
        Random random = new Random();
        try {
            int randomIndex = random.nextInt(array.size());
            return array.get(randomIndex);
        } catch (IllegalArgumentException e) {
            return null; // TODO Change return value
        }
    }
}
