package report;

public enum ReportType {
    CONFIGURATION("HouseConfigurationReport", "txt"),
    CONSUMPTION("ConsumptionReport", "csv"),
    ACTIVITY("ActivityAndUsageReport", "txt"),
    EVENT("EventReport", "csv");

    private final String description;
    private final String extension;

    ReportType(String description, String extension) {
        this.description = description;
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }

    @Override
    public String toString() {
        return description;
    }
}
