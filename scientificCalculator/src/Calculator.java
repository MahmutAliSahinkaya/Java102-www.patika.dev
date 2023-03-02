import java.util.Scanner;

public class Calculator {
    public static void run() {
        System.out.println("Calculator is running...");
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        boolean isContinue;
        do {
            System.out.println("1. Addition");
            System.out.println("2. Subtraction");
            System.out.println("3. Multiplication");
            System.out.println("4. Division");
            System.out.println("5. Exponentiation");
            System.out.println("6. Square Root");
            System.out.println("7. Derivative");
            System.out.println("8. Integral");
            System.out.println("9. Factorial");
            System.out.println("10. Modulus");
            System.out.println("11. Absolute Value");
            System.out.println("0. Exit");
            System.out.print("Choose an operation: ");
            choice = scanner.nextInt();
            isContinue = true;
            switch (choice) {
                case 1 -> {
                    System.out.print("First number: ");
                    double number1 = scanner.nextDouble();
                    System.out.print("Second number: ");
                    double number2 = scanner.nextDouble();
                    System.out.println("Result: " + Addition.add(number1, number2));
                }
                case 2 -> {
                    System.out.print("First number: ");
                    double number3 = scanner.nextDouble();
                    System.out.print("Second number: ");
                    double number4 = scanner.nextDouble();
                    System.out.println("Result: " + Subtraction.subtract(number3, number4));
                }
                case 3 -> {
                    System.out.print("First number: ");
                    double number5 = scanner.nextDouble();
                    System.out.print("Second number: ");
                    double number6 = scanner.nextDouble();
                    System.out.println("Result: " + Multiplication.multiply(number5, number6));
                }
                case 4 -> {
                    System.out.print("First number: ");
                    double number7 = scanner.nextDouble();
                    System.out.print("Second number: ");
                    double number8 = scanner.nextDouble();
                    System.out.println("Result: " + Division.divide(number7, number8));
                }
                case 5 -> {
                    System.out.print("Base: ");
                    double base = scanner.nextDouble();
                    System.out.print("Exponent: ");
                    double exponent = scanner.nextDouble();
                    System.out.println("Result: " + Exponentiation.power(base, exponent));
                }
                case 6 -> {
                    System.out.print("Number: ");
                    double number9 = scanner.nextDouble();
                    System.out.println("Result: " + SquareRoot.squareRoot(number9));
                }
                case 7 -> {
                    System.out.print("Degree of equation: ");
                    int degree = scanner.nextInt();
                    double[] coefficients = new double[degree];
                    System.out.println("Enter the coefficients:");
                    for (int i = 0; i < degree; i++) {
                        System.out.print(i + ". coefficient: ");
                        coefficients[i] = scanner.nextDouble();
                    }
                    System.out.print("X value: ");
                    double x = scanner.nextDouble();
                    System.out.println("Result: " + Derivative.derivative(coefficients, degree, x));
                }
                case 8 -> {
                    System.out.print("Degree of equation: ");
                    int degree2 = scanner.nextInt();
                    double[] coefficients2 = new double[degree2];
                    System.out.println("Enter the coefficients:");
                    for (int i = 0; i < degree2; i++) {
                        System.out.print(i + ". coefficient: ");
                        coefficients2[i] = scanner.nextDouble();
                    }
                    System.out.print("Lower bound: ");
                    double lowerBound = scanner.nextDouble();
                    System.out.print("Upper bound: ");
                    double upperBound = scanner.nextDouble();
                    System.out.println("Result: " + Integral.integrate(coefficients2, degree2, lowerBound, upperBound));
                }
                case 9 -> {
                    System.out.print("Number: ");
                    int number10 = scanner.nextInt();
                    System.out.println("Result: " + Factorial.factorial(number10));
                }
                case 10 -> {
                    System.out.print("First number: ");
                    int number11 = scanner.nextInt();
                    System.out.print("Second number: ");
                    int number12 = scanner.nextInt();
                    System.out.println("Result: " + Modulus.modulus(number11, number12));
                }
                case 11 -> {
                    System.out.print("Number: ");
                    double number13 = scanner.nextDouble();
                    System.out.println("Result: " + AbsoluteValue.absoluteValue(number13));
                }
                case 0 -> {
                    isContinue = false;
                    System.out.println("Exiting...");
                }
                default -> System.out.println("Invalid operation selected.");
            }
        } while (isContinue);
    }
}


