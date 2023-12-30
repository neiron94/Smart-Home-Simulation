package consumer.device.common.entertainment;

import utils.ConfigurationReader;
import utils.HelpFunctions;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EntertainmentService {

    static {
        ConfigurationReader.readContentConfig();

        for (int i = 0; i < new Random().nextInt(3) + 3; ++i) { // Randomly generate playlists
            List<Song> playlist = new ArrayList<>();
            for (int j = 0; j < new Random().nextInt(3) + 5; ++j) playlist.add(AudioService.getRandomSong());
            AudioService.addPlaylist(playlist);
        }
    }

    public static class GameService {
        private final static List<Game> games = new ArrayList<>(); // Simulation of database with games

        public static void addGame(Game game) {
            games.add(game);
        }

        public static Game getRandomGame() {
            return HelpFunctions.getRandomObject(games);
        }
    }

    public static class AudioService {
        private final static List<Song> songs = new ArrayList<>(); // Simulation of database with songs
        private final static List<List<Song>> playlists = new ArrayList<>(); // Simulation of database with playlists

        public static void addSong(Song song) {
            songs.add(song);
        }

        public static Song getRandomSong() {
            return HelpFunctions.getRandomObject(songs);
        }

        public static void addPlaylist(List<Song> playlist) {
            playlists.add(playlist);
        }

        public static List<Song> getRandomPlaylist() {
            return HelpFunctions.getRandomObject(playlists);
        }
    }

    public static class VideoService {
        private final static List<Video> shows = new ArrayList<>(); // Simulation of database with videos

        public static void addVideo(Video video) {
            shows.add(video);
        }

        public static Video getRandomVideo() {
            return HelpFunctions.getRandomObject(shows);
        }
    }
}
