package com.yotamarker.lgkotlin1;

public class Point{
    public int x;
    public int y;
    public Point(Point p)
    {
        x = p.x;
        y = p.y;
    }
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
    public Point getLocation(){
        return new Point(x, y);
    }
    public String toString(){
        return getClass().getName() + "[x=" + x + ",y=" + y + ']';
    }
}
