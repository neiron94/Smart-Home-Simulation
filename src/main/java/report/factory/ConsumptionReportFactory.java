package report.factory;

import consumer.device.Device;
import place.Home;
import report.ConsumptionReport;
import report.Report;
import smarthome.Simulation;
import utils.Constants;

import java.util.Map;

/**
 * Class for creating ConsumptionReport.
 */
public class ConsumptionReportFactory implements ReportFactory {

    private final Device device;

    public ConsumptionReportFactory(Device device) {
        this.device = device;
    }

    /**
     * Creates new ConsumptionReport.
     * @return New ConsumptionReport.
     */
    @Override
    public Report createReport() {
        Home home = Simulation.getInstance().getHome();

        double usedElectricity = home.getElectricitySupplySystem().getConsumedMap().entrySet().stream() // Get electricity consumers stream
                .filter(entry -> entry.getKey() == device) // Filter this device
                .map(Map.Entry::getValue) // Get consumed electricity value
                .findFirst().orElse(0.0);
        double usedWater = home.getWaterSupplySystem().getConsumedMap().entrySet().stream() // Get water consumers stream
                .filter(entry -> entry.getKey() == device) // Filter this device
                .map(Map.Entry::getValue) // Get consumed water value
                .findFirst().orElse(0.0);
        double usedGas = home.getGasSupplySystem().getConsumedMap().entrySet().stream() // Get gas consumers stream
                .filter(entry -> entry.getKey() == device) // Filter this device
                .map(Map.Entry::getValue) // Get consumed gas value
                .findFirst().orElse(0.0);
        double spentMoney = usedGas * Constants.Consumption.GAS_COST;
        spentMoney += usedWater * Constants.Consumption.WATER_COST;
        spentMoney += usedElectricity * Constants.Consumption.ELECTRICITY_COST;

        return new ConsumptionReport(device.toString(),
                String.format("%.2f", usedElectricity),
                String.format("%.2f", usedWater),
                String.format("%.2f", usedGas),
                String.format("%.2f", spentMoney));

    }
}
