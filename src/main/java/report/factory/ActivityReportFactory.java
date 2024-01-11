package report.factory;

import creature.Creature;
import report.ActivityAndUsageReport;
import report.Report;

import java.util.List;

/**
 * Class for creating ActivityAndUsageReport.
 */
public class ActivityReportFactory implements ReportFactory {

    private final Creature creature;

    public ActivityReportFactory(Creature creature) {
        this.creature = creature;
    }

    /**
     * Creates new ActivityAndUsageReport.
     * @return New ActivityAndUsageReport.
     */
    @Override
    public Report createReport() {
        String name = creature.getName();

        List<String> activities = creature.getActivity().getActivities(); // Get activities stream

        List<String> usages = creature.getActivity().getUsage().entrySet().stream() // Get usage stream
                .map(entry -> String.format("%s - %d", entry.getKey().toString(), entry.getValue())) // Get usage string representation stream
                .toList(); // Convert representations to list

        return new ActivityAndUsageReport(name, activities, usages);
    }
}
