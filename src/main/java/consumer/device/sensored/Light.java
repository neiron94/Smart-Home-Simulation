package consumer.device.sensored;

import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import consumer.device.sensored.sensor.LightSensor;
import place.Room;
import utils.Constants.Consumption.Electricity;
import utils.HelpFunctions;


import java.awt.*;

public class Light extends ParameterDevice<LightSensor> {
    private Color color;

    public Light(int id, Room startRoom) {
        super(DeviceType.LIGHT, id, startRoom);
        color = new Color(Color.YELLOW.getRGB());
        this.sensor = new LightSensor();
        this.sensor.attachDevice(this);
    }

    @Override
    public void react(Number parameter) {
        // TODO - increment and decrement by 5?
        if (parameter.doubleValue() < room.getControlPanel().getBrightness())
            power = HelpFunctions.adjustPercent(++power);
        else if (parameter.doubleValue() > room.getControlPanel().getBrightness())
            power = HelpFunctions.adjustPercent(--power);

        if (color.getRGB() != room.getControlPanel().getColor().getRGB())
            color = new Color(room.getControlPanel().getColor().getRGB());
    }

    @Override
    public double consumeElectricity() {
        return HelpFunctions.countElectricityConsumption(status, Electricity.LIGHT * power);
    }

    @Override
    public void setStatus(DeviceStatus status) {
        if (status != DeviceStatus.STANDBY)
            this.status = status;
    }

    public Color getColor() {
        return color;
    }
}
