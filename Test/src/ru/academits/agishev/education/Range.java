package ru.academits.agishev.education;

import java.util.Scanner;

public class Range {
    private double from;
    private double to;

    private void setFrom(double from) {
        this.from = from;
    }

    private void setTo(double to) {
        this.to = to;
    }

    private double spacingLength() {
        return Math.abs(this.from - this.to);
    }

    private boolean isInside(double point) {
        if (this.to > this.from) {
            return this.to >= point && point >= this.from;
        }
        return this.from >= point && point >= this.to;
    }

    public static void main(String[] args) {
        System.out.println("Введите начало диапазона");
        Scanner scanner = new Scanner(System.in);
        Range range = new Range();
        range.setFrom(scanner.nextDouble());

        System.out.println("Введите конец диапазона");
        range.setTo(scanner.nextDouble());

        System.out.println("Длина диапазона равна = " + range.spacingLength());

        System.out.println("Введите число");
        if (range.isInside(scanner.nextDouble())) {
            System.out.println("Введенное число принадлежит указанному диапазону");
        } else {
            System.out.println("Введенное число не принадлежит указанному диапазону");
        }
    }
}
