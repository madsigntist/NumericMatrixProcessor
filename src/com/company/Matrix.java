package com.company;

import java.util.Scanner;

public class Matrix {
    private static final Scanner scanner = new Scanner(System.in);
    static double[][] matrix1;
    static double[][] matrix2;
    static double constant;

    public static void insertElements(String matrixName) {
        int size1 = scanner.nextInt();
        int size2 = scanner.nextInt();
        boolean bool = "matrix1".equals(matrixName) || "constant".equals(matrixName)
                || "transpose".equals(matrixName) || "determinant".equals(matrixName)
                || "inverse".equals(matrixName);

        if (bool) {
            matrix1 = new double[size1][size2];

            if ("transpose".equals(matrixName) || "determinant".equals(matrixName)
                    || "inverse".equals(matrixName)) {
                System.out.println("Enter matrix: ");
            } else {
                System.out.println("Enter first matrix:");
            }
        } else {
            matrix2 = new double[size1][size2];
            System.out.println("Enter second matrix:");
        }

        for (int i = 0; i < size1; i++) {
            for (int j = 0; j < size2; j++) {
                if (bool) {
                    matrix1[i][j] = scanner.nextDouble();
                } else {
                    matrix2[i][j] = scanner.nextDouble();
                }
            }
        }

        if ("constant".equals(matrixName)) {
            System.out.println("Enter constant: ");
            constant = scanner.nextDouble();
        }
    }

