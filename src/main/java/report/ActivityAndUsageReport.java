package report;

import consumer.device.Device;
import creature.Action;
import creature.Creature;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ActivityAndUsageReport extends Report {
    private final String creature;
    private final String activity;
    private final String usage;

    public ActivityAndUsageReport(String creature, String activity, String usage) {
        super(ReportType.ACTIVITY);
        this.creature = creature;
        this.activity = activity;
        this.usage = usage;
    }

    public String getCreature() {
        return creature;
    }

    public String getActivity() {
        return activity;
    }

    public String getUsage() {
        return usage;
    }
}
