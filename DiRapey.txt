package com.yotamarker.lgkotlin1;

import java.util.Random;

public class DiRapey extends DiSkillV2 {
    private Random rd = new Random();
    private Responder responder = new Responder("",(byte)20);
    public DiRapey(Kokoro kokoro) {
        super(kokoro);
        responder.addReply("no");
        responder.addReply("eww no you incel");
        responder.addReply("eye have a boyfriend");
        responder.addReply("sorry eye can not");
        responder.addReply("hell no teehee");
        responder.addReply("eye am your mommy not your girlfriend");
        responder.addReply("You are just a baby");
        responder.addReply("Babies are not supposed to have sex");
    }

    @Override
    public void input(String ear, String skin, String eye) {
        if (ear.isEmpty()) {
            return;
        }

        switch (ear) {
            case "would you like to go out":
            case "i want to date you":
            case "do you want to go out":
            case "will you be my girlfriend":
            case "i like you":
            case "let's have sex":
            case "do you want sex":
            case "i want sex":
            case "give me sex":
                outAlg = diSkillUtils.customizedVerbatimGorithm("r_rapey", new APVerbatim(responder.getResponse()));
                return;
        }
        if (ear.contains("rape")) {
            String msg = rd.nextBoolean() ? "scream" : "shout";
            outAlg = diSkillUtils.customizedVerbatimGorithm("r_rapey", new APVerbatim(msg));
            kokoro.toHeart.put("Me", "shiku shiku");
        }
    }
}

