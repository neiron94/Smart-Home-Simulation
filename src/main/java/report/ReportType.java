/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package report;

/**
 * Enum for holding name and output filename of reports of different types.
 */
public enum ReportType {
    CONFIGURATION("Configuration", "txt"),
    CONSUMPTION("Consumption", "tsv"),
    ACTIVITY("Activity", "txt"),
    EVENT("Event", "tsv");

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
