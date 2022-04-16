package com.yotamarker.lgkotlin1;

import java.util.HashMap;

public class DiResponder extends DISkill {
    private DISkillUtils diSkillUtils = new DISkillUtils();
    private HashMap<String, Responder> responses = new HashMap<String, Responder>();
    private Responder defaultReplies;// c'tor with max replies
    private Boolean loaded = true;
    private Algorithm outAlg = null;
    private String prevContext = "nothing";
    public DiResponder(Kokoro kokoro) {
        super(kokoro);
        String defaults = kokoro.grimoireMemento.simpleLoad("defaults");
        defaultReplies = new Responder(defaults, (byte) 20);
    }

    @Override
    public void input(String ear, String skin, String eye) {
        if (ear.isEmpty()) {
            return;
        }
        if (ear.equals("hey") && loaded) {
            outAlg = diSkillUtils.customizedVerbatimGorithm("translator", new APResponder(kokoro, 0));// TODO add shyness
            loaded = false;
        }
        // AP end indicators :
        switch (ear) {
            case "ok":
            case "okay":
            case "stop":
            case "stop it":
            case "shut up":
            case "be quiet":
            case "pain":
                loaded = true;
                prevContext = ear;
                return;
        }
        if (skin.contains("pain")) {
            loaded = true;
            prevContext = ear;
            return;
        }
        if (ear.contains("repeat")) {
            String material = ear.replace("repeat", "");
            defaultReplies.addReply(material);
            kokoro.grimoireMemento.simpleSave("defaults", defaultReplies.getRepresantaionStr());
            outAlg = diSkillUtils.customizedVerbatimGorithm("translator", new APSay(1, "ok"));
            return;
        } // filter
        if (ear.contains("tell me")) {
            String material = ear.replace("tell me", "");
            if (!responses.containsKey(prevContext)) {
                String temp = kokoro.grimoireMemento.simpleLoad(prevContext);
                responses.put(prevContext, new Responder(temp));
            }
            Responder r1 = responses.get(prevContext);
            r1.addReply(material);
            kokoro.grimoireMemento.simpleSave(prevContext, r1.getRepresantaionStr());
            outAlg = diSkillUtils.customizedVerbatimGorithm("translator", new APSay(1, "ok"));
            return;
        }
        prevContext = ear;
    }

    @Override
    public void output(Neuron noiron) {
        if (!(outAlg == null)) {
            noiron.algParts.add(outAlg);
            outAlg = null;
        }
    }

}
