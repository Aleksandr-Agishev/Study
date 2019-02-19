package ru.academits.agishev.education.range;

import java.util.Scanner;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        if (from <= to) {
            this.from = from;
            this.to = to;
        } else {
            this.from = to;
            this.to = from;
        }
    }

    public double getFrom() {
        return this.from;
    }

    public void setFrom(double from) {
        if (from <= this.to) {
            this.from = from;
        } else {
            this.from = this.to;
            this.to = from;
        }
    }

    public double getTo() {
        return this.to;
    }

    public void setTo(double to) {
        if (to >= this.from) {
            this.to = to;
        } else {
            this.to = this.from;
            this.from = to;
        }
    }

    public double getSpacingLength() {
        return this.from - this.to;
    }

    public boolean isInside(double point) {
        return this.to >= point && point >= this.from;
    }

    public Range getIntersectionLength(Range range) {
        double r1From = this.from;
        double r1To = this.to;
        double r2From = range.getFrom();
        double r2To = range.getTo();

        double intersectionFrom;
        if (r1From <= r2From && r1To >= r2From) {
            intersectionFrom = r2From;
        } else if (r2From <= r1From && r2To >= r1From) {
            intersectionFrom = r1From;
        } else {
            return null;
        }

        double intersectionTo;
        if (r1From <= r2To && r1To >= r2To) {
            intersectionTo = r2To;
        } else if (r2From <= r1To && r2To >= r1To) {
            intersectionTo = r1To;
        } else {
            return null;
        }
        return new Range(intersectionFrom, intersectionTo);
    }

    public Range[] getRangeUnion(Range range) {
        double r1From = this.from;
        double r1To = this.to;
        double r2From = range.getFrom();
        double r2To = range.getTo();

        if (r1To <= r2From || r2To <= r1From) {
            return new Range[]{this,range};
        }

        double rangeUnionFrom;
        if (r1From <= r2From && r1To >= r2From) {
            rangeUnionFrom = r1From;
        } else {
            rangeUnionFrom = r2From;
        }

        double rangeUnionTo;
        if (r1From <= r2To && r1To >= r2To) {
            rangeUnionTo = r1To;
        } else {
            rangeUnionTo = r2To;
        }

        return new Range[]{new Range(rangeUnionFrom, rangeUnionTo)};
    }

    public Range[] getRangeDifference(Range range) {
        double r1From = this.from;
        double r1To = this.to;
        double r2From = range.getFrom();
        double r2To = range.getTo();

        double[] f = {1,2};
        if (r1To <= r2From || r2To <= r1From) {
            return new Range[]{this};
        }

        if (r2From <= r1From && r2To >= r1To) {
            return new Range[0];
        }

        if (r1From < r2From && r1To > r2To) {
            return new Range[]{new Range(r1From, r2From),new Range(r2To, r1To)};
        }

        if (r2From <= r1From && r2To >= r1From) {
            return new Range[]{new Range(r2To, r1To)};
        }

        return new Range[]{new Range(r1From, r2From)};
    }
}
