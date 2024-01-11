package report;

import creature.person.Person;
import place.Home;
import smarthome.Simulation;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class ReportCreator {
    private static final String REPORT_PATH = String.join(File.separator, System.getProperty("user.dir"), "report") + File.separator; // TODO Find out where to save reports (not to use System.getProperty)

    static {
        File directory = new File(REPORT_PATH); // Create directory
        if (!directory.exists()) if (!directory.mkdirs()); // TODO Handle an error + log info about error of directory making

        Arrays.stream(ReportType.values()).forEach(ReportCreator::createFile); // Create files to write reports there later
    }

    public static void createReports() {
        String date = Simulation.getInstance().getCurrentTime().minusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        createActivityReports(date);
        createConsumptionReports(date);
        createEventReports();
    }

    public static void createConfigurationReport() {
        Report report = new ReportFactory().makeReport(ReportType.CONFIGURATION);
        writeFile(report.getReportType(), report.toString());
    }

    private static void createActivityReports(String date) {
        writeFile(ReportType.ACTIVITY, date);

        Simulation.getInstance().getCreatures().forEach(creature -> {
            Report report = new ReportFactory(creature).makeReport(ReportType.ACTIVITY);
            writeFile(report.getReportType(), report.toString());
            creature.getActivity().getActivities().clear(); // Clear reported activities
            creature.getActivity().getUsage().clear(); // Clear reported usages
        });
    }

    private static void createConsumptionReports(String date) {
        Simulation.getInstance().getDevices().forEach(device -> {
            Report report = new ReportFactory(device).makeReport(ReportType.CONSUMPTION);
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
                    person.getSolvedEvents().entrySet().forEach(event -> {
                        Report report = new ReportFactory(person, event).makeReport(ReportType.EVENT);
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
