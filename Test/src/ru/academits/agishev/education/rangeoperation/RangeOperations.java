package ru.academits.agishev.education.rangeoperation;
import ru.academits.agishev.education.range.Range;

import java.util.Scanner;

public class RangeOperations {
    public static void main(String[] args) {
        System.out.println("Введите начало диапазона");
        Scanner scanner = new Scanner(System.in);
        double from = scanner.nextDouble();

        System.out.println("Введите конец диапазона");
        double to = scanner.nextDouble();
        Range range = new Range(from,to);

        System.out.println("Введите начало диапазона");
        double from2 = scanner.nextDouble();

        System.out.println("Введите конец диапазона");
        double to2 = scanner.nextDouble();
        Range range2 = new Range(from2,to2);

        System.out.println("from" + range.getRangeDifference(range2)[0].getFrom());
        System.out.println("to" + range.getRangeDifference(range2)[0].getTo());

        System.out.println("Длина диапазона равна 1 = " + range.getSpacingLength());


        System.out.println("Введите число");
        if (range.isInside(scanner.nextDouble())) {
            System.out.println("Введенное число принадлежит указанному диапазону");
        } else {
            System.out.println("Введенное число не принадлежит указанному диапазону");
        }
    }
}
