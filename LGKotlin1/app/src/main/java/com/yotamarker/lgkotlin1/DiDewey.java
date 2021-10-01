package com.yotamarker.lgkotlin1;

import java.util.Hashtable;

public class DiDewey extends DiSkillV2 {
    private String reader = "";
    private String temp = "";
    private int mode = 0;
    private RegexUtil regexUtil = new RegexUtil();
    private Hashtable<String, String> my_dict = new Hashtable<String, String>();
    private Responder responder = new Responder("yes#ok#chii#shouryuken#hadouken#moti",6);
    private Responder responder2 = new Responder("the data has been cleared#bye bye#ok#shouryuken#hadouken#bitchpudding",6);
    public DiDewey(Kokoro kokoro) {
        super(kokoro);
        for (int i = 1; i < 11; i++) {
            my_dict.put(i + "", kokoro.grimoireMemento.simpleLoad(i+""));
        }
        // load
    }

    @Override
    public void input(String ear, String skin, String eye) {
        // mode shifter
        switch (ear) {
            case "read 1":case "read one":
                this.mode = 1;
                this.outAlg = this.diSkillUtils.simpleVerbatimAlgorithm("dewey", "yes your majesty");
                return;
            case "read 2":case "read two":case "read to":
                this.mode = 2;
                this.outAlg = this.diSkillUtils.simpleVerbatimAlgorithm("dewey", "yes your majesty");
                return;
            case "read 3":case "read three":
                this.mode = 3;
                this.outAlg = this.diSkillUtils.simpleVerbatimAlgorithm("dewey", "yes your majesty");
                return;
            case "read 4":case "read four":
                this.mode = 4;
                this.outAlg = this.diSkillUtils.simpleVerbatimAlgorithm("dewey", "yes your majesty");
                return;
            case "read 5":case "read five":
                this.mode = 5;
                this.outAlg = this.diSkillUtils.simpleVerbatimAlgorithm("dewey", "yes your majesty");
                return;
            case "read 6":case "read six":
                this.mode = 6;
                this.outAlg = this.diSkillUtils.simpleVerbatimAlgorithm("dewey", "yes your majesty");
                return;
            case "read 7":case "read seven":
                this.mode = 7;
                this.outAlg = this.diSkillUtils.simpleVerbatimAlgorithm("dewey", "yes your majesty");
                return;
            case "read 8":case "read eight":
                this.mode = 8;
                this.outAlg = this.diSkillUtils.simpleVerbatimAlgorithm("dewey", "yes your majesty");
                return;
            case "read 9":case "read nine":
                this.mode = 9;
                this.outAlg = this.diSkillUtils.simpleVerbatimAlgorithm("dewey", "yes your majesty");
                return;
            case "read 10":case "read ten":
                this.mode = 10;
                this.outAlg = this.diSkillUtils.simpleVerbatimAlgorithm("dewey", "yes your majesty");
                return;
            case "forget":case "clear data":case "forget about the packages":case "forget about the parcels":
                clearData();
                return;
            default:
                break;
        }
        if (mode > 0) {
            if (ear.equals("done")) {
                my_dict.put(mode + "", reader);
                kokoro.grimoireMemento.simpleSave(mode+"",reader);
                reader = "";
                mode = 0;
                this.outAlg = this.diSkillUtils.simpleVerbatimAlgorithm("dewey_done", "finished");
                return;
            }
            temp = regexUtil.numberRegex(ear);
            if (!temp.isEmpty()) {
                if(temp.length()==1){temp="00"+temp;}
                if(temp.length()==2){temp="0"+temp;}
                reader = reader + temp + " ";
                this.outAlg = this.diSkillUtils.simpleVerbatimAlgorithm("dewey_plus", responder.getResponse());
                temp = "";
                return;
            }
            temp = "";
        }
        // find
        if (ear.startsWith("find")) {
            temp = regexUtil.numberRegex(ear);
            if (!temp.isEmpty()) {
                if(temp.length()==1){temp="00"+temp;}
                if(temp.length()==2){temp="0"+temp;}
                Boolean foundParcel=false;
                String result = "";
                for (int i = 1; i < 11; i++) {
                    if (my_dict.get(i + "").contains(temp)) {
                        foundParcel=true;
                        result=result + i;
                    }
                }
                if(foundParcel){
                    this.outAlg = this.diSkillUtils.simpleVerbatimAlgorithm("dewey_res", result);
                    return;
                }
                this.outAlg = this.diSkillUtils.simpleVerbatimAlgorithm("dewey_res", "i do not know");
            }
        }

    }
    private void clearData(){
        for (int i = 1; i < 11; i++) {
            my_dict.put(i + "", "");
            kokoro.grimoireMemento.simpleSave(i+"","null");
        }
        this.outAlg = this.diSkillUtils.simpleVerbatimAlgorithm("dewey_res", responder2.getResponse());
    }
}

