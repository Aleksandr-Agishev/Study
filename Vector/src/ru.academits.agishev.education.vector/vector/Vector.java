package ru.academits.agishev.education.vector.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public int getSize() {
        return this.components.length;
    }

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Длина массива элементов вектора меньше 1");
        }
        this.components = new double[size];
    }

    public Vector(Vector vector) {
        this.components = Arrays.copyOf(vector.components, vector.components.length);
    }

    public Vector(double[] components) {
        if (components.length <= 0) {
            throw new IllegalArgumentException("Длина массива элементов вектора меньше 1");
        }
        this.components = Arrays.copyOf(components, components.length);
    }

    public Vector(int size, double[] components) {
        if (size <= 0 || components.length == 0) {
            throw new IllegalArgumentException("Длина массива элементов вектора меньше 1");
        }

        double[] comp;
        if (size <= components.length) {
            comp = Arrays.copyOf(components, size);
        } else {
            comp = Arrays.copyOf(components, components.length);
        }
        this.components = comp;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        for (int i = 0; i < components.length - 1; i++) {
            stringBuilder.append(Double.toString(components[i]));
            stringBuilder.append(",");
        }
        stringBuilder.append(Double.toString(components[components.length - 1]));
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public static Vector sum(Vector vector1, Vector vector2) {
        int maxSize = Math.max(vector1.components.length, vector2.components.length);
        Vector vectorsSum = new Vector(maxSize);
        /*int minSize = Math.min(vector1.components.length, vector2.components.length);
        for (int i = 0; i < minSize; i++) {
            sum.components[i] = vector1.components[i] + vector2.components[i];
        }

        if (maxSize == vector1.components.length) {
            System.arraycopy(vector1.components, minSize, sum.components, minSize, maxSize - minSize);
        } else {
            System.arraycopy(vector2.components, minSize, sum.components, minSize, maxSize - minSize);
        }*/
        vectorsSum.sum(vector1);
        vectorsSum.sum(vector2);
        return vectorsSum;
    }

    public static Vector difference(Vector vector1, Vector vector2) {
        int maxSize = Math.max(vector1.components.length, vector2.components.length);
        Vector dif = new Vector(maxSize);
        /*int minSize = Math.min(vector1.components.length, vector2.components.length);
        for (int i = 0; i < minSize; i++) {
            dif.components[i] = vector1.components[i] - vector2.components[i];
        }

        if (maxSize == vector1.components.length) {
            System.arraycopy(vector1.components, minSize, dif.components, minSize, maxSize - minSize);
        } else {
            for (int i = minSize; i < maxSize; i++) {
                dif.components[i] = -vector2.components[i];
            }
        }*/
        dif.sum(vector1);
        dif.difference(vector2);
        return dif;
    }

    public static double scalarMultiplication(Vector vector1, Vector vector2) {
        double scalarMultiplication = 0;
        for (int i = 0; i < Math.min(vector1.components.length, vector2.components.length); i++) {
            scalarMultiplication += vector1.components[i] * vector2.components[i];
        }
        return scalarMultiplication;
    }

    public void sum(Vector vector) {
        double[] newComponents = new double[Math.max(vector.components.length, this.components.length)];
        System.arraycopy(this.components, 0, newComponents, 0, this.components.length);

        for (int i = 0; i < vector.components.length; i++) {
            newComponents[i] += vector.components[i];
        }
        this.components = newComponents;
    }

    public void difference(Vector vector) {
        double[] newComponents = new double[Math.max(vector.components.length, this.components.length)];
        System.arraycopy(this.components, 0, newComponents, 0, this.components.length);

        for (int i = 0; i < vector.components.length; i++) {
            newComponents[i] -= vector.components[i];
        }
        this.components = newComponents;
    }

    public void multiplication(double scalar) {
        for (int i = 0; i < this.components.length; i++) {
            this.components[i] *= scalar;
        }
    }

    public void turn() {
        multiplication(-1);
    }

    public double getLength() {
        double square = 0;
        for (double e : this.components) {
            square += Math.sqrt(e);
        }
        return Math.pow(square, 0.5);
    }

    public double getComponent(int number) {
        return components[number];
    }

    public void setComponents(int number, double value) {
        if (number >= 1 && number <= this.components.length) {
            this.components[number - 1] = value;
        }
    }

    @Override
    public boolean equals(Object vector) {
        if (vector == this) {
            return true;
        }
        if (vector == null || vector.getClass() != this.getClass()) {
            return false;
        }
        Vector v = (Vector) vector;
        if (this.components.length != v.components.length) {
            return false;
        }
        return Arrays.equals(this.components, v.components);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(components);
    }
}
