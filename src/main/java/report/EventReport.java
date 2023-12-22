package report;

import creature.person.Person;

public class EventReport extends Report {
    private final String solver;
    private final String creator;
    private final String type;

    private EventReport(String creature, String device, String type) {
        super();
        solver = creature;
        creator = device;
        this.type = type;
    }

    public static EventReport makeReport(Person solver, Event event) { // Factory method // TODO Control after creating Event class
        String solverName = solver.getName();
        String creatorName = event.getCreator().toString();
        String typeName = event.getEventType().toString();
        return new EventReport(solverName, creatorName, typeName);
    }

    @Override
    public void saveReport() {
        // TODO Path to .txt file by default

        // TODO Create file creator.txt - append method
        // TODO Create file solver.txt - append method
        // TODO Create file type.txt - append method
    }
}
