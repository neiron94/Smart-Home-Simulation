package report;

import creature.person.Person;
import place.Home;
import smarthome.Simulation;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class ReportCreator {
    private static final String REPORT_PATH = System.getProperty("user.dir") + "/report/"; // TODO Find out where to save reports (not to use System.getProperty)

    static {
        Arrays.stream(ReportType.values()).forEach(ReportCreator::createFile); // Create files to write reports there later
    }

    public static void createReports() {
        createActivityReports();
        createConsumptionReports();
        createEventReports();
    }

    public static void createConfigurationReport() {
        HouseConfigurationReport report = (HouseConfigurationReport) new ReportFactory().makeReport(ReportType.CONFIGURATION);

        writeFile(ReportType.CONFIGURATION, "Home");
        report.getHierarchy().forEach((key, value) -> {
            String data = value.stream().reduce(key, (result, room) -> result + ("\n\t\t" + room));
            writeFile(ReportType.CONFIGURATION, '\t' + data);
        });

        writeFile(ReportType.CONFIGURATION, "Residents");
        report.getResidents().forEach(resident -> writeFile(ReportType.CONFIGURATION, '\t' + resident));
    }

    private static void createActivityReports() {
        writeFile(ReportType.ACTIVITY, Simulation.getInstance().getCurrentTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        Simulation.getInstance().getResidents().forEach(resident -> {
            ActivityAndUsageReport report = (ActivityAndUsageReport) new ReportFactory(resident).makeReport(ReportType.ACTIVITY);
            String creature = '\t' + report.getCreature();
            String activities = report.getActivities().stream().reduce("\t\tActivity", (result, activity) -> result + ("\n\t\t\t" + activity));
            String usages = report.getUsages().stream().reduce("\t\tUsage", (result, usage) -> result + ("\n\t\t\t" + usage));
            writeFile(ReportType.ACTIVITY, String.join("\n", creature, activities, usages));
            resident.setActivity(null); // Clear reported activity // TODO Maybe smth better than null?
        });
    }

    private static void createConsumptionReports() {
        String date = Simulation.getInstance().getCurrentTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Simulation.getInstance().getDevices().forEach(device -> {
            ConsumptionReport report = (ConsumptionReport) new ReportFactory(device).makeReport(ReportType.CONSUMPTION);
            String consumer = report.getDevice();
            String gas = report.getUsedGas();
            String water = report.getUsedWater();
            String electricity = report.getUsedElectricity();
            String money = report.getSpentMoney();
            writeFile(ReportType.CONSUMPTION, String.join("\t", date, consumer, gas, water, electricity, money));
        });

        Home home = Simulation.getInstance().getHome();
        home.getGasSupplySystem().restoreConsumptions(); // Clear reported gas consumption
        home.getWaterSupplySystem().restoreConsumptions(); // Clear reported water consumption
        home.getElectricitySupplySystem().restoreConsumptions(); // Clear reported electricity consumption
    }

    private static void createEventReports() {
        Simulation.getInstance().getResidents().stream()
                .filter(creature -> creature instanceof Person)
                .map(creature -> (Person) creature)
                .forEach(person -> {
                    person.getSolvedEvents().entrySet().forEach(event -> {
                        EventReport report = (EventReport) new ReportFactory(person, event).makeReport(ReportType.EVENT);
                        String creationTime = report.getCreationTime();
                        String solutionTime = report.getSolutionTime();
                        String type = report.getType();
                        String solver = report.getSolver();
                        String creator = report.getCreator();
                        writeFile(ReportType.EVENT, String.join("\t", creationTime, solutionTime, type, solver, creator));
                    });
                    person.getSolvedEvents().clear(); // Clear reported events
                });
    }

    private static void createFile(ReportType type) {
        try {
            new File(REPORT_PATH + type.getFileName()).delete(); // Delete previous simulation report
            FileWriter file = new FileWriter(REPORT_PATH + type.getFileName()); // Create new report file

            if (type == ReportType.CONSUMPTION) file.write("Date\tDevice\tElectricity\tWater\tGas\tMoney" + '\n'); // Add ConsumptionReport header
            if (type == ReportType.EVENT) file.write("Created\tSolved\tType\tCreator\tSolver" + '\n'); // Add EventReport header

            file.close();
        } catch (IOException e) {
            // TODO Handle an error
        }
    }

    private static void writeFile(ReportType type, String data) {
        try {
            FileWriter file = new FileWriter(REPORT_PATH + type.getFileName());
            file.write(data + '\n');
            file.close();
        } catch (IOException e) {
            // TODO Handle an error
        }
    }
}
