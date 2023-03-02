public class SquareRoot {
    public static double squareRoot(double a) {
        if (a < 0){
            throw new IllegalArgumentException("a cannot be negative");
        }
        double result = a;
        double previous;
        do {
            previous = result;
            result = 0.5 * (previous + a / previous);
        } while (previous != result);
        return result;
    }
}