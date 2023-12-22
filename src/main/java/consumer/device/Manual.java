package consumer.device;

import java.util.Date;

public record Manual(Date guaranteeExpirationDate, String text, RepairDifficulty difficulty) {

    public void read() {
        // TODO - return text?, should take some time depending on repair difficulty
    }
}
