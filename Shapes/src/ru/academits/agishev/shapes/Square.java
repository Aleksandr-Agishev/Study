package ru.academits.agishev.shapes;

public class Square implements Shape {
    private double side;

    public Square(double side) {
        this.side = Math.max(side, 0);
    }

    @Override
    public double getWidth() {
        return this.side;
    }

    @Override
    public double getHeight() {
        return this.side;
    }

    @Override
    public double getArea() {
        return Math.pow(this.side, 2);
    }

    @Override
    public double getPerimeter() {
        return this.side * 4;
    }

    @Override
    public String toString() {
        return Double.toString(side) + "Square";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Square s = (Square) o;
        return side == s.side;
    }

    public int hashCode() {
        return Double.hashCode(side);
    }
}