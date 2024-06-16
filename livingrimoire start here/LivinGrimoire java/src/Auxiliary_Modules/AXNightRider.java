package Auxiliary_Modules;

public class AXNightRider {
    // night rider display simulation for LED lights count up than down
    private int mode = 0;
    private int position = 0;
    private int lim = 0;
    private int direction = 1;

    public AXNightRider(int limit) {
        if (limit > 0) {
            this.lim = limit;
        }
    }

    public void setLim(int lim) {
        // number of LEDs
        this.lim = lim;
    }

    public void setMode(int mode) {
        // room for more modes to be added
        if (mode > -1 && mode < 10) {
            this.mode = mode;
        }
    }

    public int getPosition() {
        switch (mode){
            case 0:
                mode0();
                break;
        }
        return position;
    }

    private void mode0() {
        // clasic night rider display
        position += direction;
        if (direction < 1) {
            if (position < 1) {
                position = 0;
                direction = 1;
            }
        } else {
            if (position > lim - 1) {
                position = lim;
                direction = -1;
            }
        }
    }
}
