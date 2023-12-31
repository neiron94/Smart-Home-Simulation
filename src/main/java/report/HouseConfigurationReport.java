package report;

import smarthome.Simulation;

import java.util.stream.Collectors;
import java.util.stream.Stream;


public class HouseConfigurationReport extends Report {
    private final String hierarchy;
    private final String residents;

    public HouseConfigurationReport(String hierarchy, String residents) {
        super(ReportType.CONFIGURATION);
        this.hierarchy = hierarchy;
        this.residents = residents;
    }

    public String getHierarchy() {
        return hierarchy;
    }

    public String getResidents() {
        return residents;
    }
}
