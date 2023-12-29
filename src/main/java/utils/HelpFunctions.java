package utils;

public class HelpFunctions {
    public static int adjustPercent(int value) {
        return value < 0 ? 0 : Math.min(value, 100);
    }
}
