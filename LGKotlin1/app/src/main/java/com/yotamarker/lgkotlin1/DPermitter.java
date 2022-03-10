package com.yotamarker.lgkotlin1;


import java.util.ArrayList;

/* handles permissions using 2 names for the chobit :
 * name one : permission lv1 (engages with friend)
 * name 2 : permission level2 (for doing things with the owner only)
 * */
public class DPermitter extends AbsCmdReq implements Neuronable {
    /*
     * conjuratis : change pass : oldPass new password newPassword change lv1 name :
     * pass your name is newName change lv2 name : pass you are newName
     */
    Permission permission;
    private int permissionLevel = 2;
    private int relevantCase = 0;
    private RegexUtil regexer = new RegexUtil();
    public DPermitter(Permission permission) {
        super();
        this.permission = permission;
    }

    @Override
    public void output(Neuron noiron) {
        switch (this.relevantCase) {
            case 1:
                requipSayAlg(noiron, permission.getLv1Name());
                break;
            case 2:
                requipSayAlg(noiron, "got it");
                break;
            case 3:
                requipSayAlg(noiron, "new nickname has been established");
                break;
            case 4:
                requipSayAlg(noiron, "new password accepted");
                break;
            default:
                break;
        }
        this.relevantCase = 0;
    }

    @Override
    public void input(String ear, String skin, String eye) {
        permission.setPermissionLevel(ear);
        this.permissionLevel = this.permission.getPermissionLevel();
        if (ear.contains("what is your name")) {
            this.relevantCase = 1;
            return;
        }
        String password = regexer.regexChecker("(\\w+)(?= you are)", ear);
        String newName = regexer.regexChecker("(?<=you are)(.*)", ear);
        if (permission.setLv2Name(password, newName)) {
            this.relevantCase = 2;
            return;
        }
        ;
        password = regexer.regexChecker("(\\w+)(?= your name is)", ear);
        newName = regexer.regexChecker("(?<=your name is)(.*)", ear);
        if (permission.setLv1Name(password, newName)) {
            this.relevantCase = 3;
            return;
        }
        ;
        String oldPass = regexer.regexChecker("(\\w+)(?= new password)", ear);
        String newPass = regexer.regexChecker("(?<=new password)(.*)", ear);
        if (permission.setPassword(oldPass, newPass)) {
            this.relevantCase = 4;
            return;
        }
        ;
    }

    public int getPermissionLevel() {
        return permissionLevel;
    }

    private void requipSayAlg(Neuron noiron, String replay) {
        AbsAlgPart itte = new APSay(1, replay);
        ArrayList<AbsAlgPart> algParts1 = new ArrayList<>();
        algParts1.add(itte);
        String represantation = "say " + replay;
        Algorithm algorithm = new Algorithm("say", represantation, algParts1);
        noiron.algParts.add(algorithm);
    }
}