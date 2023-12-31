package report;

import creature.person.Person;
import event.Event;

public class EventReport extends Report {
    private final String solver;
    private final String creator;
    private final String type;

    public EventReport(String creature, String device, String type) {
        super(ReportType.EVENT);
        solver = creature;
        creator = device;
        this.type = type;
    }

    public String getSolver() {
        return solver;
    }

    public String getCreator() {
        return creator;
    }

    public String getType() {
        return type;
    }
}
