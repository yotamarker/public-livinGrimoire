package Auxiliary_Modules;

import java.awt.*;

public class LGPointDouble {
    public Double x;
    public Double y;

    public LGPointDouble(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public LGPointDouble() {
        this.x = 0.0;
        this.y = 0.0;
    }

    public void reset() {
        this.x = 0.0;
        this.y = 0.0;
    }

    public void shift(Double x, Double y) {
        this.x += x;
        this.y += y;
    }

    public String toString() {
        return x + "," + y;
    }
    public Double getDistance(Point p1, Point p2) {
        // returns the absolute distance between 2 points
        return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
    }
}
