package report;

public abstract class ConsumptionReport extends Report {
    private final String usedGas;
    private final String usedWater;
    private final String usedElectricity;
    private final String spentMoney;

    protected ConsumptionReport(String usedGas, String usedWater, String usedElectricity, String spentMoney) {
        super();
        this.usedGas = usedGas;
        this.usedWater = usedWater;
        this.usedElectricity = usedElectricity;
        this.spentMoney = spentMoney;
    }
}
