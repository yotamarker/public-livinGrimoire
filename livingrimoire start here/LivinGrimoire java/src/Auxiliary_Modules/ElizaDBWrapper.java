package Auxiliary_Modules;

import LivinGrimoire.Kokoro;

import java.util.HashSet;

public class ElizaDBWrapper {
    // this (function wrapper) class adds save load functionality to the ElizaDeducer Object
    /*
        ElizaDeducer ed = new ElizaDeducerInitializer(2);
        ed.getEc2().addFromDB("test","one_two_three"); // manual load for testing
        Kokoro k = new Kokoro(new AbsDictionaryDB()); // use skill's kokoro attribute
        ElizaDBWrapper ew = new ElizaDBWrapper();
        System.out.println(ew.respond("test", ed.getEc2(), k)); // get reply for input, tries loading reply from DB
        System.out.println(ew.respond("test", ed.getEc2(), k)); // doesn't try DB load on second run
        ed.learn("a is b"); // learn only after respond
        ew.sleepNSave(ed.getEc2(), k); // save when bot is sleeping, not on every skill input method visit
*/

    private final HashSet<String> modifiedKeys = new HashSet<>();
    public String respond(String in1, EventChatV2 ec,Kokoro kokoro){
        if(modifiedKeys.contains(in1)){return ec.response(in1);}
        modifiedKeys.add(in1);
        // load
        ec.addFromDB(in1, kokoro.grimoireMemento.simpleLoad(in1));
        return ec.response(in1);
    }
    public String respondLatest(String in1, EventChatV2 ec,Kokoro kokoro){
        if(modifiedKeys.contains(in1)){return ec.responseLatest(in1);}
        modifiedKeys.add(in1);
        // load and get latest reply for input
        ec.addFromDB(in1, kokoro.grimoireMemento.simpleLoad(in1));
        return ec.responseLatest(in1);
    }
    public void sleepNSave(EventChatV2 ecv2,Kokoro kokoro){
        ecv2.getModifiedKeys().forEach(element -> {
            kokoro.grimoireMemento.simpleSave(element, ecv2.getSaveStr(element));
        });
    }
}
