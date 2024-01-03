package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.device.Device;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import place.Room;
import utils.Constants.Consumption.Electricity;
import utils.HelpFunctions;


public class Fridge extends Device implements ElectricityConsumer {

    private static final double MIN_TEMPERATURE = 0;
    private static final double MAX_TEMPERATURE = 10;

    private double temperature; // 0-5 °C
    private int fullness;   // percent

    public Fridge(int id, Room startRoom) {
        super(DeviceType.FRIDGE, id, startRoom);
        fullness = 0;
        temperature = 0;
    }

    //--------- Main public functions ----------//

    @Override
    public double consumeElectricity() {
        return HelpFunctions.countElectricityConsumption(status, Electricity.FRIDGE / 2 + Electricity.FRIDGE / 2 * (MAX_TEMPERATURE - temperature) / MAX_TEMPERATURE);
    }

    //---------- API for human -----------//

    public int takeFood(int amount) {
        int actualAmount = Math.min(amount, fullness);
        setFullness(fullness - actualAmount);
        if (fullness <= 0)  orderFood();
        return actualAmount;
    }

    public void putFood(int amount) {
        setFullness(fullness + amount);
    }

    public void increaseTemperature() {
        setTemperature(temperature + 1);
    }

    public void decreaseTemperature() {
        setTemperature(temperature - 1);
    }

    //------------- Help functions -------------//

    private void orderFood() {
        setFullness(100);
    }

    //---------- Getters and Setters ----------//

    public double getTemperature() {
        return temperature;
    }

    private void setTemperature(double temperature) {
        this.temperature = HelpFunctions.adjustToRange(temperature, MIN_TEMPERATURE, MAX_TEMPERATURE);
    }

    public int getFullness() {
        return fullness;
    }

    private void setFullness(int fullness) {
        this.fullness = HelpFunctions.adjustPercent(fullness);
    }
}
