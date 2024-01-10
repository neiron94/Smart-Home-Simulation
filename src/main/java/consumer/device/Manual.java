package consumer.device;

import smarthome.Simulation;
import java.time.LocalDateTime;

/**
 * Stores information that you need when device is broken.
 */
public class Manual {
    private final LocalDateTime guaranteeExpirationDate;
    private final String text;
    private final RepairDifficulty difficulty;

    public Manual(DeviceType type) {
        this.guaranteeExpirationDate = Simulation.getInstance().getCurrentTime().plus(type.getGuarantee());
        this.text = String.format("You are reading %s manual. Difficulty of repair is %s.", type.getName(), type.getDifficulty());
        this.difficulty = type.getDifficulty();
    }

    public LocalDateTime getGuaranteeExpirationDate() {
        return guaranteeExpirationDate;
    }

    public String getText() {
        return text;
    }

    public RepairDifficulty getDifficulty() {
        return difficulty;
    }

    public void read() {}
}