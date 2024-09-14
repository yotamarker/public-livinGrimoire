package Skills.logical;

import Auxiliary_Modules.AXCmdBreaker;
import Auxiliary_Modules.Eliza;
import Auxiliary_Modules.RailChatBot;
import Auxiliary_Modules.UniqueItemSizeLimitedPriorityQueue;
import LivinGrimoire.Skill;

public class DiRailChatBot extends Skill {
    private RailChatBot rcb = new RailChatBot();
    private AXCmdBreaker dialog = new AXCmdBreaker("babe");
    private UniqueItemSizeLimitedPriorityQueue filter = new UniqueItemSizeLimitedPriorityQueue();
    private AXCmdBreaker bads = new AXCmdBreaker("is bad");
    private AXCmdBreaker goods = new AXCmdBreaker("is good");
    private String filterTemp = "";

    public void setQueLim(int lim){
        filter.setLimit(lim);
    }

    @Override
    public void input(String ear, String skin, String eye) {
        // filter learn:
        filterTemp = bads.extractCmdParam(ear);
        if (!filterTemp.isEmpty()){
            filter.add(filterTemp);
            filterTemp = "";
            setSimpleAlg("i will keep that in mind");
            return;
        }
        filterTemp = goods.extractCmdParam(ear);
        if (!filterTemp.isEmpty()){
            filter.removeItem(filterTemp);
            filterTemp = "";
            setSimpleAlg("understood");
            return;
        }
        if (filter.strContainsResponse(ear)){return;} // filter in
        String temp = dialog.extractCmdParam(ear);
        if (!temp.isEmpty()){
            String result = rcb.respondDialog(temp);
            if (filter.strContainsResponse(result)){return;} // filter out
            setSimpleAlg(Eliza.PhraseMatcher.reflect(result));
            return;
        }
        rcb.learn(ear);
    }
}
