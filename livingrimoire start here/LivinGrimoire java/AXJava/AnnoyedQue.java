package AXJava;

public class AnnoyedQue {
    private RefreshQ q1 = new RefreshQ();
    private RefreshQ q2 = new RefreshQ();

    public AnnoyedQue(int queLim) {
        q1.setLimit(queLim);
        q2.setLimit(queLim);
    }
    public void learn(String ear){
        if (q1.contains(ear)){
            q2.add(ear);
            return;
        }
        q1.add(ear);
    }
    public boolean isAnnoyed(String ear){
        return q2.strContainsResponse(ear);
    }
}
