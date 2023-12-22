package utils;

import place.Home;
import place.Floor;
import place.Room;
import place.RoomType;

public class HomeBuilder {
    private Home home;
    private JSON specification; // TODO Change type of attribute - it should be JSON file

    public HomeBuilder(JSON specification) {
        this.specification = specification;
    }

    private Home buildHome() { // TODO Correct function - make floors by specification
        Home home = new Home();
        home.addFloor(buildFloor()); // For all floors at home
        return home;
    }

    private Floor buildFloor() { // TODO Correct function - make rooms by specification
        Floor floor = new Floor();
        floor.addRoom(buildRoom(/*Room type from specification*/)); // For all rooms at floor
        return floor;
    }

    private Room buildRoom(RoomType type) { // TODO Correct function
        return new Room(type);
    }

    public Home getHome() {
        buildHome();
        return home;
    }
}
