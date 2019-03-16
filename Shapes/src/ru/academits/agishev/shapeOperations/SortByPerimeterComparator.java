package ru.academits.agishev.shapeOperations;

import ru.academits.agishev.shapes.Shape;

import java.util.Comparator;

public class SortByPerimeterComparator implements Comparator<Shape> {
    public int compare(Shape a, Shape b) {
        return Double.compare(b.getPerimeter(), a.getPerimeter());
    }
}
