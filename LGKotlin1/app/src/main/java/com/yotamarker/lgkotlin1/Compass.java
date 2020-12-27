package com.yotamarker.lgkotlin1;

public class Compass {
    // input degress via external static var using setDegrees function
    private static float degrees = 0.0f;
    private static int direction = 0;
    private static ZeroTimeGate timeGate = new ZeroTimeGate(0);
    private static float TOLERANCE = 5.0f;

    public static void SetDegrees(float newDegress) {
        if (Math.abs(degrees - newDegress) > TOLERANCE) {
            timeGate.open(3);
        }
        direction = (int) (newDegress / 45);
    }

    public static Boolean isActive() {
        return timeGate.isOpen();
    }

    public static String getDirectionStr() {
        switch (direction) {
            case 0:
                return "north";
            case 1:
                return "northeast";
            case 2:
                return "east";
            case 3:
                return "southeast";
            case 4:
                return "south";
            case 5:
                return "southwest";
            case 6:
                return "west";
            case 7:
                return "northwest";
            default:
                break;
        }
        return "";
    }

    public static int getDirection() {
        return direction;
    }
}
