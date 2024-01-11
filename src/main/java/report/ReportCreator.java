package report;

import creature.person.Person;
import place.Home;
import report.factory.ActivityReportFactory;
import report.factory.ConfigurationReportFactory;
import report.factory.ConsumptionReportFactory;
import report.factory.EventReportFactory;
import smarthome.Simulation;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * Creates reports using ReportFactory and writes them to files.
 */
public class ReportCreator {
    private static final String REPORT_PATH = String.join(File.separator, System.getProperty("user.dir"), "report") + File.separator;

    static {
        File directory = new File(REPORT_PATH); // Create directory
        if (!directory.exists()) if (!directory.mkdirs()); // TODO Handle an error + log info about error of directory making

        Arrays.stream(ReportType.values()).forEach(ReportCreator::createFile); // Create files to write reports there later
    }

    /**
     * Create all types of reports (except for configuration report) and write them to files.
     * Also resets to zero people's activities and consumed maps in supply systems.
     * Is called in the end of every simulation day.
     */
    public static void createReports() {
        String date = Simulation.getInstance().getCurrentTime().minusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        createActivityReports(date);
        createConsumptionReports(date);
        createEventReports();
    }

    /**
     * Creates and writes to file configuration report. Is called
     * only once when reading from config file.
     */
    public static void createConfigurationReport() {
        Report report = new ConfigurationReportFactory().createReport();
        writeFile(report.getReportType(), report.toString());
    }

    private static void createActivityReports(String date) {
        writeFile(ReportType.ACTIVITY, date);

        Simulation.getInstance().getCreatures().forEach(creature -> {
            Report report = new ActivityReportFactory(creature).createReport();
            writeFile(report.getReportType(), report.toString());
            creature.getActivity().getActivities().clear(); // Clear reported activities
            creature.getActivity().getUsage().clear(); // Clear reported usages
        });
    }

    private static void createConsumptionReports(String date) {
        Simulation.getInstance().getDevices().forEach(device -> {
            Report report = new ConsumptionReportFactory(device).createReport();
            writeFile(report.getReportType(), String.join("\t", date, report.toString()));
        });

        Home home = Simulation.getInstance().getHome();
        home.getGasSupplySystem().restoreConsumptions(); // Clear reported gas consumptions
        home.getWaterSupplySystem().restoreConsumptions(); // Clear reported water consumptions
        home.getElectricitySupplySystem().restoreConsumptions(); // Clear reported electricity consumptions
    }

    private static void createEventReports() {
        Simulation.getInstance().getCreatures().stream()
                .filter(creature -> creature instanceof Person)
                .map(creature -> (Person) creature)
                .forEach(person -> {
                    person.getSolvedEvents().forEach((key, value) -> {
                        Report report = new EventReportFactory(key, value, person).createReport();
                        writeFile(report.getReportType(), report.toString());
                    });
                    person.getSolvedEvents().clear(); // Clear reported events
                });
    }

    private static void createFile(ReportType type) {
        try {
            File file = new File(REPORT_PATH + type.getFileName()); // Delete previous simulation report
            if (file.exists()) if (!file.delete()) return; // TODO Handle an error + log info about error of old report file deletion

            FileWriter writer = new FileWriter(REPORT_PATH + type.getFileName()); // Create new report file

            switch (type) {
                case CONSUMPTION -> writer.write("Date\tDevice\tElectricity\tWater\tGas\tMoney\n"); // Add ConsumptionReport header
                case EVENT -> writer.write("Created\tSolved\tType\tCreator\tSolver\n"); // Add EventReport header
                case CONFIGURATION, ACTIVITY -> writer.write("");
            }

            writer.close();
        } catch (IOException e) {
            // TODO Handle an error
        }
    }

    private static void writeFile(ReportType type, String data) {
        try {
            FileWriter writer = new FileWriter(REPORT_PATH + type.getFileName(), true);
            writer.write(data + '\n');
            writer.close();
        } catch (IOException e) {
            // TODO Handle an error
        }
    }
}
