package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import smarthome.Simulation;

import java.io.File;
import java.io.IOException;

public class ConfigurationReader {
    public static void readSimulationConfig() {
        String configPath = System.getProperty("user.dir") + "/config/Simulation.json";
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode config = mapper.readTree(new File(configPath));
            Simulation.configuration = config.path("config").asText();
            Simulation.finishTime = 0; // TODO Count simulation finish time
            Simulation.reportTime = 0; // TODO Count simulation report time
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
