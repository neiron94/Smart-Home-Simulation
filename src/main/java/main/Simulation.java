package main;

import consumer.device.Device;
import creature.Creature;
import place.DeviceService;
import place.Home;
import utils.Percent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Simulation {
    private static Simulation instance = null;

    private Date currentTime; // TODO Changes in simulate() function
    private final Date finishTime;

    private final Home home;
    private final List<Creature> residents;
    private final List<Device> devices;
    private final DeviceService service;

    private double streetTemperature; // TODO Changes in calculateSimulation() function
    private Percent streetBrightness; // TODO Changes in calculateSimulation() function
    private Percent streetHumidity; // TODO Changes in calculateSimulation() function

    private Simulation() {
        home = new Home(); // TODO Make from JSON config - Home Builder ???
        residents = new ArrayList<>(); // TODO Make from JSON config - Creatures Factory ???
        devices = new ArrayList<>(); // TODO Make from JSON config - Devices Factory ???
        service = new DeviceService();

        currentTime = null; // TODO Get local PC time
        finishTime = null; // TODO Read from console or JSON config ??

        streetTemperature = 0; // TODO Initialize value of temperature - or maybe at first iteration ??
        streetHumidity = new Percent(0); // TODO Initialize value of humidity - or maybe at first iteration ??
        streetBrightness = new Percent(0); // TODO Initialize value of brightness - or maybe at first iteration ??
    }

    public static Simulation getInstance() {
        if (instance == null) instance = new Simulation();
        return instance;
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

    public Percent getStreetBrightness() {
        return streetBrightness;
    }

    public Percent getStreetHumidity() {
        return streetHumidity;
    }

    public void start() {
        readConfig(); build(); // TODO Read configs + simulation build - here or at instance creating in Main ???
        // TODO Generate Configuration Report
        simulate(); // Program loop
    }

    private void readConfig() {
        // TODO Read JSON configs - here or earlier ???
    }

    private void build() {
        // TODO Build simulation - here or earlier ???
    }

    private void simulate() {
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
