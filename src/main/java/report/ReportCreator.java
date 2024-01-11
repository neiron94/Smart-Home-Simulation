package report;

import creature.person.Person;
import place.Home;
import report.factory.ActivityReportFactory;
import report.factory.ConfigurationReportFactory;
import report.factory.ConsumptionReportFactory;
import report.factory.EventReportFactory;
import smarthome.Simulation;
import utils.HelpFunctions;
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
        if (!directory.exists()) if (!directory.mkdirs()) HelpFunctions.logger.warn("Can't create 'report' directory. Reports wouldn't be saved");

        Arrays.stream(ReportType.values()).forEach(ReportCreator::createFile); // Create files to write reports there later
    }

    private static boolean configuration = true;
    private static boolean consumption = true;
    private static boolean activity = true;
    private static boolean event = true;

    /**
     * Create all types of reports (except for configuration report) and write them to files.
     * Also resets to zero people's activities and consumed maps in supply systems.
     * Is called in the end of every simulation day.
     */
    public static void createReports() {
        String date = Simulation.getInstance().getCurrentTime().minusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        createConsumptionReports(date);
        createActivityReports(date);
        createEventReports();
    }

    /**
     * Creates and writes to file configuration report. Is called
     * only once when reading from config file.
     */
    public static void createConfigurationReport() {
        Report report = new ConfigurationReportFactory().createReport();
        if (configuration) writeFile(report.getReportType(), report.toString());
        HelpFunctions.logger.info("House Configuration report created");
    }

    private static void createActivityReports(String date) {
        writeFile(ReportType.ACTIVITY, date);

        Simulation.getInstance().getCreatures().forEach(creature -> {
            Report report = new ActivityReportFactory(creature).createReport();
            if (activity) writeFile(report.getReportType(), report.toString());
            creature.getActivity().getActivities().clear(); // Clear reported activities
            creature.getActivity().getUsage().clear(); // Clear reported usages
        });

        HelpFunctions.logger.info("Activity and Usage report created");
    }

    private static void createConsumptionReports(String date) {
        Simulation.getInstance().getDevices().forEach(device -> {
            Report report = new ConsumptionReportFactory(device).createReport();
            if (consumption) writeFile(report.getReportType(), String.join("\t", date, report.toString()));
        });

        Home home = Simulation.getInstance().getHome();
        home.getGasSupplySystem().restoreConsumptions(); // Clear reported gas consumptions
        home.getWaterSupplySystem().restoreConsumptions(); // Clear reported water consumptions
        home.getElectricitySupplySystem().restoreConsumptions(); // Clear reported electricity consumptions

        HelpFunctions.logger.info("Consumption report created");
    }

    private static void createEventReports() {
        Simulation.getInstance().getCreatures().stream()
                .filter(creature -> creature instanceof Person)
                .map(creature -> (Person) creature)
                .forEach(person -> {
                    person.getSolvedEvents().forEach((key, value) -> {
                        Report report = new EventReportFactory(key, value, person).createReport();
                        if (event) writeFile(report.getReportType(), report.toString());
                    });
                    person.getSolvedEvents().clear(); // Clear reported events
                });

        HelpFunctions.logger.info("Event report created");
    }

    private static void createFile(ReportType type) {
        try {
            File file = new File(REPORT_PATH + type.getFileName()); // Delete previous simulation report
            if (file.exists() && !file.delete()) {
                HelpFunctions.logger.warn(String.format("Can't delete old '%s' report file. Report wouldn't be saved", type.getFileName()));
                return;
            }

            FileWriter writer = new FileWriter(REPORT_PATH + type.getFileName()); // Create new report file

            switch (type) {
                case CONSUMPTION -> writer.write("Date\tDevice\tElectricity\tWater\tGas\tMoney\n"); // Add ConsumptionReport header
                case EVENT -> writer.write("Created\tSolved\tType\tCreator\tSolver\n"); // Add EventReport header
                case CONFIGURATION, ACTIVITY -> writer.write("");
            }

            writer.close();
        } catch (IOException e) {
            switch (type) {
                case CONFIGURATION -> configuration = false;
                case CONSUMPTION -> consumption = false;
                case ACTIVITY -> activity = false;
                case EVENT -> event = false;
            }
            HelpFunctions.logger.warn(String.format("Can't write '%s' file. Report wouldn't be saved", type.getFileName()));
        }
    }

    private static void writeFile(ReportType type, String data) {
        try {
            FileWriter writer = new FileWriter(REPORT_PATH + type.getFileName(), true);
            writer.write(data + '\n');
            writer.close();
        } catch (IOException e) {
            HelpFunctions.logger.warn(String.format("Can't write '%s' file. Report wouldn't be saved", type.getFileName()));
        }
    }
}
