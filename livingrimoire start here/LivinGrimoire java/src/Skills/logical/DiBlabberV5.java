package Skills.logical;

import Auxiliary_Modules.*;
import LivinGrimoire.Skill;

import java.util.ArrayList;

public class DiBlabberV5 extends Skill {
    private AXNPC2 npc;
    private String tempStr = "";
    private OnOffSwitch autoTalk = new OnOffSwitch();
    private String funnel = "";
    public AXContextCmd cntxtcmd = new AXContextCmd();

    public DiBlabberV5(int memorySize, int replyChance) {
        super();
        this.npc = new AXNPC2(memorySize, replyChance);
        this.npc.cmdBreaker = new AXCmdBreaker("tell me");
        this.autoTalk.setOn(new Responder("filth on"));
        cntxtcmd.contextCommands.add("princess");
        cntxtcmd.commands.add("more");
        cntxtcmd.commands.add("again");
    }
    public DiBlabberV5() {
        super();
        this.npc = new AXNPC2(9, 90);
        this.npc.cmdBreaker = new AXCmdBreaker("tell me");
        this.autoTalk.setOn(new Responder("filth on"));
        cntxtcmd.contextCommands.add("princess");
        cntxtcmd.commands.add("more");
        cntxtcmd.commands.add("again");
    }

    public DiBlabberV5 addResponses(String... responses) {
        for (String str1 : responses) {
            this.npc.responder.getElements().add(str1);
        }
        return this;
    }

    public DiBlabberV5 setResponses(String... responses) {
        this.npc.responder.setElements(new ArrayList<String>());
        for (String str1 : responses) {
            this.npc.responder.getElements().add(str1);
        }
        return this;
    }

    public void input(String ear, String skin, String eye) {
        // auto talk mode
        if (this.autoTalk.getMode(ear)) {
            String t = this.npc.respond();
            if (t.length() > 0) {
                this.setSimpleAlg(Eliza.PhraseMatcher.reflect(t));
                return;
            }
        }
        if (ear.isEmpty()) {
            return;
        }
        if (cntxtcmd.engageCommand(ear)){
            this.setSimpleAlg(Eliza.PhraseMatcher.reflect(this.npc.respond()));
            return;
        }
        // funnel
        this.funnel = ear.replace("tell me how", "tell me");
        this.funnel = this.funnel.replace("tell me to", "tell me");
        // blabber
        this.tempStr = this.npc.strRespond(this.funnel);
        if (this.tempStr.length() > 0) {
            this.setSimpleAlg(Eliza.PhraseMatcher.reflect(this.npc.forceRespond()));
        }
        this.npc.learn(this.funnel);
    }
}

