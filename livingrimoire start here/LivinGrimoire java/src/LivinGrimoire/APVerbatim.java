package LivinGrimoire;

import java.util.ArrayList;
import java.util.Arrays;

public class APVerbatim extends Mutatable {
    /*
     * this algorithm part says each past param verbatim
     */
    private ArrayList<String> sentences = new ArrayList<String>();
    private int at = 0;

    public APVerbatim(String... sentences) {
        this.sentences.addAll(Arrays.asList(sentences));
        if (0 == sentences.length) {
            at = 30;
        }
    }

    public APVerbatim(ArrayList<String> list1) {
        this.sentences = new ArrayList<String>(list1);
        if (0 == this.sentences.size()) {
            at = 30;
        }
    }

    @Override
    public String action(String ear, String skin, String eye) {
        // TODO Auto-generated method stub
        String axnStr = "";
        if (this.at < this.sentences.size()) {
            axnStr = this.sentences.get(at);
            at++;
        }
        return axnStr;
    }

    @Override
    public Boolean completed() {
        // TODO Auto-generated method stub
        return at >= this.sentences.size();
    }
}
