package creature.pet;

public enum PetType {
    CAT("Cat"),
    DOG("Dog"),
    PARROT("Parrot"),
    FISH("Fish"),
    HAMSTER("Hamster");

    private final String type;

    PetType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
