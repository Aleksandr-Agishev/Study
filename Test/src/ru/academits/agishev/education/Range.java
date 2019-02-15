package ru.academits.agishev.education;

import java.util.Scanner;

public class Range {
    private double from;
    private double to;

    private double getFrom() {
        checkFromTo();
        return this.from;
    }

    private void setFrom(double from) {
        this.from = from;
    }

    private double getTo() {
        checkFromTo();
        return this.to;
    }

    private void setTo(double to) {
        this.to = to;
    }

    private void checkFromTo() {
        if (this.from > this.to) {
            double exchanger = this.from;
            this.from = this.to;
            this.to = exchanger;
        }
    }

    private double spacingLength() {
        checkFromTo();
        return this.from - this.to;
    }

    private boolean isInside(double point) {
        checkFromTo();
        return this.to >= point && point >= this.from;
    }

    private Range intersectionLength(Range range1, Range range2) {
        Range intersectionLength = new Range();
        if (range1.isInside(range2.getFrom())) {
            intersectionLength.setFrom(range2.getFrom());
        } else if (range2.isInside(range1.getFrom())) {
            intersectionLength.setFrom(range1.getFrom());
        } else {
            return null;
        }

        if (range1.isInside(range2.getTo())) {
            intersectionLength.setTo(range2.getTo());
        } else if (range2.isInside(range1.getTo())) {
            intersectionLength.setTo(range1.getTo());
        } else {
            return null;
        }
        return intersectionLength;
    }

    private Range[] intervalSplicing(Range range1, Range range2) {
        if (!range1.isInside(range2.getFrom()) && !range1.isInside(range2.getFrom())) {
            Range[] intervalSplicing = new Range[2];
            intervalSplicing[0] = range1;
            intervalSplicing[1] = range2;
            return intervalSplicing;
        }

        Range[] intervalSplicing = new Range[1];
        Range r = new Range();
        if (range1.isInside(range2.getFrom())) {
            r.setFrom(range1.getFrom());
        } else {
            r.setFrom(range2.getFrom());
        }

        if (range1.isInside(range2.getTo())) {
            r.setTo(range1.getTo());
        } else {
            r.setTo(range2.getTo());
        }
        intervalSplicing[0] = r;
        return intervalSplicing;
    }

    private Range[] intervalDifference(Range range1, Range range2) {
        if (!range1.isInside(range2.getFrom()) && !range1.isInside(range2.getFrom())) {
            Range[] intervalDifference = new Range[1];
            intervalDifference[0] = range1;
            return intervalDifference;
        }

        if (range2.isInside(range1.getFrom()) && range2.isInside(range1.getTo())) {
            return null;
        }

        if (range1.isInside(range2.getFrom()) && range1.isInside(range2.getTo())) {
            Range[] intervalDifference = new Range[2];
            Range r1 = new Range();
            r1.setFrom(range1.getFrom());
            r1.setTo(range2.getFrom());
            intervalDifference[0] = r1;
            Range r2 = new Range();
            r2.setFrom(range2.getTo());
            r2.setTo(range1.getTo());
            intervalDifference[1] = r2;
            return intervalDifference;
        }

        if (range2.isInside(range1.getFrom())) {
            Range[] intervalDifference = new Range[1];
            Range r = new Range();
            r.setFrom(range2.getTo());
            r.setTo(range1.getTo());
            intervalDifference[0] = r;
            return intervalDifference;
        }

        Range[] intervalDifference = new Range[1];
        Range r = new Range();
        r.setFrom(range1.getFrom());
        r.setTo(range2.getFrom());
        intervalDifference[0] = r;
        return intervalDifference;
    }

    public static void main(String[] args) {
        System.out.println("Введите начало диапазона");
        Scanner scanner = new Scanner(System.in);
        Range range = new Range();
        range.setFrom(scanner.nextDouble());

        System.out.println("Введите конец диапазона");
        range.setTo(scanner.nextDouble());

        System.out.println("Введите начало диапазона");
        Range range2 = new Range();
        range2.setFrom(scanner.nextDouble());

        System.out.println("Введите конец диапазона");
        range2.setTo(scanner.nextDouble());

        System.out.println("from" + range.intervalDifference(range, range2)[0].getFrom());
        System.out.println("to" + range.intervalDifference(range, range2)[0].getTo());

        System.out.println("Длина диапазона равна 1 = " + range.spacingLength());


        System.out.println("Введите число");
        if (range.isInside(scanner.nextDouble())) {
            System.out.println("Введенное число принадлежит указанному диапазону");
        } else {
            System.out.println("Введенное число не принадлежит указанному диапазону");
        }
    }
}
