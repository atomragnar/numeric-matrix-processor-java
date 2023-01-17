package processor.app.matrixoperations;

import java.util.Arrays;
import java.util.Scanner;

public class Matrix {

    private double[][] matrix;
    private int rows;
    private int cols;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.matrix = new double[rows][cols];
    }

    public void printMatrix() {
        System.out.println("The result is:");
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                System.out.print(this.matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public void setMatrix(int rows, int columns, double value) {
        this.matrix[rows][columns] = value;
    }


    public void addMatrices(Scanner scanner) {
        Matrix secondMatrix = AppUtils.readSecondMatScanner(scanner);
        if (secondMatrix != null && this.rows == secondMatrix.rows && this.cols == secondMatrix.cols) {
            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < this.cols; j++) {
                    System.out.print(this.matrix[i][j] + secondMatrix.matrix[i][j] + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("The operation cannot be performed");
        }
    }


    public void multiplyByConstant(Scanner scanner) {
        double constant = AppUtils.readConstant(scanner);
        if (constant != -1) {
            System.out.println("The result is:");
            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < this.cols; j++) {
                    double matrixNum = this.matrix[i][j];
                    System.out.print(matrixNum * constant + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("The operation cannot be performed");
        }
    }

    public void multiplyMatrices(Scanner scanner) {
        Matrix secondMatrix = AppUtils.readSecondMatScanner(scanner);
        double n = 0;
        if (secondMatrix != null && this.cols == secondMatrix.rows) {
            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < secondMatrix.cols; j++) {
                    for (int k = 0; k < this.cols; k++) {
                        double multiplication = this.matrix[i][k] * secondMatrix.matrix[k][j];
                        n += multiplication;
                    }
                    System.out.print(n + " ");
                    n = 0;
                }
                System.out.println();
            }
        } else {
            System.out.println("The operation cannot be performed");
        }

    }

    public void mainDiagonalTranspose() {
        double[][] newMatrix = new double[this.rows][this.cols];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                newMatrix[i][j] = this.matrix[j][i];

            }

        }
        this.setMatrix(newMatrix);
    }

    public void sideDiagonalTranspose() {
        double[][] newMatrix = new double[this.rows][this.cols];
        int countRow = this.cols - 1;
        for (int i = 0; i < this.rows; i++) {
            int count = this.cols - 1;
            for (int j = 0; j < this.cols; j++) {
                newMatrix[count][countRow] = this.matrix[i][j];
                count--;
            }
            countRow--;
        }
        this.setMatrix(newMatrix);
    }

    public void verticalLineTranspose() {
        double[][] newMatrix = new double[this.rows][this.cols];
        int count;
        for (int i = 0; i < this.rows; i++) {
            count = this.cols - 1;
            for (int j = 0; j < this.cols; j++) {
                newMatrix[i][j] = this.matrix[i][count];
                count--;
            }
        }
        this.setMatrix(newMatrix);
    }

    public void horizontalLineTranspose() {
        double[][] newMatrix = new double[this.rows][this.cols];
        int count = this.cols - 1;
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                newMatrix[i][j] = this.matrix[count][j];
            }
            count--;
        }
        setMatrix(newMatrix);
    }


    public double[][] copyOfMatrix() {
        double[][] temp = new double[this.rows][this.cols];
        for (int i = 0; i < this.rows; i++) {
            temp[i] = Arrays.copyOf(this.matrix[i], this.cols);
        }
        return temp;
    }

    /* private int[][] copyOfMatrixToInt() {
         int[][] temp = new int[this.rows][this.cols];
         for (int i = 0; i < this.rows; i++) {
             for (int j = 0; j < this.cols; j++) {
                 temp[i][j] = (int) this.matrix[i][j];
             }
         }
         return temp;
     }
 */
    public static double determinantOfMatrix(double[][] matrix) {
        double[][] temp;
        double result = 0;
        if (matrix.length == 1) {
            return matrix[0][0];
        }
        if (matrix.length == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }
        for (int i = 0; i < matrix.length; i++) {
            temp = new double[matrix.length - 1][matrix.length - 1];
            for (int j = 1; j < matrix.length; j++) {
                for (int k = 0; k < matrix.length; k++) {
                    if (k < i) {
                        temp[j - 1][k] = matrix[j][k];
                    } else if (k > i) {
                        temp[j - 1][k - 1] = matrix[j][k];
                    }
                }
            }
            result += matrix[0][i] * Math.pow(-1, i) * determinantOfMatrix(temp);
        }
        return result;
    }

    static double getCofactor(double[][] matrix, int row, int column, int length) {
        double[][] temp = new double[length - 1][length - 1];
        int count = 0;
        for (int j = 0; j < matrix.length; j++) {
            if (j == row) {
                count++;
                continue;
            }
            for (int k = 0; k < matrix.length; k++) {
                if (count == 1) {
                    if (k > column) {
                        temp[j - 1][k - 1] = matrix[j][k];
                    } else if (column > k) {
                        temp[j - 1][k] = matrix[j][k];
                    }
                } else {
                    if (k > column) {
                        temp[j][k - 1] = matrix[j][k];
                    } else if (column > k) {
                        temp[j][k] = matrix[j][k];
                    }
                }
            }
        }
        return Math.pow(-1, row - column) * determinantOfMatrix(temp);
    }

    double[][] getMatrixOfCofactors() {
        double[][] matrixOfCofactor = new double[this.rows][this.cols];

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                matrixOfCofactor[i][j] = getCofactor(this.matrix, i, j, this.rows);

            }

        }
        return transposeForInverse(matrixOfCofactor);
    }

    static double[][] transposeForInverse(double[][] matrix) {
        double[][] newMatrix = new double[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                newMatrix[i][j] = matrix[j][i];

            }

        }
        return newMatrix;
    }

    public void getInverseMatrix() {
        double determinant = determinantOfMatrix(this.matrix);

        if (determinant == 0.0) {
            System.out.println("This matrix doesn't have an inverse.");
        } else {

            double scalar = 1 / determinant;

            double[][] matrixOfCofactors = this.getMatrixOfCofactors();
            double[][] inverseMatrix = new double[this.rows][this.cols];


            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < this.rows; j++) {
                    inverseMatrix[i][j] = matrixOfCofactors[i][j] * scalar;
                }

            }


            double[][] controlMatrix = new double[this.rows][this.rows];

            double multiplication = 0;

            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < this.rows; j++) {
                    for (int k = 0; k < this.rows; k++) {
                        multiplication += this.matrix[i][k] * inverseMatrix[k][j];
                    }
                    controlMatrix[i][j] = multiplication;
                    multiplication = 0;
                }

            }
            System.out.println(Arrays.deepToString(inverseMatrix));
            System.out.println(Arrays.deepToString(controlMatrix));


            System.out.println("The result is:");
            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < this.rows; j++) {
                    System.out.printf("%5.2f ", inverseMatrix[i][j]);
                }
                System.out.println();
            }
        }
    }

    static boolean hasInverse(double[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                if (i == j) {
                    if (mat[i][j] >= 1.0 && mat[i][j] < 1.10) {
                        return false;
                    }
                } else {
                    if (mat[i][j] >= 0.0 && mat[i][j] < 0.15) {
                        return false;
                    }
                }

            }

        }
        return true;
    }


}




