package processor;

import processor.app.matrixoperations.AppUtils;
import processor.app.matrixoperations.Matrix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        boolean appOn = true;
        Scanner scanner = new Scanner(System.in);
        int inputOption;

        while (appOn) {
            AppUtils.printMenu();

            inputOption = scanner.nextInt();

            switch (inputOption) {
                case 1: // addition
                    try {
                        Matrix firstMatrix = AppUtils.readFirstMatScanner(scanner);
                        firstMatrix.addMatrices(scanner);
                    } catch (NullPointerException e) {
                        System.out.println("ERROR");
                    }
                    break;
                case 2: // multiply by constant
                    try {
                        Matrix multiplyConstantMatrix = AppUtils.readFirstMatScanner(scanner);
                        multiplyConstantMatrix.multiplyByConstant(scanner);
                    } catch (NullPointerException e) {
                        System.out.println("ERROR");
                    }
                    break;
                case 3: // multiply matrices
                    try {
                        Matrix multiplyMatrix = AppUtils.readFirstMatScanner(scanner);
                        multiplyMatrix.multiplyMatrices(scanner);
                    } catch (NullPointerException e) {
                        System.out.println("ERROR");
                    }
                    break;
                case 4: // transpose operations
                    try {
                        AppUtils.printTransposeOperationsMenu();
                        int userInput = scanner.nextInt();
                        Matrix transposeMatrix;
                        switch (userInput) {
                            case 1:
                                transposeMatrix = AppUtils.readFirstMatScanner(scanner);
                                transposeMatrix.mainDiagonalTranspose();
                                transposeMatrix.printMatrix();
                                break;
                            case 2:
                                transposeMatrix = AppUtils.readFirstMatScanner(scanner);
                                transposeMatrix.sideDiagonalTranspose();
                                transposeMatrix.printMatrix();
                                break;
                            case 3:
                                transposeMatrix = AppUtils.readFirstMatScanner(scanner);
                                transposeMatrix.verticalLineTranspose();
                                transposeMatrix.printMatrix();
                                break;
                            case 4:
                                transposeMatrix = AppUtils.readFirstMatScanner(scanner);
                                transposeMatrix.horizontalLineTranspose();
                                transposeMatrix.printMatrix();
                                break;
                            default:
                                break;
                        }
                    } catch (NullPointerException e) {
                        System.out.println("ERROR");
                    }
                    break;
                case 5:
                    try {
                        Matrix calcDetMatrix = AppUtils.readFirstMatScanner(scanner);

                        double[][] matrix = calcDetMatrix.copyOfMatrix();

                        double determinant = Matrix.determinantOfMatrix(matrix);

                        System.out.println(determinant);

                    } catch (NullPointerException e) {
                        System.out.println("ERROR");
                    }
                    break;
                case 6:
                    try {
                        Matrix calcInverseMatrix = AppUtils.readFirstMatScanner(scanner);
                        calcInverseMatrix.getInverseMatrix();
                        break;

                    } catch (NullPointerException e) {
                        System.out.println("ERROR");
                    }
                    break;
                case 0: // quit the app
                    appOn = false;
                    break;
                default:
                    System.out.println("No such operation");
                    break;
            }
        }


    }

}
