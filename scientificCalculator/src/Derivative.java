public class Derivative {
    public static double derivative(double[] coefficients, int degree, double x) {
        double result = 0;
        for (int i = 0; i < degree; i++) {
            result += (degree - i) * coefficients[i] * Exponentiation.power(x, degree - i - 1);
        }
        return result;
    }

}