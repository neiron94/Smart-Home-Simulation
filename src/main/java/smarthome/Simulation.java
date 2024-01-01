package smarthome;

import consumer.device.Device;
import creature.Creature;
import place.DeviceService;
import place.Home;
import place.HomeBuilder;
import report.ReportCreator;
import report.ReportFactory;
import report.ReportType;
import utils.ConfigurationReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Simulation {
    private static Simulation INSTANCE;

    private String configurationName;
    private LocalDateTime finishTime;
    private LocalDateTime currentTime; // TODO changes in simulate() function

    private Home home;
    private final List<Creature> residents = new ArrayList<>();
    private final List<Device> devices = new ArrayList<>();
    private final DeviceService service = new DeviceService();

    private double streetTemperature; // TODO Changes in calculateSimulation() function
    private int streetBrightness; // TODO Changes in calculateSimulation() function
    private int streetHumidity; // TODO Changes in calculateSimulation() function

    private Simulation() {
        currentTime = LocalDateTime.now(); // TODO Cut of seconds
        streetTemperature = 0; // TODO Initialize value of temperature - or maybe at first iteration ??
        streetHumidity = 0; // TODO Initialize value of humidity - or maybe at first iteration ??
        streetBrightness = 0; // TODO Initialize value of brightness - or maybe at first iteration ??
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
        String configurationName = Simulation.getInstance().configurationName;
        simulation.home = new HomeBuilder(configurationName).getHome();
        ConfigurationReader.readCreatureConfig(configurationName);
        ConfigurationReader.readDeviceConfig(configurationName);
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

    public List<Creature> getResidents() {
        return residents;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public DeviceService getService() {
        return service;
    }

    public double getStreetTemperature() {
        return streetTemperature;
    }

    public int getStreetBrightness() {
        return streetBrightness;
    }

    public int getStreetHumidity() {
        return streetHumidity;
    }

    public void simulate() {
        ReportCreator.createConfigurationReport();

        while (true) { // TODO Change condition to compare currentTime and finishTime
            calculateSimulation(); // Updates simulation parameters (temperature, humidity, brightness)

            residents.forEach(Creature::routine); // Calls routine function in all creatures
            devices.forEach(Device::routine); // Calls routine function in all devices

            if (false) ReportCreator.createReports(); // TODO Change condition (once in a day)
        }

        // TODO Log simulation finish
    }

    private void calculateSimulation() {
        // TODO Actualize simulation parameters
        // TODO streetTemperature depends on time and month
        // TODO streetBrightness depends on time
        // TODO streetHumidity depends on month
    }
}