    public static void addition() {
        if (matrix1.length == matrix2.length && matrix1[0].length == matrix2[0].length) {
            System.out.println("The result is:");

            for (int i = 0; i < matrix1.length; i++) {
                for (int j = 0; j < matrix1[0].length; j++) {
                    System.out.print(matrix1[i][j] + matrix2[i][j] + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("The operation cannot be performed");
        }
    }

    public static void multiplicationConstant() {
        for (double[] i : matrix1) {
            for (double j : i) {
                System.out.print(j * constant + " ");
            }
            System.out.println();
        }
    }

    public static void multiplication() {
        if (matrix1[0].length == matrix2.length) {
            double[][] result = new double[matrix1.length][matrix2[0].length];

            System.out.println("The result is:");

            for (int i = 0; i < matrix1.length; i++) {
                for (int j = 0; j < matrix2[0].length; j++) {
                    for (int k = 0; k < matrix2.length; k++) {
                        result[i][j] += matrix1[i][k] * matrix2[k][j];
                    }
                    System.out.print(result[i][j] + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("The operation cannot be performed");
        }
    }

    public static void display() {
        System.out.println("Enter size of first matrix: ");
        insertElements("matrix1");
        System.out.println("Enter size of second matrix: ");
        insertElements("matrix2");
    }

    public static void transposeOptions() {
        System.out.println("1. Main diagonal\n2. Side diagonal\n3. Vertical line\n" +
                "4. Horizontal line");

        int option = scanner.nextInt();

        System.out.println("Enter matrix size:");

        insertElements("transpose");

        switch (option) {
            case 1:
                mainDiagonal();
                break;
            case 2:
                sideDiagonal();
                break;
            case 3:
                verticalDiagonal();
                break;
            case 4:
                horizontalDiagonal();
                break;
        }
    }

    public static void mainDiagonal() {
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1[0].length; j++) {
                System.out.print(matrix1[j][i] + " ");
            }
            System.out.println();
        }
    }

    public static void sideDiagonal() {
        for (int i = matrix1[0].length - 1; i >= 0; i--) {
            for (int j = matrix1[0].length - 1; j >= 0; j--) {
                System.out.print(matrix1[j][i] + " ");
            }
            System.out.println();
        }
    }

    public static void verticalDiagonal() {
        for (double[] doubles : matrix1) {
            for (int j = matrix1[0].length - 1; j >= 0; j--) {
                System.out.print(doubles[j] + " ");
            }
            System.out.println();
        }
    }

    public static void horizontalDiagonal() {
        for (int i = matrix1.length - 1; i >= 0; i--) {
            for (int j = 0; j < matrix1[0].length; j++) {
                System.out.print(matrix1[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static double determinantOfMatrix() {
        double num1, num2, det = 1, total = 1;
        int index, n = matrix1.length;
        double[] temp = new double[n + 1];

        for (int i = 0; i < n; i++) {
            index = i;

            while (matrix1[index][i] == 0 && index < n) {
                index++;
            }

            if (index != i) {
                for (int j = 0; j < n; j++) {
                    double swap = matrix1[index][j];
                    matrix1[index][j] = matrix1[i][j];
                    matrix1[i][j] = swap;
                }
                det = (int) (det * Math.pow(-1, index - i));
            }

            System.arraycopy(matrix1[i], 0, temp, 0, n);

            for (int j = i + 1; j < n; j++) {
                num1 = temp[i];
                num2 = matrix1[j][i];

                for (int k = 0; k < n; k++) {
                    matrix1[j][k] = (num1 * matrix1[j][k]) - (num2 * temp[k]);
                }

                total = total * num1;
            }
        }

        for (int i = 0; i < n; i++) {
            det = det * matrix1[i][i];
        }

        return (det / total);
    }

    public static void inverseOfMatrix() {
        double[][] matrix = new double[matrix1.length][matrix1[0].length];

        for (int i = 0; i < matrix1.length; i++) {
            System.arraycopy(matrix1[i], 0, matrix[i], 0, matrix1[0].length);
        }

        double det = determinantOfMatrix();

        if (det == 0) {
            System.out.println("This matrix doesn't have an inverse.");
        } else {
            double[][] adj = new double[matrix.length][matrix[0].length];
            double[][] trans = new double[matrix.length][matrix[0].length];

            adjoint(matrix, adj);

            for (int i = 0; i < adj.length; i++) {
                for (int j = 0; j < adj[0].length; j++) {
                    trans[i][j] = adj[j][i];
                }
            }

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    System.out.print((1 / det) * trans[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

    public static void adjoint(double[][] mat, double[][] adj) {
        if (mat.length == 3 && mat[0].length == 3) {
            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat[0].length; j++) {
                    adj[i][j] = (((i + j) % 2 == 0) ? 1 : -1) * determinant(mat, i, j);
                }
            }
        } else {
            double[][] matrix = new double[mat.length - 1][mat[0].length - 1];
            int row = 0, column = 0;

            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat[0].length; j++) {
                    for (int k = 0; k < mat.length; k++) {
                        for (int l = 0; l < mat[0].length; l++) {
                            if (k != i && l != j) {
                                matrix[row][column] = mat[k][l];
                                column++;
                            }
                        }
                        if (k != i) {
                            column = 0;
                            row++;
                        }
                    }

                    row = 0;
                    column = 0;
                    adj[i][j] += (((i + j) % 2 == 0) ? 1 : -1) * (matrix[0][0] * matrix[1][1] * matrix[2][2] +
                            matrix[0][1] * matrix[1][2] * matrix[2][0] + matrix[0][2] * matrix[1][0] * matrix[2][1] -
                            matrix[0][2] * matrix[1][1] * matrix[2][0] - matrix[0][1] * matrix[1][0] * matrix[2][2] -
                            matrix[0][0] * matrix[1][2] * matrix[2][1]);
                }
            }
        }
    }

    public static double determinant(double[][] mat, int p, int q) {
        double[][] temp = new double[2][2];
        int c1 = 0, c2 = 0;

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (i != p && j != q) {
                    temp[c1][c2] = mat[i][j];
                    c2++;
                }
            }
            if (i != p) {
                c1++;
                c2 = 0;
            }
        }

        return (temp[0][0] * temp[1][1]) - (temp[0][1] * temp[1][0]);
    }
}
