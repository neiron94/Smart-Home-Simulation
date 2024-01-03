package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import consumer.device.DeviceFactory;
import creature.CreatureFactory;
import place.ControlPanel;
import place.Room;
import place.RoomConfiguration;
import consumer.device.common.entertainment.EntertainmentFactory;
import consumer.device.common.entertainment.EntertainmentService;
import place.Street;
import smarthome.Simulation;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;

public class ConfigurationReader {
    private static final String CONFIG_PATH = System.getProperty("user.dir") + "/src/main/resources/config/"; // TODO Find out how to open configurations (not to use System.getProperty)
    private static final int TEMPERATURE = 0; // TODO Move to constants
    private static final int HUMIDITY = 1; // TODO Move to constants
    private final static int BRIGHTNESS = 2; // TODO Move to constants
    private static final int RED = 0; // TODO Move to constants
    private static final int GREEN = 1; // TODO Move to constants
    private static final int BLUE = 2; // TODO Move to constants

    public static void readSimulationConfig(Simulation simulation) {
        String configPath = CONFIG_PATH + "Simulation.json";
        JsonNode config = openConfig(configPath);

        simulation.setConfigurationName(config.path("config").asText());
        simulation.setFinishTime(Simulation.getInstance().getCurrentTime().toLocalDate().atStartOfDay().plusDays(config.path("duration").asLong()));
    }

    public static JsonNode readHomeConfig(String configName) {
        String configPath = CONFIG_PATH + configName + "/Home.json";
        return openConfig(configPath);
    }

    public static void readDeviceConfig(String configName) {
        String configPath = CONFIG_PATH + configName + "/Device.json";
        JsonNode config = openConfig(configPath);

        config.fields().forEachRemaining(device -> {
            for (int i = 0; i < device.getValue().size(); ++i) {
                int roomId = device.getValue().get(i).asInt();
                Room startRoom = Simulation.getInstance().getHome().getFloors().stream()
                        .flatMap(floor -> floor.getRooms().stream())
                        .filter(room -> room.getId() == roomId)
                        .findFirst().orElseThrow(NoSuchElementException::new);
                new DeviceFactory(device.getKey()).createDevice(i+1, startRoom);
            }
        });
    }

    public static void readCreatureConfig(String configName) {
        String configPath = CONFIG_PATH + configName + "/Creature.json";
        JsonNode config = openConfig(configPath);

        CreatureFactory factory = new CreatureFactory();

        for (int i = 0; i < config.path("PERSON").size(); ++i) {
            JsonNode person = config.path("PERSON").get(i);
            String name = person.path("name").asText();
            String gender = person.path("gender").asText();
            String familyStatus = person.path("status").asText();
            factory.createPerson(name, gender, familyStatus);
        }

        for (int i = 0; i < config.path("PET").size(); ++i) {
            JsonNode pet = config.path("PET").get(i);
            String name = pet.path("name").asText();
            String petType = pet.path("type").asText();
            factory.createPet(name, petType);
        }
    }

    public static void readRoomConfigurationConfig() {
        String configPath = CONFIG_PATH + "RoomConfiguration.json";
        JsonNode config = openConfig(configPath);

        for (int i = 0; i < config.path("configurations").size(); ++i) {
            JsonNode configuration = config.path("configurations").get(i);
            String name = configuration.path("name").asText();
            float temperature = configuration.path("temperature").floatValue();
            int humidity = configuration.path("humidity").asInt();
            int brightness = configuration.path("brightness").asInt();
            int r = configuration.path("color").get(RED).asInt();
            int g = configuration.path("color").get(GREEN).asInt();
            int b = configuration.path("color").get(BLUE).asInt();
            ControlPanel.addConfiguration(new RoomConfiguration(name, temperature, humidity, brightness, new Color(r, g, b)));
        }
    }

    public static void readContentConfig() {
        String configPath = CONFIG_PATH + "Content.json";
        JsonNode config = openConfig(configPath);

        EntertainmentFactory factory = new EntertainmentFactory();

        for (int i = 0; i < config.path("SONG").size(); ++i) {
            JsonNode song = config.path("SONG").get(i);
            String author = song.path("author").asText();
            String album = song.path("album").asText();
            String name = song.path("name").asText();
            String genre = song.path("genre").asText();
            int duration = song.path("duration").asInt();
            EntertainmentService.AudioService.addSong(factory.createSong(author, album, name, genre, duration));
        }

        for (int i = 0; i < config.path("VIDEO").size(); ++i) {
            JsonNode video = config.path("VIDEO").get(i);
            String name = video.path("name").asText();
            String description = video.path("description").asText();
            String platform = video.path("platform").asText();
            int duration = video.path("duration").asInt();
            EntertainmentService.VideoService.addVideo(factory.createVideo(name, description, platform, duration));
        }

        for (int i = 0; i < config.path("GAME").size(); ++i) {
            JsonNode game = config.path("GAME").get(i);
            String name = game.path("name").asText();
            String description = game.path("description").asText();
            String genre = game.path("genre").asText();
            EntertainmentService.GameService.addGame(factory.createGame(name, description, genre));
        }
    }

    public static void readWeatherConfig() {
        String configPath = CONFIG_PATH + "Weather.json";
        JsonNode config = openConfig(configPath);

        JsonNode temperature = config.path("temperature");
        JsonNode humidity = config.path("temperature");
        JsonNode brightness = config.path("temperature");

        for (int i = 0; i < 12; ++i) {
            for (int j = 0; j < 24; ++j) {
                Street.stats[TEMPERATURE][i][j] = temperature.get(i).get(j).asDouble();
                Street.stats[HUMIDITY][i][j] = humidity.get(i).get(j).asDouble();
                Street.stats[BRIGHTNESS][i][j] = brightness.get(i).get(j).asDouble();
            }
        }
    }

    private static JsonNode openConfig(String configPath) {
        try {
            return new ObjectMapper().readTree(new File(configPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
