package ru.academits.agishev.education.vector;

import java.util.Arrays;

public class Vector {
    private int size;
    private double[] components = new double[size];

    public int getSize() {
        return size;
    }

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Длина массива элементов вектора меньше 1");
        }
        this.size = size;
        double[] comp = new double[size];
        for (int i = 0; i < size; i++) {
            comp[i] = 0;
        }
        this.components = comp;
    }

    public Vector(Vector vector) {
        this.size = vector.size;
        double[] comp = new double[vector.size];
        System.arraycopy(vector.components, 0, comp, 0, this.size);
        this.components = comp;
    }

    public Vector(double[] components) {
        this.size = components.length;
        double[] comp = new double[this.size];
        System.arraycopy(components, 0, comp, 0, this.size);
        this.components = comp;
    }

    public Vector(int size, double[] components) {
        if (size <= 0) {
            throw new IllegalArgumentException("Длина массива элементов вектора меньше 1");
        }
        this.size = size;
        double[] comp = new double[this.size];
        if (size <= components.length) {
            System.arraycopy(components, 0, comp, 0, this.size);
        } else {
            System.arraycopy(components, 0, comp, 0, components.length);
            for (int i = components.length; i < size; i++) {
                comp[i] = 0;
            }
        }
        this.components = comp;
    }

    @Override
    public String toString() {
        return Arrays.toString(this.components);
    }

    public static Vector sum(Vector vector1, Vector vector2) {
        int maxSize = Math.max(vector1.size, vector2.size);
        Vector sum = new Vector(maxSize);
        int minSize = Math.min(vector1.size, vector2.size);
        for (int i = 0; i < minSize; i++) {
            sum.components[i] = vector1.components[i] + vector2.components[i];
        }

        if (maxSize == vector1.size) {
            System.arraycopy(vector1.components, minSize, sum.components, minSize, maxSize - minSize);
        } else {
            System.arraycopy(vector2.components, minSize, sum.components, minSize, maxSize - minSize);
        }
        return sum;
    }

    public static Vector difference(Vector vector1, Vector vector2) {
        int maxSize = Math.max(vector1.size, vector2.size);
        Vector dif = new Vector(maxSize);
        int minSize = Math.min(vector1.size, vector2.size);
        for (int i = 0; i < minSize; i++) {
            dif.components[i] = vector1.components[i] - vector2.components[i];
        }

        if (maxSize == vector1.size) {
            System.arraycopy(vector1.components, minSize, dif.components, minSize, maxSize - minSize);
        } else {
            for (int i = minSize; i < maxSize; i++) {
                dif.components[i] = -vector2.components[i];
            }
        }
        return dif;
    }

    public static Vector scalarMultiplication(Vector vector1, Vector vector2) {
        int maxSize = Math.max(vector1.size, vector2.size);
        Vector scalarMultiplication = new Vector(maxSize);
        int minSize = Math.min(vector1.size, vector2.size);
        for (int i = 0; i < minSize; i++) {
            scalarMultiplication.components[i] = vector1.components[i] * vector2.components[i];
        }
        for (int i = minSize; i < maxSize; i++) {
            scalarMultiplication.components[i] = 0;
        }
        return scalarMultiplication;
    }

    public void sum(Vector vector) {
        if (vector.size <= this.size) {
            for (int i = 0; i < vector.size; i++) {
                this.components[i] += vector.components[i];
            }
        } else {
            double[] newComponents = new double[vector.size];
            for (int i = 0; i < this.size; i++) {
                newComponents[i] = this.components[i] + vector.components[i];
            }
            System.arraycopy(vector.components, this.size, newComponents, this.size, vector.size - this.size);
            this.size = vector.size;
            this.components = newComponents;
        }
    }

    public void difference(Vector vector) {
        if (vector.size <= this.size) {
            for (int i = 0; i < vector.size; i++) {
                this.components[i] -= vector.components[i];
            }
        } else {
            double[] newComponents = new double[vector.size];
            for (int i = 0; i < this.size; i++) {
                newComponents[i] = this.components[i] - vector.components[i];
            }
            for (int i = this.size; i < vector.size; i++) {
                newComponents[i] = -vector.components[i];
            }
            this.size = vector.size;
            this.components = newComponents;
        }
    }

    public void multiplication(double scalar) {
        for (int i = 0; i < this.size; i++) {
            this.components[i] *= scalar;
        }
    }

    public void turn() {
        for (int i = 0; i < this.size; i++) {
            this.components[i] *= -1;
        }
    }

    public double length() {
        double square = 0;
        for (int i = 0; i < this.size; i++) {
            square += Math.pow(this.components[i], 2);
        }
        return Math.pow(square, 0.5);
    }

    public double getComponent(int number) {
        return components[number];
    }

    public void setComponents(int number, double value) {
        if (number >= 0 && number < this.size) {
            this.components[number] = value;
        }
    }

    public boolean equals(Vector vector) {
        if (vector == this) {
            return true;
        }
        if (vector == null || vector.getClass() != this.getClass()) {
            return false;
        }
        Vector v = (Vector) vector;
        boolean b = this.size == v.size;
        for (int i = 0; i < this.size; i++) {
            if (!b) {
                return false;
            }
            b = v.components[i] == this.components[i];
        }
        return b;
    }

    @Override
    public int hashCode() {
        final int prime = 401;
        int hash = 1;
        hash = prime * hash + Integer.hashCode(size);
        hash = prime * hash + Arrays.hashCode(components);
        return hash;
    }
}
