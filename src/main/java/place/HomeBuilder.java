package place;

import com.fasterxml.jackson.databind.JsonNode;
import utils.ConfigurationReader;

/**
 * // TODO - Javadoc
 */
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
//        System.out.println("Created Home instance"); // TODO May be logged
    }

    private Floor buildFloor(int id) {
        Floor floor = new Floor(id);
        for (int i = 0; i < floorDraft.get(id-1).size(); ++i) floor.addRoom(buildRoom(floorDraft.get(id-1).get(i).asInt(), floor));
//        System.out.println("Created Floor instance: " + floor); // TODO May be logged
        return floor;
    }

    private Room buildRoom(int id, Floor floor) {
        RoomType type = RoomType.valueOf(roomDraft.get(id-1).asText());
        Room room = new Room(id, type, floor);
//        System.out.println("Created Room instance: " + room); // TODO May be logged
        return room;
    }

    public void reset() {
        home = new Home();
    }

    public Home getHome() {
        buildHome();
        return this.home;
    }
}
