package place;

public class Street implements Location {
    private static Street INSTANCE;

    private double temperature; // TODO Changes in calculateSimulation() function
    private int brightness; // TODO Changes in calculateSimulation() function
    private int humidity; // TODO Changes in calculateSimulation() function

    public synchronized static Street getInstance() {
        if (INSTANCE == null) INSTANCE = new Street();
        return INSTANCE;
    }

    public double getTemperature() {
        return temperature;
    }

    public int getBrightness() {
        return brightness;
    }

    public int getHumidity() {
        return humidity;
    }

    @Override
    public void routine() {
        // TODO Implement street routine
        // TODO street temperature depends on time and month
        // TODO street brightness depends on time
        // TODO street humidity depends on month
    }
}
