/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package report;

import java.util.List;

/**
 * Report containing information about person's
 * activity (text interpretation of actions and number of usages of different devices).
 */
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

    @Override
    public String toString() {
        return '\t' + creature + '\n' +
                activities.stream().reduce("\t\tActivity", (result, activity) -> result + ("\n\t\t\t" + activity)) + '\n' +
                usages.stream().reduce("\t\tUsage", (result, usage) -> result + ("\n\t\t\t" + usage));
    }
}
