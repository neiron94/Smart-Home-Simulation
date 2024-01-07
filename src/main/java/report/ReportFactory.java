package report;

import consumer.device.Device;
import creature.Action;
import creature.Creature;
import creature.person.Person;
import event.Event;
import place.Room;
import place.Floor;
import place.Home;
import smarthome.Simulation;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReportFactory {
    private Device device;
    private Creature creature;
    private Person person;
    private Map.Entry<Event, LocalDateTime> event;

    public ReportFactory() {}

    public ReportFactory(Creature creature) {
        this.creature = creature;
    }

    public ReportFactory(Device device) {
        this.device = device;
    }

    public ReportFactory(Person person, Map.Entry<Event, LocalDateTime> event) {
        this.person = person;
        this.event = event;
    }

    public Report makeReport(ReportType type) {
        return switch (type) {
            case CONFIGURATION -> makeConfigurationReport();
            case ACTIVITY -> makeActivityReport();
            case CONSUMPTION -> makeConsumptionReport();
            case EVENT -> makeEventReport();
            default -> throw new IllegalArgumentException("Invalid report type!"); // TODO Handle error
        };
    }

    private HouseConfigurationReport makeConfigurationReport() {
        Simulation simulation = Simulation.getInstance();

        Map<String, List<String>> hierarchy = simulation.getHome().getFloors().stream() // Get floors stream
                .collect(Collectors.toMap( // Convert to map (K - Floor name, V - Room hierarchies)
                        Floor::toString, // Get floor name
                        floor -> floor.getRooms().stream() // Get rooms stream
                                .sorted() // Sort rooms in alphabetical order
                                .map(room -> Stream.concat( // Step into room
                                        Stream.of(room.toString()), // Get room name
                                        simulation.getDevices().stream() // Get devices stream
                                                .filter(device -> room == device.getRoom()) // Filter only devices in this room
                                                .sorted() // Sort devices in alphabetical order
                                                .map(device -> "\t\t\t" + device) // Get devices name
                                        ).collect(Collectors.joining("\n")) // Make result string
                                ).toList() // Convert hierarchies to list
                ));

        List<String> creatures = simulation.getCreatures().stream() // Get residents stream
                .map(Objects::toString) // Get resident string representation
                .toList(); // Convert representations to list

        return new HouseConfigurationReport(hierarchy, creatures);
    }

    private ActivityAndUsageReport makeActivityReport() {
        String name = creature.getName();

        List<String> activities = creature.getActivity().getActivities(); // Get activities stream

        List<String> usages = creature.getActivity().getUsage().entrySet().stream() // Get usage stream
                .map(entry -> String.format("%s - %d", entry.getKey().toString(), entry.getValue())) // Get usage string representation stream
                .toList(); // Convert representations to list

        return new ActivityAndUsageReport(name, activities, usages);
    }

    private ConsumptionReport makeConsumptionReport() {
        Home home = Simulation.getInstance().getHome();

        double usedGas = home.getGasSupplySystem().getConsumedMap().entrySet().stream() // Get gas consumers stream
                .filter(entry -> entry.getKey() == device) // Filter this device
                .map(Map.Entry::getValue) // Get consumed gas value
                .findFirst().orElse(0.0);
        double usedWater = home.getWaterSupplySystem().getConsumedMap().entrySet().stream() // Get water consumers stream
                .filter(entry -> entry.getKey() == device) // Filter this device
                .map(Map.Entry::getValue) // Get consumed water value
                .findFirst().orElse(0.0);
        double usedElectricity = home.getElectricitySupplySystem().getConsumedMap().entrySet().stream() // Get electricity consumers stream
                .filter(entry -> entry.getKey() == device) // Filter this device
                .map(Map.Entry::getValue) // Get consumed electricity value
                .findFirst().orElse(0.0);
        double spentMoney = 0; // TODO Make formulae to count money + choose proper class to store Money

        return new ConsumptionReport(device.toString(),
                String.valueOf(usedGas),
                String.valueOf(usedWater),
                String.valueOf(usedElectricity),
                String.valueOf(spentMoney));
    }

    private EventReport makeEventReport() {
        String creationTime = event.getKey().getEventDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        String solutionTime = event.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        String type = event.getKey().getEventType().toString();
        String solver = person.getName();
        String creator = event.getKey().getCreator().toString();
        return new EventReport(creationTime, solutionTime, type, solver, creator);
    }
}
