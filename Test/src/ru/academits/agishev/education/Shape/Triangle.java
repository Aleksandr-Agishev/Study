package ru.academits.agishev.education.Shape;

import java.util.Arrays;

public class Triangle implements Shape {
    private double x1;
    private double x2;
    private double x3;
    private double y1;
    private double y2;
    private double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
    }

    @Override
    public double getWidth() {
        return Math.max(this.x1, Math.max(this.x2, this.x3)) - Math.min(this.x1, Math.min(this.x2, this.x3));
    }

    @Override
    public double getHeight() {
        return Math.max(this.y1, Math.max(this.y2, this.y3)) - Math.min(this.y1, Math.min(this.y2, this.y3));
    }

    @Override
    public double getArea() {
        return Math.abs(0.5 * ((this.x1 - this.x3) * (this.y2 - this.y3) - (this.x2 - this.x3) * (this.y1 - this.y3)));
    }

    @Override
    public double getPerimeter() {
        double sideA = Math.pow(Math.pow(this.x2 - this.x1, 2) + Math.pow(this.y2 - this.y1, 2), 0.5);
        double sideB = Math.pow(Math.pow(this.x3 - this.x2, 2) + Math.pow(this.y3 - this.y2, 2), 0.5);
        double sideC = Math.pow(Math.pow(this.x1 - this.x3, 2) + Math.pow(this.y1 - this.y3, 2), 0.5);
        return sideA + sideB + sideC;
    }

    @Override
    public String toString() {
        double[] array = {x1, x2, x3, y1, y2, y3};
        return Arrays.toString(array);
    }

    public boolean equals(Triangle o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Triangle t = (Triangle) o;
        return x1 == t.x1 && x2 == t.x2 && x3 == t.x3 & y1 == t.y1 && y2 == t.y2 && y3 == t.y3;
    }

    public int hashCode() {
        final int prime = 401;
        int hash = 1;
        hash = prime * hash + Double.hashCode(x1);
        hash = prime * hash + Double.hashCode(x2);
        hash = prime * hash + Double.hashCode(x3);
        hash = prime * hash + Double.hashCode(y1);
        hash = prime * hash + Double.hashCode(y2);
        hash = prime * hash + Double.hashCode(y3);
        return hash;
    }
}
