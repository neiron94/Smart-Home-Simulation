/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import consumer.device.DeviceFactory;
import creature.CreatureFactory;
import place.*;
import consumer.device.common.entertainment.EntertainmentFactory;
import consumer.device.common.entertainment.EntertainmentService;
import place.weather.Weather;
import smarthome.Simulation;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import static utils.Constants.ConfigurationReaderConstants.*;
import static utils.Constants.Time.*;

/**
 * Class for reading configuration files.
 */
public class ConfigurationReader {
    private static final String CONFIG_PATH = String.join(File.separator, System.getProperty("user.dir"), "src", "main", "resources", "config") + File.separator;

    /**
     * Reads configuration of Simulation.
     * @param simulation instance of simulation, where information will be written
     */
    public static void readSimulationConfig(Simulation simulation) {
        String configPath = CONFIG_PATH + "Simulation.json";
        JsonNode config = openConfig(configPath);

        simulation.setConfigurationName(config.path("config").asText());
        simulation.setFinishTime(simulation.getCurrentTime().toLocalDate().atStartOfDay().plusDays(config.path("duration").asLong()));

        HelpFunctions.logger.info("Main simulation configuration read");
    }

    /**
     * Reads home configuration from the specified directory.
     * @param configName directory of home configuration
     * @return opened json configuration file
     */
    public static JsonNode readHomeConfig(String configName) {
        String configPath = CONFIG_PATH + configName + File.separator + "Home.json";
        return openConfig(configPath);
    }

    /**
     * Reads device configuration from the given directory and creates all the devices.
     * @param configName directory of device configuration
     */
    public static void readDeviceConfig(String configName) {
        String configPath = CONFIG_PATH + configName + File.separator + "Device.json";
        JsonNode config = openConfig(configPath);

        config.fields().forEachRemaining(device -> {
            for (int i = 0; i < device.getValue().size(); ++i) {
                int roomId = device.getValue().get(i).asInt();
                Room startRoom = Simulation.getInstance().getHome().getFloors().stream()
                        .flatMap(floor -> floor.getRooms().stream())
                        .filter(room -> room.getId() == roomId)
                        .findFirst().orElse(null);

                if (startRoom == null) {
                    HelpFunctions.logger.warn(String.format("Problem with creating %s_%d. Device wasn't created", device.getKey(), i+1));
                    return;
                }

                try {
                    new DeviceFactory(device.getKey()).createDevice(i+1, startRoom);
                } catch (NoSuchElementException ignored) {}
            }
        });

        HelpFunctions.logger.info("Devices configuration read");
    }

    /**
     * Reads creature configuration from the given directory and creates all the creatures.
     * @param configName directory of creature configuration
     */
    public static void readCreatureConfig(String configName) {
        String configPath = CONFIG_PATH + configName + File.separator + "Creature.json";
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

        HelpFunctions.logger.info("Creatures configuration read");
    }

    /**
     * Reads pre-created room configurations from config and adds them to the
     * simulation of database.
     */
    public static void readRoomConfigurationConfig() {
        String configPath = CONFIG_PATH + "RoomConfiguration.json";
        JsonNode config = openConfig(configPath);

        for (int i = 0; i < config.path("configurations").size(); ++i) {
            JsonNode configuration = config.path("configurations").get(i);
            String name = configuration.path("name").asText();
            double temperature = configuration.path("temperature").asDouble();
            double humidity = configuration.path("humidity").asDouble();
            double brightness = configuration.path("brightness").asDouble();
            int r = configuration.path("color").get(RED).asInt();
            int g = configuration.path("color").get(GREEN).asInt();
            int b = configuration.path("color").get(BLUE).asInt();
            ControlPanel.addConfiguration(new RoomConfiguration(name, temperature, humidity, brightness, new Color(r, g, b)));
        }

        HelpFunctions.logger.info("RoomConfig configuration read");
    }

    /**
     * Reads pre-created content (songs, games, videos) from config and adds them to the
     * simulation of database.
     */
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
            factory.createSong(author, album, name, genre, duration).ifPresent(EntertainmentService.AudioService::addSong);
        }

        for (int i = 0; i < config.path("VIDEO").size(); ++i) {
            JsonNode video = config.path("VIDEO").get(i);
            String name = video.path("name").asText();
            String description = video.path("description").asText();
            String platform = video.path("platform").asText();
            int duration = video.path("duration").asInt();
            factory.createVideo(name, description, platform, duration).ifPresent(EntertainmentService.VideoService::addVideo);
        }

        for (int i = 0; i < config.path("GAME").size(); ++i) {
            JsonNode game = config.path("GAME").get(i);
            String name = game.path("name").asText();
            String description = game.path("description").asText();
            String genre = game.path("genre").asText();
            factory.createGame(name, description, genre).ifPresent(EntertainmentService.GameService::addGame);
        }

        EntertainmentService.AudioService.createPlaylists();
        HelpFunctions.logger.info("Entertainment configuration read");
    }

    /**
     * Reads pre-created weather stats from config and adds them to the
     * simulation of database.
     */
    public static void readWeatherConfig() {
        String configPath = CONFIG_PATH + "Weather.json";
        JsonNode config = openConfig(configPath);

        JsonNode temperature = config.path("temperature");
        JsonNode humidity = config.path("humidity");
        JsonNode brightness = config.path("brightness");

        for (int i = 0; i < MONTHS; ++i) {
            for (int j = 0; j < HOURS; ++j) {
                Weather.stats[TEMPERATURE][i][j] = temperature.get(i).get(j).asDouble();
                Weather.stats[HUMIDITY][i][j] = humidity.get(i).get(j).asDouble();
                Weather.stats[BRIGHTNESS][i][j] = brightness.get(i).get(j).asDouble();
            }
        }

        HelpFunctions.logger.info("Weather configuration read");
    }

    private static JsonNode openConfig(String configPath) {
        try {
            return new ObjectMapper().readTree(new File(configPath));
        } catch (IOException e) {
            HelpFunctions.logger.error(String.format("Can't open '%s'", configPath));
            System.exit(1);
            return null;
        }
    }
}
