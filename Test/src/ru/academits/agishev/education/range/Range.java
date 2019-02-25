package ru.academits.agishev.education.range;

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

    public double getLength() {
        return this.from - this.to;
    }

    public boolean isInside(double point) {
        return this.to >= point && point >= this.from;
    }

    public Range getIntersection(Range range) {
        double intersectionFrom = Math.max(range.from, this.from);
        double intersectionTo = Math.min(this.to, range.to);
        return intersectionFrom < intersectionTo ? new Range(intersectionFrom, intersectionTo) : null;
    }

    public Range[] getUnion(Range range) {
        double maxFrom = Math.max(this.from, range.from);
        double minTo = Math.min(this.to, range.to);
        if (maxFrom > minTo) {
            return new Range[]{new Range(this.from, this.to), new Range(range.from, range.to)};
        }

        double minFrom = Math.min(this.from, range.from);
        double maxTo = Math.max(this.to, range.to);
        return new Range[]{new Range(minFrom, maxTo)};
    }

    public Range[] getDifference(Range range) {
        double r1From = this.from;
        double r1To = this.to;
        double r2From = range.from;
        double r2To = range.to;

        if (r1To <= r2From || r2To <= r1From) {
            return new Range[]{new Range(this.from, this.to)};
        }

        if (r2From <= r1From && r2To >= r1To) {
            return new Range[0];
        }

        if (r1From < r2From && r1To > r2To) {
            return new Range[]{new Range(r1From, r2From), new Range(r2To, r1To)};
        }

        if (r2From <= r1From && r2To >= r1From) {
            return new Range[]{new Range(r2To, r1To)};
        }

        return new Range[]{new Range(r1From, r2From)};
    }
}
