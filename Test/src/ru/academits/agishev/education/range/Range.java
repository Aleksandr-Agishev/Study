package ru.academits.agishev.education.range;

import java.util.Scanner;

public class Range {
    private double from;
    private double to;

    //public Range() {    }

    public Range(double from, double to) {
        this.from = from >= to ? from : to;
        this.to = from >= to ? to : from;
    }

    public double getFrom() {
        checkFromTo();
        return this.from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        checkFromTo();
        return this.to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public void checkFromTo() {
        if (this.from > this.to) {
            double exchanger = this.from;
            this.from = this.to;
            this.to = exchanger;
        }
    }

    public double spacingLength() {
        checkFromTo();
        return this.from - this.to;
    }

    public boolean isInside(double point) {
        checkFromTo();
        return this.to >= point && point >= this.from;
    }

    public Range intersectionLength(Range range1, Range range2) {
        double intersectionFrom;
        double intersectionTo;
        if (range1.isInside(range2.getFrom())) {
            intersectionFrom = range2.getFrom();
        } else if (range2.isInside(range1.getFrom())) {
            intersectionFrom = range1.getFrom();
        } else {
            return null;
        }

        if (range1.isInside(range2.getTo())) {
            intersectionTo = range2.getTo();
        } else if (range2.isInside(range1.getTo())) {
            intersectionTo = range1.getTo();
        } else {
            return null;
        }
        return new Range(intersectionFrom,intersectionTo);
    }

    public Range[] intervalSplicing(Range range1, Range range2) {
        if (!range1.isInside(range2.getFrom()) && !range1.isInside(range2.getFrom())) {
            Range[] intervalSplicing = new Range[2];
            intervalSplicing[0] = range1;
            intervalSplicing[1] = range2;
            return intervalSplicing;
        }

        double intervalSplicingFrom;
        if (range1.isInside(range2.getFrom())) {
            intervalSplicingFrom =  range1.getFrom();
        } else {
            intervalSplicingFrom =  range2.getFrom();
        }

        double intervalSplicingTo;
        if (range1.isInside(range2.getTo())) {
            intervalSplicingTo =  range1.getTo();
        } else {
            intervalSplicingTo =  range2.getTo();
        }

        Range[] intervalSplicing = new Range[1];
        Range r = new Range(intervalSplicingFrom,intervalSplicingTo);
        intervalSplicing[0] = r;
        return intervalSplicing;
    }

    public Range[] intervalDifference(Range range1, Range range2) {
        if (!range1.isInside(range2.getFrom()) && !range1.isInside(range2.getFrom())) {
            Range[] intervalDifference = new Range[1];
            intervalDifference[0] = range1;
            return intervalDifference;
        }

        if (range2.isInside(range1.getFrom()) && range2.isInside(range1.getTo())) {
            return new Range[0];
        }

        if (range1.isInside(range2.getFrom()) && range1.isInside(range2.getTo())) {
            Range[] intervalDifference = new Range[2];
            Range r1 = new Range(range1.getFrom(),range2.getFrom());
            intervalDifference[0] = r1;
            Range r2 = new Range(range2.getTo(),range1.getTo());
            intervalDifference[1] = r2;
            return intervalDifference;
        }

        if (range2.isInside(range1.getFrom())) {
            Range[] intervalDifference = new Range[1];
            Range r = new Range(range2.getTo(),range1.getTo());
            intervalDifference[0] = r;
            return intervalDifference;
        }

        Range[] intervalDifference = new Range[1];
        Range r = new Range(range1.getFrom(),range2.getFrom());
        intervalDifference[0] = r;
        return intervalDifference;
    }
}
