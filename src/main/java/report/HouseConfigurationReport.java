package report;

import smarthome.Simulation;

import java.util.stream.Collectors;
import java.util.stream.Stream;


public class HouseConfigurationReport extends Report {
    private final String hierarchy;
    private final String residents;

    private HouseConfigurationReport(String hierarchy, String residents) {
        super();
        this.hierarchy = hierarchy;
        this.residents = residents;
    }

    public static HouseConfigurationReport makeReport() { // Factory method
        String hierarchyString = Simulation.getInstance().getHome().getFloors().stream() // Get floors stream
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

        String residentsString = Simulation.getInstance().getResidents().stream() // Get creatures stream
                                    .map(resident -> "\t" + resident.getName()) // Get creature names stream
                                    .collect(Collectors.joining("\n")); // Make result string

        return new HouseConfigurationReport(hierarchyString, residentsString);
    }

    @Override
    public void saveReport() {
        // TODO Path to .txt file by default

        // TODO Save to .txt
    }
}
