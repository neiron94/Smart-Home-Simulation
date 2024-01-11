package smarthome;

import consumer.DeleteVisitor;
import consumer.SupplySystem;
import consumer.device.Device;
import creature.Creature;
import place.*;
import report.ReportCreator;
import utils.ConfigurationReader;
import utils.Constants;
import utils.HelpFunctions;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Simulation {
    private static final LocalTime REPORT_TIME = LocalTime.of(0, 0);

    private static Simulation INSTANCE;
    public static Simulation getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Simulation();
            readConfigurations(INSTANCE);
        }
        return INSTANCE;
    }

    private String configurationName;
    private LocalDateTime finishTime;
    private LocalDateTime currentTime;
    private LocalDateTime restoreSupplySystemTime;

    private Home home;
    private final Set<Creature> creatures;
    private final Set<Device> devices;

    private Simulation() {
        currentTime = LocalDateTime.now().withSecond(0).withNano(0);
        restoreSupplySystemTime = currentTime;
        creatures = new HashSet<>();
        devices = new HashSet<>();
    }

    private static void readConfigurations(Simulation simulation) {
        ConfigurationReader.readSimulationConfig(simulation); // Read main configuration
        ConfigurationReader.readContentConfig(); // Create entertainment
        ConfigurationReader.readRoomConfigurationConfig(); // Create room configurations
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
        Street street = Street.getInstance();
        HelpFunctions.logger.info("Simulation started");

        while (!currentTime.isAfter(finishTime)) {
            shutdownSupplySystem();
            street.routine(); // Update street parameters (temperature, humidity, brightness)
            home.getFloors().stream().flatMap(floor -> floor.getRooms().stream()).forEach(Room::routine); // Do rooms routine

            devices.stream().filter(Device::isFunctional).forEach(Device::routine); // Do devices routine
            creatures.stream().filter(Creature::isAlive).forEach(Creature::routine); // Do creatures routine

            currentTime = currentTime.plusMinutes(1);
            if (currentTime.toLocalTime().equals(REPORT_TIME)) {
                HelpFunctions.logger.info(String.format("Simulating day %s", currentTime.minusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
                ReportCreator.createReports();
                devices.stream().filter(device -> !device.isFunctional()).forEach(device -> device.accept(new DeleteVisitor())); // Delete non-functional devices
                creatures.stream().filter(creature -> !creature.isAlive()).forEach(creatures::remove); // Delete dead creatures
            }
        }

        HelpFunctions.logger.info("Simulation finished");
    }

    private void shutdownSupplySystem() {
        List<SupplySystem<?>> supplySystems = List.of(home.getElectricitySupplySystem(), home.getGasSupplySystem(), home.getWaterSupplySystem());

        if (restoreSupplySystemTime.isAfter(currentTime))
            supplySystems.forEach(supplySystem -> {
                if (!supplySystem.isResourceAvailable()) supplySystem.recover();
            });

        if (Math.random() <= Constants.Probabilities.SHUTDOWN_PROBABILITY) {
            HelpFunctions.getRandomObject(supplySystems).orElseThrow(NoSuchElementException::new).shutdown();
            restoreSupplySystemTime = currentTime.plusHours(new Random().nextInt(1, 4));
        }
    }
}
