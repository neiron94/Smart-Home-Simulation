/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package smarthome;

import consumer.DeleteVisitor;
import consumer.supplySystem.SupplySystem;
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

/**
 * Core singleton class which controls the simulation flow.
 */
public class Simulation {
    private static final LocalTime REPORT_TIME = LocalTime.of(0, 0);

    private static Simulation INSTANCE;

    /**
     * Singleton getInstance method. Reads configuration files when is called for the first time.
     * @return instance of Simulation.
     */
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

    /**
     * Main cycle of the program. Calls routine of street, rooms, devices, creatures every tick.
     * Can randomly creat shutdown of some supply system. Creates reports and
     * deletes all dead people and nonfunctional devices in the end of each day.
     */
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
                List<Device> brokenDevices = devices.stream().filter(device -> !device.isFunctional()).toList();
                brokenDevices.forEach(device -> device.accept(new DeleteVisitor()));
                creatures.removeIf(creature -> !creature.isAlive());
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
            HelpFunctions.getRandomObject(supplySystems).ifPresent(SupplySystem::shutdown);
            restoreSupplySystemTime = currentTime.plusHours(new Random().nextInt(1, 4));
        }
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
}
