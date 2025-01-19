package Auxiliary_Modules;

public class AnnoyedQue {
    private final RefreshQ q1 = new RefreshQ();
    private final RefreshQ q2 = new RefreshQ();

    private final RefreshQ stuffedQue = new RefreshQ();

    public AnnoyedQue(int queLim) {
        q1.setLimit(queLim);
        q2.setLimit(queLim);
        stuffedQue.setLimit(queLim);
    }
    public void learn(String ear){
        if (q1.contains(ear)){
            q2.add(ear);
            stuffedQue.stuff(ear);
            return;
        }
        q1.add(ear);
    }
    public boolean isAnnoyed(String ear){
        return q2.strContainsResponse(ear);
    }
    public void reset() {
        // Insert unique throwaway strings to reset the state
        for (int i = 0; i < this.q1.getLimit(); i++) {
            this.learn("throwaway_string_" + i);
        }
    }
    public boolean AnnoyedLevel(String ear, int level) {
        int count = 0;
        for (String item : this.stuffedQue.elements) {
            if (item.equals(ear)) {
                count++;
            }
        }
        return count > level;
    }
}
