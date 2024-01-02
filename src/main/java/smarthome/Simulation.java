package smarthome;

import consumer.device.Device;
import creature.Creature;
import place.*;
import report.ReportCreator;
import utils.ConfigurationReader;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Simulation {
    private static Simulation INSTANCE;

    private String configurationName;
    private LocalDateTime finishTime;
    private LocalDateTime currentTime;

    private Home home;
    private final DeviceService service;
    private final Set<Creature> residents = new HashSet<>();
    private final Set<Device> devices = new HashSet<>();

    private Simulation() {
        currentTime = LocalDateTime.now().withSecond(0).withNano(0);
        service = new DeviceService();
    }

    public synchronized static Simulation getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Simulation();
            readConfig(INSTANCE);
        }
        return INSTANCE;
    }

    private static void readConfig(Simulation simulation) {
        ConfigurationReader.readSimulationConfig();
        simulation.home = new HomeBuilder(simulation.configurationName).getHome();
        ConfigurationReader.readCreatureConfig(simulation.configurationName);
        ConfigurationReader.readDeviceConfig(simulation.configurationName);
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

    public DeviceService getService() {
        return service;
    }

    public Set<Creature> getResidents() {
        return residents;
    }

    public Set<Device> getDevices() {
        return devices;
    }

    public void simulate() {
        // TODO Log simulation start

        ReportCreator.createConfigurationReport();

        while (currentTime.equals(finishTime)) {
            // TODO Log step of simulation (currentTime)

            Street.getInstance().routine(); // Update street parameters (temperature, humidity, brightness)
            service.routine(); // Do device service routine
            home.getFloors().stream().flatMap(floor -> floor.getRooms().stream()).forEach(Room::routine); // Do rooms routine

            residents.forEach(Creature::routine); // Do creatures routine
            devices.forEach(Device::routine); // Do devices routine

            currentTime = currentTime.plusMinutes(1);
            if (false) ReportCreator.createReports(); // TODO Change condition (once in a day)
        }

        // TODO Log simulation finish
    }
}
