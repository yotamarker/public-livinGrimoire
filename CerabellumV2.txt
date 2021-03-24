package com.yotamarker.lgkotlin1;

import android.app.Activity;
import android.content.Context;

public class CerabellumV2 implements actionable{
    private Boolean screen = true;
    private String time = "";
    private String place = "";
    private String item = "";
    private String person = "";
    private int number = 0;
    private MainActivity context=null;
    private RegexUtil regexUtil = new RegexUtil();
    public CerabellumV2(MainActivity context) {
        this.context = context;
    }
    @Override
    public void act(String thought) {
        /*
        * gets the chobit result string (chobit.doIt)
        * and converts it to an action*/
        if(thought.isEmpty()){context.clearTxtBox();return;}
        String action = regexCmd(thought);
        //case, functions or variables from the mainactivity are engaged as needed
        //modify as needed
        switch(action) {
            case "change screen":
                if(screen){context.screenFlip1();}
                else {context.screenFlip2();}
                screen = !screen;
                context.clearTxtBox();
                break;
            default:
                context.cerabellumSpeak(action);
        }

    }
    private String regexCmd(String thought){
        //populate the global vars and set the action (return string value to run in the act functions switch case)
        //this is used for actions that use regexed params such as sending an SMS
        return thought;
    }
    private void clearGlobalVars(){}
}
