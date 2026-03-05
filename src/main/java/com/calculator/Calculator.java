package com.calculator;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- Scientific Calculator ---");
        while (true) {
            System.out.println("\n1. Square Root\n2. Factorial\n3. Natural Log\n4. Power\n5. Exit");
            System.out.print("Choose: ");
            int choice = scanner.nextInt();
            if (choice == 5) break;
            
            System.out.print("Enter number: ");
            double x = scanner.nextDouble();
            
            if (choice == 1) System.out.println("Result: " + sqrt(x));
            else if (choice == 2) System.out.println("Result: " + factorial((int)x));
            else if (choice == 3) System.out.println("Result: " + log(x));
            else if (choice == 4) {
                System.out.print("Enter power: ");
                double b = scanner.nextDouble();
                System.out.println("Result: " + power(x, b));
            }
        }
        scanner.close();
    }

    // --- Logic Methods (Testable by JUnit) ---

    public static double factorial(int n) {
        if (n < 0) return Double.NaN;
        double res = 1;
        for (int i = 1; i <= n; i++) res *= i;
        return res;
    }

    public static double sqrt(double x) {
        return Math.sqrt(x);
    }

    public static double log(double x) {
        return Math.log(x);
    }

    public static double power(double x, double b) {
        return Math.pow(x, b);
    }
}
