package com.yotamarker.lgkotlin1;

import java.util.ArrayList;
import java.util.Hashtable;

public class ChobitV2 {
    protected String emot = ""; // emotion
    protected ArrayList<AbsCmdReq> dClassesLv1;
    protected ArrayList<AbsCmdReq> dClassesLv2;// can engage with friends and work related
    protected ArrayList<AbsCmdReq> dClassesLv3;// can engage only by user
    protected ArrayList<AbsCmdReq> dClassesAuto = new ArrayList<>();// automatically added and engaged by time
    // algorithms fusion (polymarization)
    protected Hashtable<String, Integer> AlgDurations;
    protected Fusion fusion;
    // region essential DClasses
    protected Permission permission;
    protected DPermitter dPermitter;
    // endregion
    protected Neuron noiron;
    // sleep vars :
    protected Person activePerson = new Person();
    protected PrimoCera primoCera = new PrimoCera();
    // added :
    protected Kokoro kokoro; // soul
    protected Person master = new Person();
    protected String lastOutput = "";
    // standBy phase 260320
    protected TimeGate timeGate = new TimeGate();
    public ChobitV2(Personality personality) {
        super();
        this.AlgDurations=personality.getAlgDurations();
        this.fusion=personality.getFusion();
        this.kokoro = personality.getKokoro();
        permission=personality.getPermission();
        dPermitter=personality.getdPermitter();
        noiron = new Neuron();
        dClassesLv1=personality.getdClassesLv1();
        dClassesLv2=personality.getdClassesLv2();
        dClassesLv3=personality.getdClassesLv3();
        formAutoClassesList();
    }
public void loadPersonality(Personality personality){
    this.AlgDurations=personality.getAlgDurations();
    this.fusion=personality.getFusion();
    this.kokoro = personality.getKokoro();
    permission=personality.getPermission();
    dPermitter=personality.getdPermitter();
    noiron = new Neuron();
    dClassesLv1=personality.getdClassesLv1();
    dClassesLv2=personality.getdClassesLv2();
    dClassesLv3=personality.getdClassesLv3();
    dClassesAuto = new ArrayList<>();
    formAutoClassesList();
}
    protected void formAutoClassesList() {
        // adds automatic skills so they can be engaged by time
        for (AbsCmdReq dCls : dClassesLv2) {
            if (dCls.auto()) {
                dClassesAuto.add(dCls);
            }
        }
        for (AbsCmdReq dCls : dClassesLv3) {
            if (dCls.auto()) {
                dClassesAuto.add(dCls);
            }
        }
    }
    public String doIt(String ear, String skin, String eye) {
        ear = translateIn(ear);
        for (AbsCmdReq dCls : dClassesAuto) {
            inOut(dCls, "", skin, eye);
        }
        for (AbsCmdReq dCls : dClassesLv1) {
            inOut(dCls, ear, skin, eye);
        }
        if (dPermitter.getPermissionLevel() > 0) {
            // works with friends
            for (AbsCmdReq dCls : dClassesLv2) {
                inOut(dCls, ear, skin, eye);

            }
        }
        if (dPermitter.getPermissionLevel() > 1) {
            // only works with owner
            for (AbsCmdReq dCls : dClassesLv3) {
                inOut(dCls, ear, skin, eye);
            }
        }
        fusion.setAlgQueue(noiron);
        return translateOut(fusion.act(ear, skin, eye));
    }
    public String getSoulEmotion(){return kokoro.getEmot();}
    public String getEmot() {
        // emot (emotion for display)
        String x1 = emot;
        switch (this.emot) {
            case "APCuss ":
                x1 = "angry";
                break;
            case "APDirtyTalk":
                x1 = "grinny";
                break;
            case "APMoan":
                x1 = "horny";
                break;
            case "APSay":
                x1 = "speaking";
                break;
            case "APSleep0":
                x1 = "dreaming";
                break;
            case "APSleep":
                x1 = "asleep";
                break;
            case "APSpell":
                x1 = "blank";
                break;
            default:
                break;
        }
        emot = "";
        return x1;
    }

    protected void inOut(AbsCmdReq dClass, String ear, String skin, String eye) {
        dClass.input(ear, skin, eye); // new
        dClass.output(noiron);
    }

    protected String translateIn(String earIn) {
        // makes sure the chobit doesn't feedback on her own output
        if (earIn.equals(lastOutput)) {
            return "";
        }
        return earIn;
    }

    protected String translateOut(String outResult) {
        // save last output served
        if (!outResult.isEmpty()) {
            lastOutput = outResult;
            this.timeGate.close();
            this.kokoro.standBy = false;
        }
        // standBy :
        else {
            if (!this.timeGate.isClosed()) {
                this.kokoro.standBy = true;
                this.timeGate.close();
            } else {
                this.kokoro.standBy = false;
            }
        }
        return outResult;
    }
}
