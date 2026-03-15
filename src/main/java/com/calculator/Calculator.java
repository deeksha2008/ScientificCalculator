package com.calculator;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- Scientific Calculator ---");
        while (true) {
            System.out.println("\n1. Square Root");
            System.out.println("2. Factorial");
            System.out.println("3. Natural Log");
            System.out.println("4. Power");
            System.out.println("5. Add");
            System.out.println("6. Subtract");
            System.out.println("7. Multiply");
            System.out.println("8. Divide");
            System.out.println("9. Exit");
            System.out.print("Choose: ");
            int choice = scanner.nextInt();
            if (choice == 9) break;

            System.out.print("Enter number: ");
            double x = scanner.nextDouble();

            if (choice == 1) {
                System.out.println("Result: " + sqrt(x));
            } else if (choice == 2) {
                System.out.println("Result: " + factorial((int) x));
            } else if (choice == 3) {
                System.out.println("Result: " + log(x));
            } else if (choice == 4) {
                System.out.print("Enter power: ");
                double b = scanner.nextDouble();
                System.out.println("Result: " + power(x, b));
            } else if (choice == 5) {
                System.out.print("Enter second number: ");
                double b = scanner.nextDouble();
                System.out.println("Result: " + add(x, b));
            } else if (choice == 6) {
                System.out.print("Enter second number: ");
                double b = scanner.nextDouble();
                System.out.println("Result: " + subtract(x, b));
            } else if (choice == 7) {
                System.out.print("Enter second number: ");
                double b = scanner.nextDouble();
                System.out.println("Result: " + multiply(x, b));
            } else if (choice == 8) {
                System.out.print("Enter second number: ");
                double b = scanner.nextDouble();
                double result = divide(x, b);
                if (Double.isNaN(result)) {
                    System.out.println("Error: Division by zero is not allowed.");
                } else {
                    System.out.println("Result: " + result);
                }
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    // --- Scientific functions ---

    public static double sqrt(double x) {
        if (x < 0) return Double.NaN;
        return Math.sqrt(x);
    }

    public static double factorial(int n) {
        if (n < 0) return Double.NaN;
        double res = 1;
        for (int i = 1; i <= n; i++) res *= i;
        return res;
    }

    public static double log(double x) {
        if (x <= 0) return Double.NaN;
        return Math.log(x);
    }

    public static double power(double x, double b) {
        return Math.pow(x, b);
    }

    // --- Basic arithmetic ---

    public static double add(double a, double b) {
        return a + b;
    }

    public static double subtract(double a, double b) {
        return a - b;
    }

    public static double multiply(double a, double b) {
        return a * b;
    }

    public static double divide(double a, double b) {
        if (b == 0) return Double.NaN;
        return a / b;
    }
}
