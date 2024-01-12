package creature.person;

/**
 * Family status.
 */
public enum FamilyStatus {
    KID("Kid"),
    ADULT("Adult"),
    ELDER("Elder");

    private final String description;

    FamilyStatus(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
