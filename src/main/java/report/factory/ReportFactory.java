package report.factory;

import report.Report;

/**
 * Class for report creation.
 */
public interface ReportFactory {

    /**
     * Creates new report.
     * @return New report.
     */
    Report createReport();
}
