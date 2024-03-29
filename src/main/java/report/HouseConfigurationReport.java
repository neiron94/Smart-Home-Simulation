/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package report;

import java.util.List;
import java.util.Map;

/**
 * Report containing information about house configuration (residents and topology of floors and rooms).
 */
public class HouseConfigurationReport extends Report {
    private final Map<String, List<String>> hierarchy;
    private final List<String> residents;

    public HouseConfigurationReport(Map<String, List<String>> hierarchy, List<String> residents) {
        super(ReportType.CONFIGURATION);
        this.hierarchy = hierarchy;
        this.residents = residents;
    }

    public Map<String, List<String>> getHierarchy() {
        return hierarchy;
    }

    public List<String> getResidents() {
        return residents;
    }

    @Override
    public String toString() {
        StringBuilder report = new StringBuilder();

        report.append("Home").append('\n');
        hierarchy.keySet().stream().sorted().toList().forEach(key -> report.append(hierarchy.get(key).stream().reduce('\t' + key, (result, room) -> result + ("\n\t\t" + room))).append('\n'));

        report.append("Residents").append('\n');
        residents.forEach(resident -> report.append('\t').append(resident).append('\n'));

        return report.toString();
    }
}
