package place;

import com.fasterxml.jackson.databind.JsonNode;
import utils.ConfigurationReader;
import utils.HelpFunctions;
import java.util.Optional;

public class HomeBuilder {
    private final JsonNode homeDraft;
    private final JsonNode floorDraft;
    private final JsonNode roomDraft;
    private Home home;

    public HomeBuilder(String config) {
        JsonNode draft = ConfigurationReader.readHomeConfig(config);
        homeDraft = draft.path("HOME");
        floorDraft = draft.path("FLOOR");
        roomDraft = draft.path("ROOM");
        reset();
    }

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

    public void reset() {
        home = new Home();
    }

    public Home getHome() {
        buildHome();
        return this.home;
    }
}
