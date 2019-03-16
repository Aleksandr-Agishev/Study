package ru.academits.agishev.shapeOperations;

import ru.academits.agishev.shapes.Shape;

import java.util.Comparator;

public class SortByAreaComparator implements Comparator<Shape> {
    public int compare(Shape a, Shape b) {
        return Double.compare(b.getArea(), a.getArea());
    }
}
