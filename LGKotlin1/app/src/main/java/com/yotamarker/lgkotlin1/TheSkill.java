package com.yotamarker.lgkotlin1;

import java.util.ArrayList;

public abstract class TheSkill extends DISkill {
    protected RegexUtil regexUtil = new RegexUtil();
    protected DISkillUtils diSkillUtil = new DISkillUtils();
    protected PlayGround playGround = new PlayGround();
    protected CloudianV2 cloudian = new CloudianV2();
    private MCodes mCodes = null; // items

    public void setmCodes(MCodes mCodes) {
        this.mCodes = mCodes;
    }

    public void setFriend(Person friend) {
        this.friend = friend;
    }

    public void setAbsDefCon(AbsDefconV2 absDefCon) {
        this.absDefCon = absDefCon;
    }

    private SupeReplikaMap replikaMap = new SupeReplikaMap();
    protected Person friend = null; // if you deal with several friends handle it in the sub class
    private Boolean friendUpdatable = false;
    private ArrayList<String> items = new ArrayList<String>();
    private String item = "";
    protected AbsDefconV2 absDefCon;
    protected Algorithm outputAlg = null;
    private String clsName = "*(^^%&*";

    public void setItems(ArrayList<String> items) {
        this.items = items;
    }

    // public TheSkill(Kokoro kokoro, AbsDefconV2 absDefCon, ArrayList<String>
    // items) {
    // super(kokoro);
    // this.items = items;
    // this.absDefCon = absDefCon;
    // }

    public TheSkill(Kokoro kokoro, AbsDefconV2 absDefCon, ArrayList<String> items, String clsName) {
        super(kokoro);
        this.items = items;
        this.absDefCon = absDefCon;
        this.clsName = clsName;
    }
    @Override
    public void input(String ear, String skin, String eye) {
        detectFriend(ear);
        // func1 :
        this.outputAlg = this.absDefCon.getDefcon(ear, skin, eye);
        inputToSoul(ear, skin, eye);
        if (outputAlg != null) {
            return;
        }
        // func2
        triggeredAlgs(ear, skin, eye);
        if (!isNull(outputAlg)) {
            return;
        }
        // func3
        this.outputAlg = soulOutput(ear);
    }

    private void triggeredAlgs(String ear, String skin, String eye) {
        trgAction(ear, skin, eye);
        if (!isNull(outputAlg)) {
            return;
        }
        trgExplore(ear, skin, eye);
        if (!isNull(outputAlg)) {
            return;
        }
        trgPreserve(ear, skin, eye);
    }

    @Override
    public void output(Neuron noiron) {
        if (!isNull(this.outputAlg)) {
            noiron.algParts.add(this.outputAlg);
            this.outputAlg = null;
        }
        // after this, if there is no reference to the object,
        // it will be deleted by the garbage collector
    }

    private boolean isNull(Object obj) {
        return obj == null;
    }

    protected abstract void trgAction(String ear, String skin, String eye);
    // sensory, souled(kokoro cls directives), predicted

    protected abstract void trgExplore(String ear, String skin, String eye);

    // timed
    // Exploration and learning, Alg efficiancy tests and sort
    protected abstract void trgPreserve(String ear, String skin, String eye);
    // timed
    // items and persons preservation, causes being annoyed if repeated in day

    protected Algorithm makeFriend() {
        return diSkillUtil.verbatimGorithm(new APVerbatim("what is your name"));
    }

    protected void friendUpdate(String ear) {
        String temp = regexUtil.phoneRegex1(ear);
        if (!temp.isEmpty()) {
            friend.setPhone(temp);
        }
        temp = regexUtil.emailRegex(ear);
        if (!temp.isEmpty()) {
            friend.setEmail(temp);
        }
        temp = regexUtil.afterWord("i am ", ear);
        if (temp.isEmpty()) {
            temp = regexUtil.afterWord("my name is ", ear);
        }
        if (!temp.isEmpty()) {
            friend.setName(temp);
            friend.setActive(true);
        }
        temp = regexUtil.duplicateRegex(ear);
        if (!temp.isEmpty()) {
            friend.setJutsu(temp);
            friend.setActive(true);
        }
    }

    // key stuff detection and handling
    protected void detectFriend(String ear) {
        if (playGround.getMinutesAsInt() % 2 == 0) {
            friendUpdatable = false;
        }
        Boolean friendRequest = (ear.contains("friends") || ear.contains("my name is")) && !this.friend.getActive();
        // a friend is set to false and cleared on algPart failures
        if (friendRequest) {
            // at this case a friend volunteers himself.
            kokoro.toHeart.put("Me", "introduce");// this can be summoned in the trgPreserve in case
            // no friend.
        }
        if (ear.contains(friend.getName()) || (ear.contains(friend.getJutsu())) || friendRequest)// or friend visual
        {
            friendUpdatable = true;
            if (items.contains("friend")) {
                this.item = "friend";
            }
            // friend patch
            // the friend is active and therefore can update his info
        }
        if (friendUpdatable) {
            friendUpdate(ear);
        }
    }

    protected String currentItem(String ear, String skin, String eye) {
        for (String item : items) {
            if (eye.contains(item)) {
                return item;
            }
        }
        for (String item : items) {
            if (skin.contains(item)) {
                return item;
            }
        }
        for (String item : items) {
            if (ear.contains(item)) {
                return item;
            }
        }
        return "";
    }

    public static String strContains(String str1, String... a) {
        for (String temp : a) {
            if (str1.contains(temp)) {
                return temp;
            }
        }
        return "";
    }

    public static String strContainsList(String str1, ArrayList<String> items) {
        for (String temp : items) {
            if (str1.contains(temp)) {
                return temp;
            }
        }
        return "";
    }

    protected void inputToSoul(String ear, String skin, String eye) {
        String sensory = ear;
        String currentDefcon = this.absDefCon.getAbsoluteDefcon(ear, skin, eye);
        if (sensory.isEmpty()) {
            sensory = skin;
        }
        if (sensory.isEmpty()) {
            sensory = eye;
        }
        if (!item.equals("friend")) {
            this.item = currentItem(ear, skin, eye);
        } // friend patch
        if (!this.item.isEmpty() || !currentDefcon.isEmpty()) {
            this.replikaMap.input(item, currentDefcon, sensory);
            this.item = "";
        }
    }

    protected Algorithm soulOutput(String ear) // ear or time
    {
        String question = strContains(ear, "what", "describe", "where");
        switch (question) {
            case "where":
                String tempItem = strContainsList(ear, items);// gets item in question
                return diSkillUtil.verbatimGorithm(new APVerbatim(replikaMap.where(tempItem)));
            default:
                if (!question.isEmpty() && ear.contains(this.clsName)) {
                    return diSkillUtil.verbatimGorithm(new APVerbatim(replikaMap.answer(question)));
                }
                break;
        }
        return null;
    }

    protected Algorithm absorbedSkill(String ear, String skin, String eye, DISkill oldSkill) {
        // an adapter for upgrading DISkills to TheSkills
        Neuron tempNeuron = new Neuron();
        oldSkill.input(ear, skin, eye);
        oldSkill.output(tempNeuron);
        if (!tempNeuron.algParts.isEmpty()) {
            return tempNeuron.algParts.get(0);
        }
        return null;
    }
}
