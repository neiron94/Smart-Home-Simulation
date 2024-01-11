package report;

/**
 * Report containing information about solved events.
 */
public class EventReport extends Report {
    private final String creationTime;
    private final String solutionTime;
    private final String type;
    private final String solver;
    private final String creator;

    public EventReport(String creationTime, String solutionTime, String type, String creator, String solver) {
        super(ReportType.EVENT);
        this.creationTime = creationTime;
        this.solutionTime = solutionTime;
        this.type = type;
        this.creator = creator;
        this.solver = solver;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public String getSolutionTime() {
        return solutionTime;
    }

    public String getType() {
        return type;
    }

    public String getSolver() {
        return solver;
    }

    public String getCreator() {
        return creator;
    }

    @Override
    public String toString() {
        return creationTime + '\t' +
                solutionTime + '\t' +
                type + '\t' +
                solver + '\t' +
                creator;
    }
}
