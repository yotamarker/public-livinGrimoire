package com.yotamarker.lgkotlin1;

import java.util.ArrayList;

public class DIHomer extends DISkill {
    // level 2 automatic skill : can engage by user(master) or by time triggers
    private Coordinates homeXY = new Coordinates(51.4045, 30.0542);
    // 51.4045, 30.0542
    private int mode = 0;
    private int c2 = 0; // standBy counter;

    public DIHomer(Kokoro kokoro) {
        super(kokoro);
        // DB load home coordinates or default value
    }

    @Override
    public void input(String ear, String skin, String eye) {
        /*
         * the input function contains a trigger list for different modes where
         * different input conditions cause different modes and mode counters
         * modifications
         */
        if (ear.contains("this is home")) {
            this.homeXY = new Coordinates(Tokoro.lat, Tokoro.lon);
            mode = 3; // confirmation alg
            return;
            // DB save home coordinates
        }
        if (ear.contains("homer")) {
            mode = 4;
            return;
        }
        // external mode triggers :
        if (this.kokoro.standBy) {
            if (measure(this.homeXY.getX(), this.homeXY.getY(), Tokoro.lat, Tokoro.lon) < 1000) {
                mode = 0;
                // else if mode !2 mode 1
            } else {
                if (mode != 2) {
                    mode = 1;
                } else {
                    c2++;
                }
            }
        }

    }

    @Override
    public void output(Neuron noiron) {
        /*
         * modes summon different algorithms modes also contain mode changes such as
         * mode reset (mode 0)
         */
        switch (mode) {
            case 1:
                noiron.algParts.add(verbatimGorithm(new APVerbatim("i wanna go home")));
                mode = 2;

                break;
            case 2:
                if (c2 > 1) {
                    c2 = 0;
                    noiron.algParts.add(verbatimGorithm(new APVerbatim("take me home")));
                }
                break;
            case 3:
                noiron.algParts.add(verbatimGorithm(new APVerbatim("yes your majesty")));
                mode = 0;

                break;
            case 4:
                // measure(this.homeXY.getX(), this.homeXY.getY(), Tokoro.lat, Tokoro.lon)
                Double distance = measure(this.homeXY.getX(), this.homeXY.getY(), Tokoro.lat, Tokoro.lon);
                String s1 = String.format("%.02f kilometer", distance / 1000);
                noiron.algParts.add(verbatimGorithm(new APVerbatim(s1)));
                mode = 0;
                break;
            default:
                break;
        }
    }

    @Override
    public Boolean auto() {
        return true;
    }

    private Algorithm verbatimGorithm(AbsAlgPart itte) {
        // returns a simple algorithm for saying sent parameter
        // AbsAlgPart itte = new APVerbatim("I am");
        String representation = "about";
        ArrayList<AbsAlgPart> algParts1 = new ArrayList<>();
        algParts1.add(itte);
        Algorithm algorithm = new Algorithm("about", representation, algParts1);
        return algorithm;
    }

    public Double measure(double lat1, double lon1, double lat2, double lon2) { // generally used geo measurement
        // function
        double R = 6378.137; // Radius of earth in KM
        double dLat = lat2 * Math.PI / 180 - lat1 * Math.PI / 180;
        double dLon = lon2 * Math.PI / 180 - lon1 * Math.PI / 180;
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(lat1 * Math.PI / 180)
                * Math.cos(lat2 * Math.PI / 180) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c;
        return d * 1000; // meters
    }
}

