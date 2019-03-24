package ru.academits.agishev.education.matrix;

public class MatrixOperation {
    public static void main(String[] args) {
        Matrix m1 = new Matrix(3, 4);
        System.out.println(m1);

        double[][] am2 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        Matrix m2 = new Matrix(am2);
        System.out.println(m2);

        m1.sum(m2);
        System.out.println(m1);

        double[][] am3 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}};
        Matrix m3 = new Matrix(am3);
        System.out.println(m3);

        Matrix m4 = Matrix.multiplication(m2, m3);
        System.out.println(m4);

        Matrix m5 = Matrix.multiplication(m3, m2);
        System.out.println(m5);

        m5.transposition();
        System.out.println(m5);
    }
}
