package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import consumer.device.DeviceFactory;
import consumer.device.Device;
import creature.Creature;
import creature.CreatureFactory;
import place.Room;
import smarthome.Simulation;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

public class ConfigurationReader {
    public static void readSimulationConfig() {
        String configPath = System.getProperty("user.dir") + "/config/Simulation.json";
        JsonNode config = openConfig(configPath);

        Simulation.configurationName = config.path("config").asText();
        Simulation.finishTime = 0; // TODO Count simulation finish time
        Simulation.reportTime = 0; // TODO Count simulation report time
    }

    public static JsonNode readHomeConfig(String configName) {
        String configPath = System.getProperty("user.dir") + "/config/" + configName + "/Home.json";
        return openConfig(configPath);
    }

    public static void readDeviceConfig(String configName) {
        String configPath = System.getProperty("user.dir") + "/config/" + configName + "/Device.json";
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
        String configPath = System.getProperty("user.dir") + "/config/" + configName + "/Creature.json";
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
        String configPath = System.getProperty("user.dir") + "/config/RoomConfiguration.json";
        JsonNode config = openConfig(configPath);

    }

    public static void readContentConfig() {
        String configPath = System.getProperty("user.dir") + "/config/Content.json";
        JsonNode config = openConfig(configPath);

    }

    private static JsonNode openConfig(String configPath) {
        try {
            return new ObjectMapper().readTree(new File(configPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
