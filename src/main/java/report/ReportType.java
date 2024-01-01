package report;

public enum ReportType {
    CONFIGURATION("Configuration", "txt"),
    CONSUMPTION("Consumption", "csv"),
    ACTIVITY("Activity", "txt"),
    EVENT("Event", "csv");

    private final String name;
    private final String fileName;

    ReportType(String name, String extension) {
        this.name = String.join(" ", name, "report");
        this.fileName = String.join(".", name, extension);
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
