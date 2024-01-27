/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

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
