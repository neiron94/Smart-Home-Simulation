package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import consumer.device.DeviceFactory;
import consumer.device.Device;
import creature.Creature;
import creature.CreatureFactory;
import place.Room;
import service.EntertainmentFactory;
import service.EntertainmentService;
import smarthome.Simulation;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

public class ConfigurationReader {
    public static final String CONFIG_PATH = System.getProperty("user.dir") + "/src/main/resources/config/";

    public static void readSimulationConfig() {
        String configPath = CONFIG_PATH + "Simulation.json";
        JsonNode config = openConfig(configPath);

        Simulation.configurationName = config.path("config").asText();
        Simulation.finishTime = 0; // TODO Count simulation finish time
        Simulation.reportTime = 0; // TODO Count simulation report time
    }

    public static JsonNode readHomeConfig(String configName) {
        String configPath = CONFIG_PATH + configName + "/Home.json";
        return openConfig(configPath);
    }

    public static void readDeviceConfig(String configName) {
        String configPath = CONFIG_PATH + configName + "/Device.json";
        JsonNode config = openConfig(configPath);

        List<Device> devices = Simulation.getInstance().getDevices();

        config.fields().forEachRemaining(device -> {
            for (int i = 0; i < device.getValue().size(); ++i) {
                int roomId = device.getValue().get(i).asInt();
                Room startRoom = Simulation.getInstance().getHome().getFloors().stream()
                        .flatMap(floor -> floor.getRooms().stream())
                        .filter(room -> room.getId() == roomId)
                        .findFirst().orElseThrow(NoSuchElementException::new);
                devices.add(new DeviceFactory(device.getKey()).createDevice(i+1, startRoom));
            }
        });
    }

    public static void readCreatureConfig(String configName) {
        String configPath = CONFIG_PATH + configName + "/Creature.json";
        JsonNode config = openConfig(configPath);

        CreatureFactory factory = new CreatureFactory();
        List<Creature> creatures = Simulation.getInstance().getResidents();

        for (int i = 0; i < config.path("PERSON").size(); ++i) {
            JsonNode person = config.path("PERSON").get(i);
            String name = person.path("name").asText();
            String gender = person.path("gender").asText();
            String familyStatus = person.path("status").asText();
            creatures.add(factory.createPerson(name, gender, familyStatus));
        }

        for (int i = 0; i < config.path("PET").size(); ++i) {
            JsonNode pet = config.path("PET").get(i);
            String name = pet.path("name").asText();
            String petType = pet.path("type").asText();
            creatures.add(factory.createPet(name, petType));
        }
    }

    public static void readRoomConfigurationConfig() {
        String configPath = CONFIG_PATH + "RoomConfiguration.json";
        JsonNode config = openConfig(configPath);

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

    private static JsonNode openConfig(String configPath) {
        try {
            return new ObjectMapper().readTree(new File(configPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
