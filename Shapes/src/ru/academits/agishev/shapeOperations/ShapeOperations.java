package ru.academits.agishev.shapeOperations;

import ru.academits.agishev.shapes.*;

import java.util.Arrays;

public class ShapeOperations {
    private static Shape getAreaShapeNumber(Shape[] shapes, int number) {
        Shape[] sortShapes = shapes.clone();
        Arrays.sort(sortShapes, new SortByAreaComparator());
        return sortShapes[number];
    }

    private static Shape getPerimeterShapeNumber(Shape[] shapes, int number){
        Shape[] sortShapes = shapes.clone();
        Arrays.sort(sortShapes, new SortByPerimeterComparator());
        return sortShapes[number];
    }

    public static void main(String[] args) {
        Shape s1 = new Square(3);
        Shape s2 = new Square(4);
        Shape s3 = new Square(8);
        Shape t1 = new Triangle(1, 2, 3, 4, 5, 6);
        Shape t2 = new Triangle(2, 3, 4, 5, 6, 7);
        Shape t3 = new Triangle(3, 4, 5, 6, 7, 8);
        Shape r1 = new Rectangle(1, 2);
        Shape r2 = new Rectangle(3, 4);
        Shape r3 = new Rectangle(5, 6);
        Shape c1 = new Circle(3);
        Shape c2 = new Circle(5);
        Shape c3 = new Circle(6);
        Shape[] shapes = {s1, s2, s3, t1, t2, t3, r1, r2, r3, c1, c2, c3};

        System.out.println(getAreaShapeNumber(shapes, 0));
        System.out.println(getAreaShapeNumber(shapes, 1));
        System.out.println(getPerimeterShapeNumber(shapes, 1));
        System.out.println(c1);
        System.out.println(r1);
        System.out.println(t1);
    }
}
