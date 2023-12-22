package report;

import java.util.Date;

public abstract class Report {
    protected final String date;

    public Report() {
        this.date = Simulation.getInstance().getDate().; // TODO Convert to String in format DD/MM/YYYY
    }

    public abstract void saveReport();
}
