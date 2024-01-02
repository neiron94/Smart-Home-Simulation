package creature.pet;

public enum PetType {
    CAT("Cat"),
    DOG("Dog"),
    PARROT("Parrot"),
    FISH("Fish"),
    HAMSTER("Hamster");

    private final String description;

    PetType(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
