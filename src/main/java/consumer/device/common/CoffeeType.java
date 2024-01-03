package consumer.device.common;

import utils.Constants.Coffee;

public enum CoffeeType {
    ESPRESSO("Espresso", Coffee.ESPRESSO_MILK, Coffee.ESPRESSO_WATER, Coffee.COFFEE),
    AMERICANO("Americano", Coffee.AMERICANO_MILK, Coffee.AMERICANO_WATER, Coffee.COFFEE),
    CAPPUCCINO("Cappuccino", Coffee.CAPPUCCINO_MILK, Coffee.CAPPUCCINO_WATER, Coffee.COFFEE),
    MOCHACCINO("Mochaccino", Coffee.MOCHACCINO_MILK, Coffee.MOCHACCINO_WATER, Coffee.COFFEE),
    LATTE("Latte", Coffee.LATTE_MILK, Coffee.LATTE_WATER, Coffee.COFFEE),
    IRISH_CREAM("Irish cream", Coffee.IRISH_CREAM_MILK, Coffee.IRISH_CREAM_WATER, Coffee.COFFEE),
    BLACK_COFFEE("Black coffee", Coffee.BLACK_COFFEE_MILK, Coffee.BLACK_COFFEE_WATER, Coffee.COFFEE);

    private final String description;
    private final int milk;
    private final int water;
    private final int coffee;

    CoffeeType(String description, int milk, int water, int coffee) {
        this.description = description;
        this.milk = milk;
        this.water = water;
        this.coffee = coffee;
    }

    public String getDescription() {
        return description;
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
