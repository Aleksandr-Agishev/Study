package ru.academits.agishev.education.Shape;

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
}
