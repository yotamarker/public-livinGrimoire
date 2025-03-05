package auxiliary_modules

import livinGrimoire.Kokoro
import java.util.HashSet

class ElizaDBWrapper {
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
    private val modifiedKeys = HashSet<String>()
    fun respond(in1: String, ec: EventChatV2, kokoro: Kokoro): String {
        if (modifiedKeys.contains(in1)) {
            return ec.response(in1)
        }
        modifiedKeys.add(in1)
        // load
        ec.addFromDB(in1, kokoro.grimoireMemento.simpleLoad(in1))
        return ec.response(in1)
    }

    fun respondLatest(in1: String, ec: EventChatV2, kokoro: Kokoro): String {
        if (modifiedKeys.contains(in1)) {
            return ec.responseLatest(in1)
        }
        modifiedKeys.add(in1)
        // load and get latest reply for input
        ec.addFromDB(in1, kokoro.grimoireMemento.simpleLoad(in1))
        return ec.responseLatest(in1)
    }

    fun sleepNSave(ecv2: EventChatV2, kokoro: Kokoro) {
        ecv2.modifiedKeys.forEach { element ->
            kokoro.grimoireMemento.simpleSave(
                element,
                ecv2.getSaveStr(element)
            )
        }
    }
}