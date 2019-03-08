package ru.academits.agishev.education.Shape;

import java.util.Arrays;

public class Rectangle implements Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = Math.max(width, 0);
        this.height = Math.max(height, 0);
    }

    @Override
    public double getWidth() {
        return this.width;
    }

    @Override
    public double getHeight() {
        return this.height;
    }

    @Override
    public double getArea() {
        return this.width * this.height;
    }

    @Override
    public double getPerimeter() {
        return this.width * 2 + this.height * 2;
    }

    @Override
    public String toString() {
        double[] array = {width, height};
        return Arrays.toString(array);
    }

    public boolean equals(Rectangle o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Rectangle r = (Rectangle) o;
        return width == r.width && height == r.height;
    }

    public int hashCode() {
        final int prime = 401;
        int hash = 1;
        hash = prime * hash + Double.hashCode(width);
        hash = prime * hash + Double.hashCode(height);
        return hash;
    }
}
