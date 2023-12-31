package report;

import consumer.device.Device;
import creature.Creature;
import creature.person.Person;
import event.Event;
import place.Home;
import smarthome.Simulation;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReportFactory {
    private Home home;
    private Event event;
    private Person person;
    private Device device;
    private Creature creature;
    private Simulation simulation;

    public ReportFactory(Simulation simulation) {
        this.simulation = simulation;
    }

    public ReportFactory(Creature creature) {
        this.creature = creature;
    }

    public ReportFactory(Home home) {
        this.home = home;
    }

    public ReportFactory(Home home, Device device) {
        this.home = home;
        this.device = device;
    }

    public ReportFactory(Person person, Event event) {
        this.person = person;
        this.event = event;
    }

    @SuppressWarnings("unchecked")
    public <T extends Report> T makeReport(ReportType type) { // TODO Change report return type
        return switch (type) {
            case CONFIGURATION -> (T) makeConfigurationReport();
            case ACTIVITY -> (T) makeActivityReport();
            case CONSUMPTION -> device == null ? (T) makeHomeConsumptionReport() : (T) makeDeviceConsumptionReport();
            case EVENT -> (T) makeEventReport();
            default -> throw new IllegalArgumentException("Invalid report type!"); // TODO Handle error
        };
    }

    private HouseConfigurationReport makeConfigurationReport() {
        String hierarchy = simulation.getHome().getFloors().stream() // Get floors stream
                .flatMap(floor -> Stream.concat( // Step into floor
                        Stream.of("\t" + floor.toString()), // Get floor name
                        floor.getRooms().stream() // Get rooms stream
                                .flatMap(room -> Stream.concat( // Step into room
                                        Stream.of("\t\t" + room.toString()), // Get room name
                                        Simulation.getInstance().getDevices().stream() // Get devices stream
                                                .filter(device -> room == device.getRoom()) // Filter only devices in this room
                                                .map(device -> "\t\t\t" + device.toString())) // Get devices name
                                ))
                ).collect(Collectors.joining("\n")); // Make result string

        String residents = simulation.getResidents().stream() // Get creatures stream
                .map(resident -> "\t" + resident.getName()) // Get creature names stream
                .collect(Collectors.joining("\n")); // Make result string

        return new HouseConfigurationReport(hierarchy, residents);
    }

    private ActivityAndUsageReport makeActivityReport() {
        String name = creature.getName();

        String activity = creature.getActivity().getActivities().stream() // Get activities stream
                .map(action -> "\t" + action.getDescription()) // Get activity string representation stream
                .collect(Collectors.joining("\n")); // Make result string

        String usage = creature.getActivity().getUsage().entrySet().stream() // Get usage stream
                .map(entry -> "\t" + entry.getKey().toString() + " " + entry.getValue().toString()) // Get usage string representation stream
                .collect(Collectors.joining("\n")); // Make result string

        return new ActivityAndUsageReport(name, activity, usage);
    }

    private ConsumptionReport makeDeviceConsumptionReport() {
        double usedGas = home.getGasSupplySystem().getConsumedMap().entrySet().stream() // Get gas consumers stream
                .filter(entry -> entry.getKey() == device) // Filter this device
                .map(Map.Entry::getValue) // Get consumed gas value
                .findFirst().orElse(0.0);  // TODO Or throw error?
        double usedWater = home.getWaterSupplySystem().getConsumedMap().entrySet().stream() // Get water consumers stream
                .filter(entry -> entry.getKey() == device) // Filter this device
                .map(Map.Entry::getValue) // Get consumed water value
                .findFirst().orElse(0.0);  // TODO Or throw error?
        double usedElectricity = home.getElectricitySupplySystem().getConsumedMap().entrySet().stream() // Get electricity consumers stream
                .filter(entry -> entry.getKey() == device) // Filter this device
                .map(Map.Entry::getValue) // Get consumed electricity value
                .findFirst().orElse(0.0);  // TODO Or throw error?
        int spentMoney = 0; // TODO Make formulae to count money + choose proper class to store Money

        return new DeviceConsumptionReport(String.valueOf(usedGas),
                String.valueOf(usedWater),
                String.valueOf(usedElectricity),
                String.valueOf(spentMoney),
                device.toString());
    }

    private ConsumptionReport makeHomeConsumptionReport() {
        double usedGas = home.getGasSupplySystem().getConsumedMap().values().stream() // Get gas consumers stream
                .mapToDouble(v -> v) // Get values stream
                .sum(); // Sum overall consumed gas
        double usedWater = home.getWaterSupplySystem().getConsumedMap().values().stream() // Get water consumers stream
                .mapToDouble(v -> v) // Get values stream
                .sum(); // Sum overall consumed water
        double usedElectricity = home.getElectricitySupplySystem().getConsumedMap().values().stream() // Get electricity consumers stream
                .mapToDouble(v -> v) // Get values stream
                .sum(); // Sum overall consumed electricity
        int spentMoney = 0; // TODO Make formulae to count money + choose proper class to store Money

        return new ConsumptionReport(String.valueOf(usedGas),
                String.valueOf(usedWater),
                String.valueOf(usedElectricity),
                String.valueOf(spentMoney));
    }

    private EventReport makeEventReport() {
        String solver = person.getName();
        String creator = event.getCreator().toString();
        String type = event.getEventType().toString();
        return new EventReport(solver, creator, type);
    }
}
