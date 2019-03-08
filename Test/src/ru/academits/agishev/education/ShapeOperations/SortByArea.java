package ru.academits.agishev.education.ShapeOperations;

import ru.academits.agishev.education.Shape.Shape;

import java.util.Comparator;

public class SortByArea implements Comparator<Shape> {
    public int compare(Shape a, Shape b) {
        if (a.getArea() < b.getArea()) {
            return 1;
        } else if (a.getArea() > b.getArea()) {
            return -1;
        }
        return 0;
    }
}
