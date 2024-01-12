package creature;

import consumer.device.Device;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Activity of a creature. Contains information for
 * report (text representation of performed actions and usage map for used devices).
 */
public class Activity {
    private final List<String> activities;
    private final Map<Device, Integer> usage;

    /**
     * Creates new activity object.
     */
    public Activity() {
        this.activities = new ArrayList<>();
        this.usage = new HashMap<>();
    }

    /**
     * Adds new description of performed action to list.
     * @param activity description of performed action
     */
    public void addActivity(String activity) {
        activities.add(activity);
    }

    /**
     * Increases count of usage of the specified device.
     * @param device used device
     */
    public void increaseUsage(Device device) {
        if (usage.containsKey(device)) usage.put(device, usage.get(device) + 1);
        else usage.put(device, 1);
    }

    public List<String> getActivities() {
        return activities;
    }

    public Map<Device, Integer> getUsage() {
        return usage;
    }
}
