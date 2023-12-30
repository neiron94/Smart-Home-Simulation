package smarthome;

import consumer.device.Device;
import creature.Creature;
import place.DeviceService;
import place.Home;
import place.HomeBuilder;
import utils.ConfigurationReader;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Simulation {
    private static Simulation instance = null;
    public static String configurationName;
    public static Date finishTime; // TODO Choose proper class
    public static Date reportTime; // TODO Choose proper class

    private Date currentTime; // TODO Choose proper class, changes in simulate() function

    private Home home;
    private final List<Creature> residents = new ArrayList<>();
    private final List<Device> devices = new ArrayList<>();
    private final DeviceService service = new DeviceService();

    private double streetTemperature; // TODO Changes in calculateSimulation() function
    private int streetBrightness; // TODO Changes in calculateSimulation() function
    private int streetHumidity; // TODO Changes in calculateSimulation() function

    private Simulation() {
        currentTime = null; // TODO Get local PC time
        ConfigurationReader.readSimulationConfig();

        streetTemperature = 0; // TODO Initialize value of temperature - or maybe at first iteration ??
        streetHumidity = 0; // TODO Initialize value of humidity - or maybe at first iteration ??
        streetBrightness = 0; // TODO Initialize value of brightness - or maybe at first iteration ??
    }

    public static Simulation getInstance() {
        if (instance == null) {
            instance = new Simulation();
            readConfig(instance);
        }
        return instance;
    }

    private static void readConfig(Simulation simulation) {
        simulation.home = new HomeBuilder(configurationName).buildHome();
        ConfigurationReader.readCreatureConfig(configurationName);
        ConfigurationReader.readDeviceConfig(configurationName);
    }

    public Date getCurrentTime() {
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
        // TODO Create HouseConfigurationReport

        while (true) { // TODO Change condition to compare currentTime and finishTime
            calculateSimulation(); // Updates simulation parameters (temperature, humidity, brightness)

            residents.forEach(Creature::routine); // Calls routine function in all creatures
            devices.forEach(Device::routine); // Calls routine function in all devices

            if (false) { // TODO Change condition (once in a day)
                generateReport(); // generates reports
                home.getGasSupplySystem().restoreConsumptions(); // After reporting - count from the beginning
                home.getWaterSupplySystem().restoreConsumptions(); // After reporting - count from the beginning
                home.getElectricitySupplySystem().restoreConsumptions(); // After reporting - count from the beginning
            }
        }
    }

    private void calculateSimulation() {
        // TODO Actualize simulation parameters
        // TODO streetTemperature depends on time and month
        // TODO streetBrightness depends on time
        // TODO streetHumidity depends on month
    }

    private void generateReport() {
        // TODO Generate and save Activity and Usage Report
        // TODO Generate and save Consumption Report
        // TODO Generate and save Event Report
    }
}
