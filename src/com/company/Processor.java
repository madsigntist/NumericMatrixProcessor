package com.company;

import java.util.Scanner;

public class Processor {
    private static final Scanner scanner = new Scanner(System.in);

    public static void execute() {
        int option;

        loop:
        while (true) {
            System.out.println(
                    "1. Add matrices\n" +
                    "2. Multiply matrix by a constant\n" +
                    "3. Multiply matrices\n" +
                    "4. Transpose matrix\n" +
                    "5. Calculate a " + "determinant\n" +
                    "6. Inverse matrix\n" +
                    "0. Exit");

            option = scanner.nextInt();

            switch (option) {
                case 0:
                    break loop;
                case 1:
                    Matrix.display();
                    Matrix.addition();
                    break;
                case 2:
                    Matrix.insertElements("constant");
                    Matrix.multiplicationConstant();
                    break;
                case 3:
                    Matrix.display();
                    Matrix.multiplication();
                    break;
                case 4:
                    Matrix.transposeOptions();
                    break;
                case 5:
                    System.out.println("Enter matrix size: ");
                    Matrix.insertElements("determinant");
                    System.out.println("The result is:\n" + Matrix.determinantOfMatrix());
                    break;
                case 6:
                    System.out.println("Enter matrix size: ");
                    Matrix.insertElements("inverse");
                    Matrix.inverseOfMatrix();
                    break;
            }
        }
    }
}
