package LivinGrimoire;

import java.util.PriorityQueue;

public class FusionCera extends Cerabellum {
    private int abort;
    private PriorityQueue<Algorithm> algQueue;
    public FusionCera(PriorityQueue<Algorithm> algQueue) {
        super();
        this.abort = 0;
        this.algQueue = algQueue;
    }

    public void setAbort(int abort) {
        this.abort = abort + 1;
    }

    @Override
    public String act(String ear, String skin, String eye) {
        // TODO Auto-generated method stub
        String result = super.act(ear, skin, eye);
        abort--;
        if (abort < 1) {
            super.setActive(false);
        } else {
            if (!super.isActive()) {
                algQueue.remove(super.alg);
            }
        }
        return result;
    }
}
