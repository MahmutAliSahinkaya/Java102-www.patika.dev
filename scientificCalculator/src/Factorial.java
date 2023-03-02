public class Factorial {
    public static int factorial(int n) {
        if(n<0){
            throw new IllegalArgumentException("n cannot be negative");
        }
        if (n == 0) {
            return 1;
        }
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
