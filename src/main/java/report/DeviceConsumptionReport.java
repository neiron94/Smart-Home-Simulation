package report;

import consumer.device.Device;
import smarthome.Simulation;
import java.util.Map;

public class DeviceConsumptionReport extends ConsumptionReport {
    private final String device;

    private DeviceConsumptionReport(String usedGas, String usedWater, String usedElectricity, String spentMoney, String device) {
        super(usedGas, usedWater, usedElectricity, spentMoney);
        this.device = device;
    }

    public static DeviceConsumptionReport makeReport(Device device) { // Factory method
        double usedGas = Simulation.getInstance().getHome().getGasSupplySystem().getConsumedMap().entrySet().stream() // Get gas consumers stream
                                    .filter(entry -> entry.getKey() == device) // Filter this device
                                    .map(Map.Entry::getValue) // Get consumed gas value
                                    .findFirst().orElse(0.0);  // TODO Or throw error?
        double usedWater = Simulation.getInstance().getHome().getWaterSupplySystem().getConsumedMap().entrySet().stream() // Get water consumers stream
                                    .filter(entry -> entry.getKey() == device) // Filter this device
                                    .map(Map.Entry::getValue) // Get consumed water value
                                    .findFirst().orElse(0.0);  // TODO Or throw error?
        double usedElectricity = Simulation.getInstance().getHome().getElectricitySupplySystem().getConsumedMap().entrySet().stream() // Get electricity consumers stream
                                    .filter(entry -> entry.getKey() == device) // Filter this device
                                    .map(Map.Entry::getValue) // Get consumed electricity value
                                    .findFirst().orElse(0.0);  // TODO Or throw error?
        int spentMoney = 0; // TODO Make formulae to count money + choose proper class to store Money

        return new DeviceConsumptionReport(String.valueOf(usedGas),
                                           String.valueOf(usedWater),
                                           String.valueOf(usedElectricity),
                                           String.valueOf(spentMoney),
                                           device.toString());
    }

    @Override
    public void saveReport() {
        // TODO Path to .txt file by default

        // TODO Create file device.txt
    }
}
