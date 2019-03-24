package ru.academits.agishev.education.matrix;

import ru.academits.agishev.education.vector.vector.Vector;

public class Matrix {
    private double[][] components; //n - строк, m - столбцов

    public Matrix(int n, int m) {//n - строк, m - столбцов
        if (n < 1 || m < 1) {
            throw new IllegalArgumentException("Задан не допустимый размер матрици");
        }
        this.components = new double[n][m];
    }

    public Matrix(Matrix matrix) {
        this.components = matrix.components;
    }

    public Matrix(double[][] components) {
        if (components.length < 1 || components[1].length < 1) {
            throw new IllegalArgumentException("Задан не допустимый размер матрици");
        }
        this.components = components;
    }

    public Matrix(Vector[] vector) { //n - строк, m - столбцов, векторы в матрице являются строками
        int linesNumber = 0;
        int columnsNumber = 0;
        for (Vector e : vector) {
            columnsNumber = columnsNumber > e.getSize() ? columnsNumber : vector.length;
            linesNumber++;
        }
        double[][] components = new double[linesNumber][columnsNumber];
        for (int i = 0; i < linesNumber; i++) {
            for (int j = 0; j < vector[i].getSize(); j++) {
                components[i][j] = vector[i].getComponent(j);
            }
        }
        this.components = components;
    }

    public int getHeight() {
        return components.length;
    }

    public int getWidth() {
        return components[1].length;
    }

    public Vector getLineVector(int lineNumber) {
        return new Vector(this.components[lineNumber]);
    }

    public void setLineVector(int lineNumber, Vector vector) {
        if (lineNumber < 1 || lineNumber > this.components.length || vector.getSize() != this.components[1].length) {
            throw new IllegalArgumentException("Введены недопустимые параметры матрицы");
        }

        for (int i = 0; i < vector.getSize(); i++) {
            this.components[lineNumber - 1][i] = vector.getComponent(i);
        }
    }

    public Vector getColumnVector(int columnNumber) {
        Vector vector = new Vector(this.components.length);
        for (int i = 0; i < vector.getLength(); i++) {
            vector.setComponents(i + 1, this.components[i][columnNumber]);
        }
        return vector;
    }

    public void setColumnVector(int columnNumber, Vector vector) {
        if (columnNumber < 1 || columnNumber > this.components[1].length || vector.getSize() != this.components.length) {
            throw new IllegalArgumentException("Введены недопустимые параметры матрицы");
        }

        for (int i = 0; i < vector.getSize(); i++) {
            this.components[i][columnNumber - 1] = vector.getComponent(i);
        }
    }

    public void transposition() {
        double[][] transposition = new double[this.components[1].length][this.components.length];
        for (int i = 0; i < this.components.length; i++) {
            for (int j = 0; j < this.components[1].length; j++) {
                transposition[j][i] = this.components[i][j];
            }
        }
        this.components = transposition;
    }

    public void scalarMultiplication(double scalar) {
        for (int i = 0; i < components.length; i++) {
            for (int j = 0; j < components[1].length; j++) {
                this.components[i][j] *= scalar;
            }
        }
    }

    private double[][] getAlgebraicAddition(double[][] matrix, int line, int column) {

        int dimension = matrix.length;
        double[][] algebraicAddition = new double[dimension - 1][dimension - 1];
        int height = 0;

        for (int i = 0; i < dimension; i++) {
            if (i == line) {
                height++;
                continue;
            }

            int width = 0;
            for (int j = 0; j < dimension; j++) {
                if (j == column) {
                    width++;
                    continue;
                }
                algebraicAddition[i - height][j - width] = matrix[i][j];
            }
        }
        return algebraicAddition;
    }

