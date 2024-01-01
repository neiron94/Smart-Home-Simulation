package report;

public class ConsumptionReport extends Report {
    private final String device;
    private final String usedGas;
    private final String usedWater;
    private final String usedElectricity;
    private final String spentMoney;

    public ConsumptionReport(String device, String usedGas, String usedWater, String usedElectricity, String spentMoney) {
        super(ReportType.CONSUMPTION);
        this.device = device;
        this.usedGas = usedGas;
        this.usedWater = usedWater;
        this.usedElectricity = usedElectricity;
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
}
