package creature;

import consumer.device.Device;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Activity {
    private List<Action> activities;
    private Map<Device, Integer> usage;

    public Activity() {
        this.activities = new ArrayList<>();
        this.usage = new HashMap<>();
    }

    public List<Action> getActivities() {
        return activities;
    }

    public Map<Device, Integer> getUsage() {
        return usage;
    }

    public void addActivity(Action action) {
        activities.add(action);
    }

    public void increaseUsage(Device device) {
        if (usage.containsKey(device)) usage.put(device, usage.get(device) + 1);
        else usage.put(device, 1);
    }
}
