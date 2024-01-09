package consumer.device;

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
