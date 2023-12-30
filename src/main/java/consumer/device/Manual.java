package consumer.device;

import smarthome.Simulation;
import java.time.LocalDateTime;

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

    public void read() {
        // TODO - return text?, should take some time depending on repair difficulty
    }
}