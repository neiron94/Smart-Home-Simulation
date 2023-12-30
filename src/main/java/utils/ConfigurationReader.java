package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import smarthome.Simulation;

import java.io.File;
import java.io.IOException;

public class ConfigurationReader {
    public static void readSimulationConfig() {
        String configPath = System.getProperty("user.dir") + "/config/Simulation.json";
        try {
            JsonNode config = new ObjectMapper().readTree(new File(configPath));
            Simulation.configurationName = config.path("config").asText();
            Simulation.finishTime = 0; // TODO Count simulation finish time
            Simulation.reportTime = 0; // TODO Count simulation report time
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JsonNode readHomeConfig(String config) {
        String configPath = System.getProperty("user.dir") + "/config/" + config;
        try {
            return new ObjectMapper().readTree(new File(configPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readDeviceConfig(String config) {

    }

    public static void readCreatureConfig(String config) {

    }

    public static void readRoomConfigurationConfig() {

    }

    public static void readContentConfig() {

    }
}
