package consumer.device.common;

import utils.Constants.Coffee;

public enum CoffeeType {
    ESPRESSO(Coffee.ESPRESSO_MILK, Coffee.ESPRESSO_WATER, Coffee.COFFEE),
    AMERICANO(Coffee.AMERICANO_MILK, Coffee.AMERICANO_WATER, Coffee.COFFEE),
    CAPPUCCINO(Coffee.CAPPUCCINO_MILK, Coffee.CAPPUCCINO_WATER, Coffee.COFFEE),
    MOCHACCINO(Coffee.MOCHACCINO_MILK, Coffee.MOCHACCINO_WATER, Coffee.COFFEE),
    LATTE(Coffee.LATTE_MILK, Coffee.LATTE_WATER, Coffee.COFFEE),
    IRISH_CREAM(Coffee.IRISH_CREAM_MILK, Coffee.IRISH_CREAM_WATER, Coffee.COFFEE),
    BLACK_COFFEE(Coffee.BLACK_COFFEE_MILK, Coffee.BLACK_COFFEE_WATER, Coffee.COFFEE);

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
