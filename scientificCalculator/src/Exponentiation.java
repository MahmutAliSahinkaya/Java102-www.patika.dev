public class Exponentiation {
    public static double power(double a, double b) {
        double result = 1.0;
        if (b >= 0) {
            for (int i = 0; i < b; i++) {
                result *= a;
            }
        } else {
            for (int i = 0; i > b; i--) {
                result /= a;
            }
        }
        return result;
    }
}
