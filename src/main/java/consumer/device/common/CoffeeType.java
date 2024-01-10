package consumer.device.common;

import utils.Constants.Coffee;

import java.util.Random;

/**
 * Types of coffee. Stores information of required amount of coffee, milk and water.
 */
public enum CoffeeType {
    ESPRESSO("Espresso", Coffee.ESPRESSO_MILK, Coffee.ESPRESSO_WATER, Coffee.COFFEE),
    AMERICANO("Americano", Coffee.AMERICANO_MILK, Coffee.AMERICANO_WATER, Coffee.COFFEE),
    CAPPUCCINO("Cappuccino", Coffee.CAPPUCCINO_MILK, Coffee.CAPPUCCINO_WATER, Coffee.COFFEE),
    MOCHACCINO("Mochaccino", Coffee.MOCHACCINO_MILK, Coffee.MOCHACCINO_WATER, Coffee.COFFEE),
    LATTE("Latte", Coffee.LATTE_MILK, Coffee.LATTE_WATER, Coffee.COFFEE),
    IRISH_CREAM("Irish cream", Coffee.IRISH_CREAM_MILK, Coffee.IRISH_CREAM_WATER, Coffee.COFFEE),
    BLACK_COFFEE("Black coffee", Coffee.BLACK_COFFEE_MILK, Coffee.BLACK_COFFEE_WATER, Coffee.COFFEE);

    private final String name;
    private final int milk;
    private final int water;
    private final int coffee;

    CoffeeType(String name, int milk, int water, int coffee) {
        this.name = name;
        this.milk = milk;
        this.water = water;
        this.coffee = coffee;
    }

    public static CoffeeType getRandomType() {
        return CoffeeType.values()[new Random().nextInt(0, CoffeeType.values().length)];
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

    public String toString() {
        return name;
    }
}
