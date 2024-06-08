package LivinGrimoire;

import java.util.ArrayList;
import java.util.Arrays;

public class APCldVerbatim extends Mutatable {
    /*
     * this algorithm part says each past param verbatim
     */
    private ArrayList<String> sentences = new ArrayList<String>();
    private int at = 0;
    private final CldBool cldBool; // access via shallow reference

    public APCldVerbatim(CldBool cldBool, String... sentences) {
        this.sentences.addAll(Arrays.asList(sentences));
        if (0 == sentences.length) {
            at = 30;
        }
        this.cldBool = cldBool;
        this.cldBool.setModeActive(true);
    }

    public APCldVerbatim(CldBool cldBool, ArrayList<String> list1) {
        this.sentences = new ArrayList<String>(list1);
        if (0 == this.sentences.size()) {
            at = 30;
        }
        this.cldBool = cldBool;
        this.cldBool.setModeActive(true);
    }

    @Override
    public String action(String ear, String skin, String eye) {
        String axnStr = "";
        if (this.at < this.sentences.size()) {
            axnStr = this.sentences.get(at);
            at++;
        }
        cldBool.setModeActive(!(at >= this.sentences.size()));
        return axnStr;
    }

    @Override
    public Boolean completed() {
        return at >= this.sentences.size();
    }

    @Override
    public Mutatable clone() {
        return new APCldVerbatim(cldBool, this.sentences);
    }
}
