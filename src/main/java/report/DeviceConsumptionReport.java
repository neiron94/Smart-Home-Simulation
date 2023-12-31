package report;

import consumer.device.Device;
import smarthome.Simulation;
import java.util.Map;

public class DeviceConsumptionReport extends ConsumptionReport {
    private final String device;

    public DeviceConsumptionReport(String usedGas, String usedWater, String usedElectricity, String spentMoney, String device) {
        super(usedGas, usedWater, usedElectricity, spentMoney);
        this.device = device;
    }

    public String getDevice() {
        return device;
    }
}
