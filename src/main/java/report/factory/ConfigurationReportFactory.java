/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package report.factory;

import report.HouseConfigurationReport;
import report.Report;
import smarthome.Simulation;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import place.Room;
import place.Floor;
import consumer.device.Device;

/**
 * Class for creating HouseConfigurationReport.
 */
public class ConfigurationReportFactory implements ReportFactory {

    /**
     * Creates new HouseConfigurationReport.
     * @return New HouseConfigurationReport.
     */
    @Override
    public Report createReport() {
        Simulation simulation = Simulation.getInstance();

        Map<String, List<String>> hierarchy = simulation.getHome().getFloors().stream() // Get floors stream
                .collect(Collectors.toMap( // Convert to map (K - Floor name, V - Room hierarchies)
                        Floor::toString, // Get floor name
                        floor -> floor.getRooms().stream() // Get rooms stream
                                .sorted(Comparator.comparing(Room::getId))
                                .map(room -> Stream.concat( // Step into room
                                                Stream.of(room.toString()), // Get room name
                                                simulation.getDevices().stream() // Get devices stream
                                                        .filter(device -> room == device.getRoom()) // Filter only devices in this room
                                                        .sorted(Comparator.comparing(Device::getId))
                                                        .map(device -> "\t\t\t" + device) // Get devices name
                                        ).collect(Collectors.joining("\n")) // Make result string
                                ).toList() // Convert hierarchies to list
                ));

        List<String> creatures = simulation.getCreatures().stream() // Get residents stream
                .map(Objects::toString) // Get resident string representation
                .toList(); // Convert representations to list

        return new HouseConfigurationReport(hierarchy, creatures);
    }
}
