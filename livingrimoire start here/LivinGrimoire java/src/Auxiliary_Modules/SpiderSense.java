package Auxiliary_Modules;

import java.util.ArrayList;

public class SpiderSense {
    // enables event prediction
    private Boolean spiderSense = false;
    private final UniqueItemSizeLimitedPriorityQueue events = new UniqueItemSizeLimitedPriorityQueue();
    private final UniqueItemSizeLimitedPriorityQueue alerts = new UniqueItemSizeLimitedPriorityQueue();
    private String prev = "";
    public SpiderSense addEvent(String event){
        // builder pattern
        events.add(event);
        return this;
    }
    // input param  can be run through an input filter prior to this function
    // weather related data (sky state) only for example for weather events predictions
    public void learn(String in1){
        // simple prediction of an event from the events que :
        if (alerts.contains(in1)){
            spiderSense = true;return;
        }
        // event has occured, remember what lead to it
        if (events.contains(in1)){
            alerts.add(prev);return;
        }
        // nothing happend
        prev = in1;
    }

    public Boolean getSpiderSense() {
        // spider sense is tingling? event predicted?
        Boolean temp = spiderSense; spiderSense = false;
        return temp;
    }
    public ArrayList<String> getAlertsShallowCopy(){
        // return shallow copy of alerts list
        return alerts.getElements();
    }
    public ArrayList<String> getAlertsClone(){
        // return deep copy of alerts list
        DeepCopier dc = new DeepCopier();
        return dc.copyList(alerts.getElements());
    }
    public void clearAlerts(){
        // this can for example prevent war, because say once a month or a year you stop
        // being on alert against a rival
        alerts.clear();
    }
    public boolean eventTriggered(String in1) {
        return events.contains(in1);
    }
    // side note:
    // use separate spider sense for separate senses
    // as well as lies (false predictions)
}
