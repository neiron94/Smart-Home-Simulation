package utils;

public class Percent extends Number {
    private int value;

    public Percent(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        // TODO Is it better to throw an exception if value out of range ?
        this.value = Math.min(100, Math.max(0, value)); // Sets 100 if value > 100, sets 0 if value < 0
    }

    public void increment() {
        // TODO Is it better to throw an exception if value out of range ?
        this.value = Math.min(100, ++this.value); // Sets 100 if value > 100
    }

    public void increment(int value) {
        // TODO Is it better to throw an exception if value out of range ?
        this.value = Math.min(100, this.value + value); // Sets 100 if value > 100
    }

    public void decrement() {
        // TODO Is it better to throw an exception if value out of range ?
        this.value = Math.max(0, --this.value); // Sets 0 if value < 0
    }

    public void decrement(int value) {
        // TODO Is it better to throw an exception if value out of range ?
        this.value = Math.max(0, this.value - value); // Sets 0 if value < 0
    }

    public void setMax() {
        this.value = 100;
    }

    public void setMin() {
        this.value = 0;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public int intValue() {
        return value;
    }

    @Override
    public long longValue() {
        return value;
    }

    @Override
    public float floatValue() {
        return value;
    }

    @Override
    public double doubleValue() {
        return value;
    }
}
