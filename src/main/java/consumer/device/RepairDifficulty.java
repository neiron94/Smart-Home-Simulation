/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package consumer.device;

/**
 * Difficulty of device repairing.
 */
public enum RepairDifficulty {
    EASY("easy", 300),
    MEDIUM("medium", 600),
    HARD("hard", 900);

    private final String description;
    private final int repairTime;

    RepairDifficulty(String description, int repairTime) {
        this.description = description;
        this.repairTime = repairTime;
    }

    public int getRepairTime() {
        return repairTime;
    }

    @Override
    public String toString() {
        return description;
    }
}
