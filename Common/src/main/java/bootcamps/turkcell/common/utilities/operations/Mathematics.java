package bootcamps.turkcell.common.utilities.operations;

public class Mathematics {
    public static double percentOf(double value, double percentage) {
        return (value * percentage) / 100;
    }

    public static double addPercentOf(double value, double percentage) {
        return value + percentOf(value, percentage);
    }
}
