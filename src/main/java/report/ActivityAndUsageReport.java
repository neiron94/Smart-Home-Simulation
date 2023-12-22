package report;

import creature.Action;
import creature.Creature;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ActivityAndUsageReport extends Report {
    private final String creature;
    private final String activity;
    private final String usage;

    private ActivityAndUsageReport(String creature, String activity, String usage) {
        super();
        this.creature = creature;
        this.activity = activity;
        this.usage = usage;
    }

    public static ActivityAndUsageReport makeReport(Creature creature) { // Factory method // TODO Control after creation Device class
        String creatureName = creature.getName();

        String activityString = creature.getActivity().getActivities().stream() // Get activities stream
                                    .map(x -> "\t" + x.getDescription()) // Get activity string representation stream
                                    .collect(Collectors.joining("\n")); // Make result string

        String usageString = creature.getActivity().getUsage().entrySet().stream() // Get usage stream
                                    .map(x -> "\t" + x.getKey().toString() + " " + x.getValue().toString()) // Get usage string representation stream
                                    .collect(Collectors.joining("\n")); // Make result string

        return new ActivityAndUsageReport(creatureName, activityString, usageString);
    }

    @Override
    public void saveReport() {
        // TODO Path to .txt file by default

        // TODO Create file creature.txt
    }
}
