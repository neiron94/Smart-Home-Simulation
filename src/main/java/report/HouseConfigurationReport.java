package report;

import java.util.List;
import java.util.Map;

public class HouseConfigurationReport extends Report {
    private final Map<String, List<String>> hierarchy;
    private final List<String> residents;

    public HouseConfigurationReport(Map<String, List<String>> hierarchy, List<String> residents) {
        super(ReportType.CONFIGURATION);
        this.hierarchy = hierarchy;
        this.residents = residents;
    }

    public Map<String, List<String>> getHierarchy() {
        return hierarchy;
    }

    public List<String> getResidents() {
        return residents;
    }
}
