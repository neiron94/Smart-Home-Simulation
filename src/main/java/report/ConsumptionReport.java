package report;

public class ConsumptionReport extends Report {
    private final String usedGas;
    private final String usedWater;
    private final String usedElectricity;
    private final String spentMoney;

    protected ConsumptionReport(String usedGas, String usedWater, String usedElectricity, String spentMoney) {
        super(ReportType.CONSUMPTION);
        this.usedGas = usedGas;
        this.usedWater = usedWater;
        this.usedElectricity = usedElectricity;
        this.spentMoney = spentMoney;
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
