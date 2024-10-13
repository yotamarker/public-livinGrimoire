package Auxiliary_Modules;

import java.util.Hashtable;

public class AXTimeContextResponder {
    // output reply based on the part of day as context
    public Responder morning = new Responder();
    public Responder afternoon = new Responder();
    public Responder evening = new Responder();
    public Responder night = new Responder();
    protected Hashtable<String, Responder> responders = new Hashtable<>();

    public AXTimeContextResponder() {
        responders.put("morning", morning);
        responders.put("afternoon", afternoon);
        responders.put("evening", evening);
        responders.put("night", night);
    }

    public String respond(){
        return responders.get(TimeUtils.partOfDay()).getAResponse();
    }
}
