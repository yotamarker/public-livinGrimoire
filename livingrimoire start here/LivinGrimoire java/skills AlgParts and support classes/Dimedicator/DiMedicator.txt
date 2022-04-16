package com.yotamarker.lgkotlin1;

import java.util.ArrayList;

public class DiMedicator extends DiSkillV2 {
    private String task = "nothing";
    private Boolean taskActive = false;
    private Boolean inContext = false;
    private PlayGround pl = new PlayGround();
    private String blockedTime = "";
    private ArrayList<String> times = new ArrayList<String>();
    public DiMedicator(Kokoro kokoro) {
        super(kokoro);
        String t1 = kokoro.grimoireMemento.simpleLoad("r_medicator_bool");
        taskActive = t1.equals("true");
        task = kokoro.grimoireMemento.simpleLoad("r_medicator");
        // load task, taskActive
        // load is active
        times.add("06:24");
        times.add("16:24");
        times.add("18:24");
    }

    @Override
    public void input(String ear, String skin, String eye) {
        // time launch
        String now = pl.getCurrentTimeStamp();
        Boolean b1 = taskActive;
        Boolean b2 = times.contains(now);
        Boolean b3 = now.equals(blockedTime);
        if (!b3 && b1 && b2) {
            verySimpleAlg(task);
            blockedTime = now;
            inContext = true;
        }
        if (ear.isEmpty()) {
            return;
        } // exit condition
        if (inContext) {
            switch (ear) {
                case "this is boring":
                case "finished":
                case "done":
                    taskActive = false;
                    kokoro.grimoireMemento.simpleSave("r_medicator_bool","false");
                    inContext = false;
                    verySimpleAlg("ok");
                    return;
                case "ok":
                case "yes":
                    inContext = false;
                    verySimpleAlg("good boy");
                    return;
                case "no":
                    inContext = false;
                    verySimpleAlg("good boy");
                    return;
                default:
                    inContext = false;// ear is not empty, something was said the order was ignored
                    break;
            }
        }
        // enable task
        if (ear.equals("give me homework")) {
            taskActive = true;
            kokoro.grimoireMemento.simpleSave("r_medicator_bool","true");
            verySimpleAlg("sure thing " + task);
            // save
        }
        // new task
        if (ear.contains("set homework")) {
            task = ear.replace("set homework", "").trim();
            kokoro.grimoireMemento.simpleSave("r_medicator",task);
            verySimpleAlg("got it");
            // save task
        }
        // get task
        switch (ear) {
            case "what do i need to do":
            case "what is my task":
                if (taskActive) {
                    verySimpleAlg(task);
                } else {
                    verySimpleAlg("you have no active task");
                }
                return;
            default:
                break;
        }
    }
    private void verySimpleAlg(String toSay) {
        outAlg = diSkillUtils.customizedVerbatimGorithm("r_medicator", new APSay(1, toSay));
    }
}
