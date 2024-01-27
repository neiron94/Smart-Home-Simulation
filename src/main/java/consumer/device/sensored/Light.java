/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package consumer.device.sensored;

import consumer.device.DeviceType;
import consumer.device.sensored.sensor.LightSensor;
import place.Room;
import utils.Constants.Consumption.Electricity;
import utils.HelpFunctions;
import java.awt.*;

/**
 * Light for increasing room's brightness.
 */
public class Light extends ParameterDevice<LightSensor> {
    private Color color;

    public Light(int id, Room startRoom) {
        super(DeviceType.LIGHT, id, startRoom);
        color = new Color(Color.YELLOW.getRGB());
        this.sensor = new LightSensor();
        this.sensor.attachDevice(this);
    }

    /**
     * Increase or decrease power depending on given parameter ond preferred parameter
     * in Control Panel of the room. Also changes color.
     * @param parameter Changed room parameter.
     */
    @Override
    public void react(Number parameter) {
        if (parameter.doubleValue() < room.getControlPanel().getBrightness())
            setPower(power + 1);
        else if (parameter.doubleValue() > room.getControlPanel().getBrightness())
            setPower(power - 1);

        if (color.getRGB() != room.getControlPanel().getColor().getRGB())
            color = new Color(room.getControlPanel().getColor().getRGB());
    }

    @Override
    public double consumeElectricity() {
        return HelpFunctions.countElectricityConsumption(status, Electricity.LIGHT * power / 100);
    }

    public Color getColor() {
        return color;
    }

    @Override
    public Light copy() {
        return new Light(id, room);
    }
}
