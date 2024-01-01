package report;

import smarthome.Simulation;

public abstract class Report {
    protected final ReportType reportType;

    public Report(ReportType type) {
        this.reportType = type;
    }

    public ReportType getReportType() {
        return reportType;
    }

    public abstract String toString();
}
