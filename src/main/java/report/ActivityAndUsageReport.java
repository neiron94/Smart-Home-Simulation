package report;

import consumer.device.Device;
import creature.Action;
import creature.Creature;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ActivityAndUsageReport extends Report {
    private final String creature;
    private final List<String> activities;
    private final List<String> usages;

    public ActivityAndUsageReport(String creature, List<String> activities, List<String> usages) {
        super(ReportType.ACTIVITY);
        this.creature = creature;
        this.activities = activities;
        this.usages = usages;
    }

    public String getCreature() {
        return creature;
    }

    public List<String> getActivities() {
        return activities;
    }

    public List<String> getUsages() {
        return usages;
    }
}
