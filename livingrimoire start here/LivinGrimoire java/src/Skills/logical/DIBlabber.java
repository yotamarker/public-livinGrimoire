package Skills.logical;

import Auxiliary_Modules.*;
import LivinGrimoire.Skill;

public class DIBlabber extends Skill {
    private Boolean isActive = true; // skill toggle
    public AXContextCmd skillToggler = new AXContextCmd();
    // chat mode select
    public AXContextCmd modeSwitch = new AXContextCmd();
    protected Cycler mode = new Cycler(1); // chat-bot mode
    // engage chatbot
    public AXContextCmd engage = new AXContextCmd();
    // chatbots
    public AXNPC2 chatbot1 = new AXNPC2(30,90); // pal mode chat module
    public AXNPC2 chatbot2 = new AXNPC2(30,90); // discreet mode chat module
    // auto mode
    private Responder autoEngage = new Responder("engage automatic mode","automatic mode", "auto mode");
    private Responder shutUp = new Responder("stop","shut up", "silence", "be quite", "be silent");
    private TimeGate tg = new TimeGate(5);
    private int nPCPlus = 5; // increase rate of output in self auto reply mode
    private int nPCNeg = -10; // decrease rate of output in self auto reply mode
    public DIBlabber(String skill_name) {
        skillToggler.contextCommands.add("toggle " + skill_name);
        skillToggler.commands.add("again");skillToggler.commands.add("repeat");
        modeSwitch.contextCommands.add("switch " + skill_name + " mode");
        modeSwitch.commands.add("again");modeSwitch.commands.add("repeat");
        engage.contextCommands.add("engage " + skill_name);engage.commands.add("talk");
        engage.commands.add("talk to me");
        engage.commands.add("again");engage.commands.add("repeat");
        mode.setToZero();
    }

    @Override
    public void input(String ear, String skin, String eye) {
        // skill toggle:
        if(skillToggler.engageCommand(ear)){isActive = !isActive;}
        if (!isActive){return;}
        // chat-bot mode switch mode
        if(modeSwitch.engageCommand(ear)){
            mode.cycleCount();
            setVerbatimAlg(4, talkMode());;
            return;
        }
        switch (mode.getMode()){
            case 0:
                mode0(ear);
                break;
            case 1:
                mode1(ear);
                break;
        }
    }
    private void mode0(String ear) {
        if (!kokoro.toHeart.getOrDefault("diblabber","").isEmpty()){
            kokoro.toHeart.put("diblabber","");
            setVerbatimAlg(4, chatbot1.forceRespond());
            return;
        }
        NPCUtilization(chatbot1,ear);
    }

    private void mode1(String ear) {
        // auto engage
        if(autoEngage.responsesContainsStr(ear)){
            tg.openGate();
            setVerbatimAlg(4,"auto NPC mode engaged");
            return;
        }
        if(shutUp.responsesContainsStr(ear)){
            tg.close();
            setVerbatimAlg(4,"auto NPC mode disengaged");
            return;
        }
        if (tg.isOpen()){
            int plus = nPCNeg;
            if (!ear.isEmpty()){plus = nPCPlus;}
            String result = chatbot2.respondPlus(plus);
            if (!result.isEmpty()) {
                setVerbatimAlg(4, result);
                return;
            }
        }
        // end auto engage code snippet
        NPCUtilization(chatbot2, ear);
    }
    private String talkMode(){
        switch (mode.getMode()){
            case 0:
                return "friend mode";
            case 1:
                return "discreet mode";
        }
        return "mode switched";
    }
    // auto mode setters
    public void setNPCTimeSpan(int n){
        tg.setPause(n);
    }
    public void setNPCNeg(int n){
        // lower NPC auto output chance
        nPCNeg = n;
    }
    public void setNPCPlus(int n){
        // increase NPC auto output chance
        nPCPlus = n;
    }
    // chat module common tasks
    private void NPCUtilization(AXNPC2 npc, String ear){
        String result = "";
        // engage
        if (engage.engageCommand(ear)) {
            result = npc.respond();
            if (!result.isEmpty()) {
                setVerbatimAlg(4, result);
                return;
            }
        }
        // str engage
        result = npc.strRespond(ear);
        if (!result.isEmpty()) {
            setVerbatimAlg(4, result);
        }
        // forced learn (say n)
        if (!npc.learn(ear)){
            // strlearn
            npc.strLearn(ear);
        }
    }
}
