public class Integral {
    public static double integrate(double [] coefficients, int degree, double lowerLimit, double upperLimit) {
        double result = 0;
        double step = 0.0001;
        for (double i = lowerLimit; i < upperLimit; i+=step) {
            double value = 0;
            for (int j = 0; j < degree; j++) {
                value += coefficients[j] * Exponentiation.power(i, degree-j-1);
            }
            result += value * step;
        }
        return result;
    }
}
