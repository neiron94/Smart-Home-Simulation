package place;

import utils.Percent;
import java.awt.Color;

public record RoomConfiguration(String name, float temperature, Percent humidity, Percent brightness, Color color) {
}
