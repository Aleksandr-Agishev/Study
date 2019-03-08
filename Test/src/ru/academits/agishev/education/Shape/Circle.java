package ru.academits.agishev.education.Shape;

public class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = Math.max(radius, 0);
    }

    @Override
    public double getWidth() {
        return this.radius * 2;
    }

    @Override

    public double getHeight() {
        return this.radius * 2;
    }

    @Override
    public double getArea() {
        return Math.pow(this.radius, 2) * Math.PI;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * this.radius;
    }

    @Override
    public String toString() {
        return Double.toString(radius);
    }

    public boolean equals(Circle o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Circle c = (Circle) o;
        return radius == c.radius;
    }

    public int hashCode() {
        final int prime = 401;
        int hash = 1;
        hash = prime * hash + Double.hashCode(radius);
        return hash;
    }
}
