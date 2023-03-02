public class Division {
    public static double divide(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("b cannot be zero");
        }
        return a/b;
    }
}
