package com.yotamarker.lgkotlin1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Responder {
    /*
     * manages reply lists
     * example summon :
     * Responder responder = new Responder("bye bye#i shall miss you#have a good day#i love you", (byte)4);
     * example usage : responder.getResponse()
     */
    private byte maxReplies = 4;
    private ArrayList<String> replies = new ArrayList<String>();
    private Random rand = new Random();

    // c'tor :
    public Responder(String material) {
        if (material.equals("null") || material.isEmpty()) {
            replies.add("");
            return;
        }
        String[] arrOfStr = material.split("#", maxReplies);
        for (int i = 0; i < arrOfStr.length - 1; i++) {
            replies.add(arrOfStr[i]);
        }
    }

    // c'tor ver 2 : for default replies
    public Responder(String material, byte maxreplies) {
        this.maxReplies = maxreplies;
        if (material.equals("null") || material.isEmpty()) {
            replies.add("");
            return;
        }
        String[] arrOfStr = material.split("#", maxReplies);
        for (int i = 0; i < arrOfStr.length; i++) {
            replies.add(arrOfStr[i]);
        }
    }
    public byte getMaxReplies() {
        return maxReplies;
    }

    public void setMaxReplies(byte maxReplies) {
        this.maxReplies = maxReplies;
    }
    public String getResponse(String key) {
        // get random response out of list
        int x = rand.nextInt(replies.size());
        String reply = replies.get(x);
        return reply;
    }

    public String getResponse(int shyness) {
        // can result in response or "" depends on shyness level, the higher the more
        // likely to get ""
        int x = rand.nextInt(replies.size() + shyness);
        if (x > replies.size()) {
            return "";
        }
        String reply = replies.get(x);
        return reply;
    }

    public String getResponse() {
        // can result in response or "" depends on shyness level, the higher the more
        // likely to get ""
        int x = rand.nextInt(replies.size());
        String reply = replies.get(x);
        return reply;
    }
    public String getRepresantaionStr() {
        // convert list to 1 string, presaving
        String result = "";
        for (String reply : replies) {
            result = result + reply + "#";
        }
        return result;
    }

    public void addReply(String reply) {
        replies.add(reply);
        Collections.shuffle(replies);
        if (replies.size() > maxReplies) {
            replies.remove(replies.size() - 1);
        }
    }
    public void removeReply(String reply) {
        replies.remove(reply);
        if (replies.isEmpty()) {
            replies.add("");
        }
    }
}
