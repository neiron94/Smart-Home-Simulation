package report;

/**
 * Report is created every simulation day and is saved to file.
 */
public abstract class Report {
    protected final ReportType reportType;

    public Report(ReportType type) {
        this.reportType = type;
    }

    /**
     * Takes text of a report to save it to file.
     * @return Text of a report.
     */
    public abstract String toString();

    public ReportType getReportType() {
        return reportType;
    }
}