    private double getMatrixDeterminant(double[][] matrix) {
        if (matrix.length == 1) {
            return matrix[0][0];
        }

        if (matrix.length == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }

        double determinant = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (i % 2 == 0) {
                determinant += matrix[0][i] * getMatrixDeterminant(getAlgebraicAddition(matrix, 0, i));
            } else {
                determinant -= matrix[0][i] * getMatrixDeterminant(getAlgebraicAddition(matrix, 0, i));
            }
        }
        return determinant;
    }

    public double getDeterminant() {
        if (this.components.length != this.components[1].length) {
            throw new IllegalArgumentException("Матрица не квадратная - определитель не существует");
        }

        return getMatrixDeterminant(components);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");
        for (int i = 0; i < this.components.length; i++) {
            stringBuilder.append("{");
            for (int j = 0; j < this.components[1].length; j++) {
                stringBuilder.append(Double.toString(this.components[i][j]));
                if (j < this.components[1].length - 1) {
                    stringBuilder.append(",");
                }
            }

            if (i < this.components.length - 1) {
                stringBuilder.append(",");
            }
            stringBuilder.append("}");
        }
        stringBuilder.append("}");

        return stringBuilder.toString();
    }

    public Vector multiplicationByVector(Vector vector) {
        if (this.components[1].length != vector.getLength()) {
            throw new IllegalArgumentException("Вектор и матрица не сопоставимых размерностей");
        }

        Vector newVector = new Vector(this.components.length);
        for (int i = 0; i < this.components.length; i++) {
            double componentsMultiplication = 0;
            for (int j = 0; j < this.components[1].length; j++) {
                componentsMultiplication += this.components[i][j] * vector.getComponent(j);
            }
            newVector.setComponents(i, componentsMultiplication);
        }

        return newVector;
    }

    public void sum(Matrix matrix) {
        if (this.components.length != matrix.components.length || this.components[1].length != matrix.components[1].length) {
            throw new IllegalArgumentException("Матрицы разных размерностей");
        }

        for (int i = 0; i < this.components.length; i++) {
            for (int j = 0; j < this.components[1].length; j++) {
                this.components[i][j] += matrix.components[i][j];
            }
        }
    }

    public void difference(Matrix matrix) {
        if (this.components.length != matrix.components.length || this.components[1].length != matrix.components[1].length) {
            throw new IllegalArgumentException("Матрицы разных размерностей");
        }

        matrix.scalarMultiplication(-1);
        sum(matrix);
    }

    public static Matrix sum(Matrix matrix1, Matrix matrix2) {
        if (matrix1.components.length != matrix2.components.length || matrix1.components[1].length != matrix2.components[1].length) {
            throw new IllegalArgumentException("Матрицы разных размерностей");
        }
        Matrix matrixSum = new Matrix(matrix1);
        matrixSum.sum(matrix2);
        return matrixSum;
    }

    public static Matrix difference(Matrix matrix1, Matrix matrix2) {
        if (matrix1.components.length != matrix2.components.length || matrix1.components[1].length != matrix2.components[1].length) {
            throw new IllegalArgumentException("Матрицы разных размерностей");
        }
        Matrix matrixDif = new Matrix(matrix2);
        matrixDif.scalarMultiplication(-1);
        matrixDif.sum(matrix2);
        return matrixDif;
    }

    private void setComponents(int line, int column, double value) {
        this.components[line][column] = value;
    }

    public static Matrix multiplication(Matrix matrix1, Matrix matrix2) {
        if (matrix1.components[1].length != matrix2.components.length) {
            throw new IllegalArgumentException("Матрицы разных размерностей");
        }

        Matrix matrixMult = new Matrix(matrix1.components.length, matrix2.components[1].length);
        for (int i = 0; i < matrix1.components.length; i++) {
            for (int j = 0; j < matrix2.components[1].length; j++) {
                double componentsMult = 0;
                for (int k = 0; k < matrix1.components[1].length; k++) {
                    componentsMult += matrix1.components[i][k] * matrix2.components[k][j];
                }
                matrixMult.setComponents(i, j, componentsMult);
            }
        }
        return matrixMult;
    }
}
