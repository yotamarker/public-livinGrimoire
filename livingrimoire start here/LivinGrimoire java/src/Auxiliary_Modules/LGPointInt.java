package Auxiliary_Modules;

import java.awt.*;

public class LGPointInt {
    public int x;
    public int y;
    public LGPointInt(Point p) {
        x = p.x;
        y = p.y;
    }

    public LGPointInt(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public LGPointInt() {
        this.x = 0;
        this.y = 0;
    }

    public void reset() {
        this.x = 0;
        this.y = 0;
    }

    public void moveBy(int x, int y) {
        this.x += x;
        this.y += y;
    }
    public Point getLocation() {
        return new Point(x, y);
    }

    public String toString() {
        return x + "," + y;
    }
    public Double getDistance(Point p1, Point p2) {
        // returns the absolute distance between 2 points
        return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
    }
}
