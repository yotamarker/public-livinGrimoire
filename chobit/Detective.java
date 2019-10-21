package chobit;


import java.util.ArrayList;

/*handles dager type input :
 * pain, repeated input, cussing, danger
 * */
public class Detective extends AbsCmdReq implements Neuronable {
    private String mimi, prevMimi; // ear input
    private int cussCounter; // must be > cussing action
    private String cry;
    private String imBusy;
    private String requestRepeated;
    private ArrayList<String> cusses = new ArrayList<String>();
    private Fusion fusion;

    public Detective(Fusion fusion) {
        super();
        this.fusion = fusion;
        this.mimi = "";
        this.prevMimi = "";
        this.imBusy = "chill";
        this.requestRepeated = "fineeeeeeeeeeeeeeeeeeeeeee";
		this.cry = "buhi";
        this.cussCounter = 0;
        cusses.add("shit");
        cusses.add("fuck");
        cusses.add("jizz");
        cusses.add("");
        cusses.add(cry);
    }

    @Override
    public void output(Neuron noiron) {
        // TODO Auto-generated method stub
        if (cussCounter > 3) {
            cussCounter = 0;
            noiron.negativeAlgParts.add(loadCuss());
            return;
        }
        if (fusion.getReqOverload()) {
            badReqConditions(this.imBusy, noiron);
            return;
        }
        if (fusion.getRepReq()) {
            badReqConditions(this.requestRepeated, noiron);
            // System.out.println("again with this shit ? :");
        }
    }

    @Override
	public void input(String ear, String skin, String eye) {
        // TODO Auto-generated method stub
        this.mimi = ear;
        if ((this.mimi.equals(this.prevMimi)  || this.cusses.contains(this.mimi)) && !this.mimi.isEmpty()) {
            // || this.cusses.contains(this.mimi) enables old cuss recognition without
            // repetition
            this.cussCounter++;
            if (!cusses.contains(mimi)) {
                cusses.set((int) (Math.random() * 3 + 1) - 1, mimi);
            }
        }
        this.prevMimi = mimi;
    }

    private Algorithm loadCuss() {
        APCuss apCuss = new APCuss(cry, cusses.get((int) (Math.random() * cusses.size() + 1) - 1));
        ArrayList<AbsAlgPart> algParts = new ArrayList<>();
        algParts.add(apCuss);
        String represantation = "cuss"; // *match
        Algorithm algorithm = new Algorithm("cuss", represantation, algParts);
        return algorithm;
    }

    private Algorithm loadReplay(String replay) {
        APSay apCuss = new APSay(1, replay);
        ArrayList<AbsAlgPart> algParts = new ArrayList<>();
        algParts.add(apCuss);
        String represantation = "say " + replay; // *match
        Algorithm algorithm = new Algorithm("say", represantation, algParts);
        return algorithm;
    }

    private void badReqConditions(String replay, Neuron noiron) {
        int actionX = (int) (Math.random() * 2 + 1) - 1;
        switch (actionX) {
            case 0:
                noiron.negativeAlgParts.add(loadCuss());
                break;
            default:
                noiron.negativeAlgParts.add(loadReplay(replay));
                break;
        }
    }
}
