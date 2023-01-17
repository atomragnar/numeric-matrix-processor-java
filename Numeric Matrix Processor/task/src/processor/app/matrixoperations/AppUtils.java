package processor.app.matrixoperations;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class AppUtils {

    public static Matrix readFirstMatScanner(Scanner scanner) {
        try {
            System.out.print("Enter size of first matrix: ");
            int rows = scanner.nextInt();
            int columns = scanner.nextInt();
            Matrix firstMatrix = new Matrix(rows, columns);
            System.out.println();
            System.out.println("Enter first matrix");
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    double value = scanner.nextDouble();
                    firstMatrix.setMatrix(i, j, value);
                }
            }
            return firstMatrix;
        } catch (NullPointerException | NumberFormatException e) {
            System.out.println("ERROR");
        }
        return null;
    }

    public static Matrix readSecondMatScanner(Scanner scanner) {
        try {
            System.out.print("Enter size of second matrix: ");
            int rows = scanner.nextInt();
            int columns = scanner.nextInt();
            Matrix secondMatrix = new Matrix(rows, columns);
            System.out.println();
            System.out.println("Enter second matrix");
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    double value = scanner.nextDouble();
                    secondMatrix.setMatrix(i, j, value);
                }
            }
            return secondMatrix;
        } catch (NullPointerException | NumberFormatException e) {
            System.out.println("ERROR");
        }
        return null;
    }


    public static double readConstant(Scanner scanner) {
        try {
            System.out.print("Enter the constant: ");
            double constant = scanner.nextDouble();
            return constant;
        } catch (NullPointerException | NumberFormatException e) {
            System.out.println("ERROR");
        }
        return -1;
    }


    public static void printMenu() {
        System.out.println("1. Add matrices");
        System.out.println("2. Multiply matrix by a constant");
        System.out.println("3. Multiply matrices");
        System.out.println("4. Transpose matrix");
        System.out.println("5. Calculate a determinant");
        System.out.println("6. Inverse matrix");
        System.out.println("0. Exit");
        System.out.print("Your choice: ");
    }

    public static void printTransposeOperationsMenu() {
        System.out.println("1. Main diagonal");
        System.out.println("2. Side diagonal");
        System.out.println("3. Vertical line");
        System.out.println("4. Horizontal line");
        System.out.print("Your choice: ");
    }


}
