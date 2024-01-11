package report.factory;

import creature.person.Person;
import event.Event;
import report.EventReport;
import report.Report;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class for creating EventReport.
 */
public class EventReportFactory implements ReportFactory {
    private final Event event;
    private final LocalDateTime solveTime;
    private final Person person;

    public EventReportFactory(Event event, LocalDateTime solveTime, Person person) {
        this.event = event;
        this.solveTime = solveTime;
        this.person = person;
    }

    /**
     * Creates new EventReport.
     * @return New EventReport.
     */
    @Override
    public Report createReport() {
        String creationTime = event.getEventDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        String solutionTime = solveTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        String type = event.getEventType().toString();
        String solver = person.getName();
        String creator = event.getCreator().toString();
        return new EventReport(creationTime, solutionTime, type, solver, creator);
    }
}
