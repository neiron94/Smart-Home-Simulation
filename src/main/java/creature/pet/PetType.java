package creature.pet;

public enum PetType {
    CAT("Cat"),
    DOG("Dog"),
    RACOON("Racoon"),
    MINI_PIG("Mini pig");

    private final String description;

    PetType(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
