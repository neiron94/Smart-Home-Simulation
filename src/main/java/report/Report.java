package report;

import main.Simulation;

import java.util.Date;

public abstract class Report {
    protected final String date;

    public Report() {
        this.date = Simulation.getInstance().getCurrentTime().toString(); // TODO Convert to String in format DD/MM/YYYY
    }

    public abstract void saveReport();
}
