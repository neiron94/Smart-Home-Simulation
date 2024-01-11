package report;

public class ConsumptionReport extends Report {
    private final String device;
    private final String usedElectricity;
    private final String usedWater;
    private final String usedGas;
    private final String spentMoney;

    public ConsumptionReport(String device, String usedElectricity, String usedWater, String usedGas, String spentMoney) {
        super(ReportType.CONSUMPTION);
        this.device = device;
        this.usedElectricity = usedElectricity;
        this.usedWater = usedWater;
        this.usedGas = usedGas;
        this.spentMoney = spentMoney;
    }

    public String getDevice() {
        return device;
    }

    public String getUsedGas() {
        return usedGas;
    }

    public String getUsedWater() {
        return usedWater;
    }

    public String getUsedElectricity() {
        return usedElectricity;
    }

    public String getSpentMoney() {
        return spentMoney;
    }

    @Override
    public String toString() {
        return device + '\t' +
                usedElectricity + '\t' +
                usedWater + '\t' +
                usedGas + '\t' +
                spentMoney;
    }
}
