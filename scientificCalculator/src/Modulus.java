public class Modulus {
    public static int modulus(int a, int b) {
        if(b == 0) {
            throw new IllegalArgumentException("b cannot be zero");
        } else {
            return a % b;
        }
    }
}
