package Skills.logical;

import Auxiliary_Modules.DrawRnd;
import Auxiliary_Modules.Responder;
import LivinGrimoire.Skill;

import java.util.ArrayList;

public class DiActivity extends Skill {
    /* for multistep activities
    uses step machine code algorithm to
    step between sub activities of activity
    see DiHuggyWuggy for example skill
    * */
    private ArrayList<DrawRnd> activities = new ArrayList<>();
    private int index = -1;
    // commands
    private String start = "start activity";
    private String stop = "stop";
    private Responder skip = new Responder("skip","next","ok");
    private String doNext = "";
    public void setStart(String start) {
        this.start = start;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }

    public void setSkip(Responder skip) {
        this.skip = skip;
    }
    public void addActivity(DrawRnd drawRnd){activities.add(drawRnd);}
    @Override
    public void input(String ear, String skin, String eye) {
        if(ear.equals(start)){
            index=0;
            for (int i = 0; i < activities.size(); i++) {
                activities.get(i).reset();
            }
        } // start activity
        // activity on?
        if(index>-1){
            if(ear.equals(stop)){index = -1;return;} // stop activity
            else if((skip.responsesContainsStr(ear))||activities.get(index).isEmptied()){
                index++; // skip current activity
            }
            if (index > activities.size()-1){index=-1;return;} // no more activities?
            doNext = activities.get(index).draw();
//            setSimpleAlg(activities.get(index).draw());
        }
        if (doNext.isEmpty()){return;}
        // you can expand the activity to a specialized algorithm here:
        switch (doNext) {
            default:
                setSimpleAlg(doNext);
                doNext = "";
                break;
        }
    }
}
