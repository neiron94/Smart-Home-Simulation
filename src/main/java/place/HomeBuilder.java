/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package place;

import com.fasterxml.jackson.databind.JsonNode;
import utils.ConfigurationReader;
import utils.HelpFunctions;
import java.util.Optional;

/**
 * Builder for creating Home.
 */
public class HomeBuilder {
    private final JsonNode homeDraft;
    private final JsonNode floorDraft;
    private final JsonNode roomDraft;
    private Home home;

    /**
     * Creates new factory.
     * @param config name of configuration to take info from
     */
    public HomeBuilder(String config) {
        JsonNode draft = ConfigurationReader.readHomeConfig(config);
        homeDraft = draft.path("HOME");
        floorDraft = draft.path("FLOOR");
        roomDraft = draft.path("ROOM");
        reset();
    }

    /**
     * Builds new Home.
     */
    public void buildHome() {
        for (int i = 0; i < homeDraft.size(); ++i) home.addFloor(buildFloor(homeDraft.get(i).asInt()));
        HelpFunctions.logger.info(String.format("%s was built", home));
    }

    private Floor buildFloor(int id) {
        Floor floor = new Floor(id);
        for (int i = 0; i < floorDraft.get(id-1).size(); ++i) buildRoom(floorDraft.get(id-1).get(i).asInt(), floor).ifPresent(floor::addRoom);
        HelpFunctions.logger.info(String.format("%s was built", floor));
        return floor;
    }

    private Optional<Room> buildRoom(int id, Floor floor) {
        try {
            RoomType type = RoomType.valueOf(roomDraft.get(id - 1).asText());
            Room room = new Room(id, type, floor);
            HelpFunctions.logger.info(String.format("%s was built", room));
            return Optional.of(room);
        } catch (IllegalArgumentException e) {
            HelpFunctions.logger.warn(String.format("Invalid room type '%s'. Room_%d wasn't created", roomDraft.get(id - 1), id));
            return Optional.empty();
        }
    }

    /**
     * Restores builder product
     */
    public void reset() {
        home = new Home();
    }

    /**
     * Builds new Home.
     * @return built Home
     */
    public Home getHome() {
        buildHome();
        return this.home;
    }
}
