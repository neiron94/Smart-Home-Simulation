package report;

import smarthome.Simulation;

public abstract class Report {
    protected final String date;
    protected final ReportType reportType;

    public Report(ReportType type) {
        this.date = Simulation.getInstance().getCurrentTime().toString(); // TODO Convert to String in format DD/MM/YYYY
        this.reportType = type;
    }

    public String getDate() {
        return date;
    }

    public ReportType getReportType() {
        return reportType;
    }
}
