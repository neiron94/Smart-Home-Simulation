package consumer.device.common;

public enum CoffeeType {
    ESPRESSO(0,0,0),    // TODO - set values
    AMERICANO(0,0,0),
    CAPPUCCINO(0,0,0),
    MOCCACCINO(0,0,0),
    LATTE(0,0,0),
    IRISH_CREAM(0,0,0),
    BLACK_COFFEE(0,0,0),
    HOT_CHOCOLATE(0,0,0);

    private final int milk;
    private final int water;
    private final int coffee;

    CoffeeType(int milk, int water, int coffee) {
        this.milk = milk;
        this.water = water;
        this.coffee = coffee;
    }

    public int getMilk() {
        return milk;
    }

    public int getWater() {
        return water;
    }

    public int getCoffee() {
        return coffee;
    }
}
