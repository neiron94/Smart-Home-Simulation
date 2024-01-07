package smarthome;

import consumer.device.Device;
import creature.Creature;
import place.*;
import report.ReportCreator;
import utils.ConfigurationReader;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class Simulation {
    private static final LocalTime REPORT_TIME = LocalTime.of(0, 0);

    private static Simulation INSTANCE;
    public synchronized static Simulation getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Simulation();
            readConfigurations(INSTANCE);
        }
        return INSTANCE;
    }

    private String configurationName;
    private LocalDateTime finishTime;
    private LocalDateTime currentTime;

    private Home home;
    private final Set<Creature> creatures = new HashSet<>();
    private final Set<Device> devices = new HashSet<>();

    private Simulation() {
        currentTime = LocalDateTime.now().withSecond(0).withNano(0);
    }

    private static void readConfigurations(Simulation simulation) {
        ConfigurationReader.readSimulationConfig(simulation); // Read main configuration
        simulation.home = new HomeBuilder(simulation.configurationName).getHome(); // Create home
        ConfigurationReader.readCreatureConfig(simulation.configurationName); // Create creatures
        ConfigurationReader.readDeviceConfig(simulation.configurationName); // Create devices

        ReportCreator.createConfigurationReport();
    }

    public void setConfigurationName(String configurationName) {
        this.configurationName = configurationName;
    }

    public void setFinishTime(LocalDateTime finishTime) {
        this.finishTime = finishTime;
    }

    public LocalDateTime getCurrentTime() {
        return currentTime;
    }

    public Home getHome() {
        return home;
    }

    public Set<Creature> getCreatures() {
        return creatures;
    }

    public Set<Device> getDevices() {
        return devices;
    }

    public void simulate() {
        // TODO Log simulation start

        while (!currentTime.isAfter(finishTime)) {
                // TODO Log step of simulation once a day (currentTime)

                Street.getInstance().routine(); // Update street parameters (temperature, humidity, brightness)
                home.getFloors().stream().flatMap(floor -> floor.getRooms().stream()).forEach(Room::routine); // Do rooms routine

                devices.forEach(Device::routine); // Do devices routine
                creatures.forEach(Creature::routine); // Do creatures routine

                currentTime = currentTime.plusMinutes(1);
                if (currentTime.toLocalTime().equals(REPORT_TIME)) ReportCreator.createReports();
        }

        // TODO Log simulation finish
    }
}
