package report;

import consumer.device.Device;
import main.Simulation;
import place.Home;

import java.util.Map;

public class HomeConsumptionReport extends ConsumptionReport {
    private HomeConsumptionReport(String usedGas, String usedWater, String usedElectricity, String spentMoney) {
        super(usedGas, usedWater, usedElectricity, spentMoney);
    }

    public static HomeConsumptionReport makeReport(Device device) { // Factory method
        int usedGas = Simulation.getInstance().getHome().getGasSupplySystem().getConsumedMap().entrySet().stream() // Get gas consumers stream
                                    .mapToInt(Map.Entry::getValue) // Get values stream
                                    .sum(); // Sum overall consumed gas
        int usedWater = Simulation.getInstance().getHome().getWaterSupplySystem().getConsumedMap().entrySet().stream() // Get water consumers stream
                                    .mapToInt(Map.Entry::getValue) // Get values stream
                                    .sum(); // Sum overall consumed water
        int usedElectricity = Simulation.getInstance().getHome().getElectricitySupplySystem().getConsumedMap().entrySet().stream() // Get electricity consumers stream
                                    .mapToInt(Map.Entry::getValue) // Get values stream
                                    .sum(); // Sum overall consumed electricity
        int spentMoney = 0; // TODO Make formulae to count money

        return new HomeConsumptionReport(String.valueOf(usedGas),
                String.valueOf(usedWater),
                String.valueOf(usedElectricity),
                String.valueOf(spentMoney));
    }

    @Override
    public void saveReport() {
        // TODO Path to .txt file by default

        // TODO Create file home.txt
    }
}
