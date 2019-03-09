package ru.academits.agishev.education.vectorOperations;

import ru.academits.agishev.education.vector.Vector;

import static ru.academits.agishev.education.vector.Vector.difference;
import static ru.academits.agishev.education.vector.Vector.scalarMultiplication;
import static ru.academits.agishev.education.vector.Vector.sum;

public class VectorOperations {
    public static void main(String[] args) {
        Vector v1 = new Vector(5);
        System.out.println(v1);
        double[] ar2 = {1, 2, 3, 4, 5};
        Vector v2 = new Vector(ar2);
        System.out.println(v2);
        Vector v3 = new Vector(6, ar2);
        System.out.println(v3);
        Vector v4 = new Vector(v3);
        System.out.println(v4.equals(v3) + "---" + v4.equals(v1) + v4.hashCode());
        v4.setComponents(5, 6);
        System.out.println(v4);
        System.out.println(v4.getSize() + "компонента 3 = " + v4.getComponent(3));
        Vector v5 = new Vector(6);
        System.out.println(v5);
        v5.sum(v4);
        System.out.println(v5);
        v1.sum(v5);
        System.out.println(v1);
        v1.sum(v2);
        v1.difference(v2);
        System.out.println(v1);
        v2.difference(v1);
        System.out.println(v2);
        Vector v6 = new Vector(ar2);
        v6.multiplication(4);
        v6.turn();
        System.out.println(v6);
        Vector v7 = sum(v1, v6);
        System.out.println(v7);
        Vector v8 = difference(v7, v6);
        System.out.println(v8);
        Vector v9 = scalarMultiplication(v1, v3);
        System.out.println(v9.length());
    }
}
