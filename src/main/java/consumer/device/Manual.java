package consumer.device;

import smarthome.Simulation;
import java.util.Date;

public class Manual {
    private final Date guaranteeExpirationDate;
    private final String text;
    private final RepairDifficulty difficulty;

    public Manual(DeviceType type) {
        this.guaranteeExpirationDate = Simulation.getInstance().getCurrentTime() + type.getGuarantee(); // TODO Calculate guarantee expiration date properly
        this.text = type.getManualText();
        this.difficulty = type.getDifficulty();
    }

    public Date getGuaranteeExpirationDate() {
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