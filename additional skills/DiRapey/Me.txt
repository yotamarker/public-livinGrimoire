package com.yotamarker.lgkotlin1;

import java.util.ArrayList;

public class Me extends DiSkillV2 {
    public Permission permission;
    public Person person = new Person();
    private DISkillUtils diSkillUtil = new DISkillUtils();
    public Me(Kokoro kokoro, Permission permission) {
        super(kokoro);
        this.permission = permission;
        this.person.setName(this.permission.getLv1Name());
        this.person.setJutsu("pffft");
    }

    @Override
    public void input(String ear, String skin, String eye) {
        if (kokoro.standBy) {
            String soulMsg = kokoro.toHeart.getOrDefault("Me", "");
            kokoro.toHeart.put("Me", "");
            switch (soulMsg) {
                case "introduce":
                    outAlg = algDispenser(0);
                    break;
                case "attention":
                    outAlg = algDispenser(1);
                case "general":
                    outAlg = algDispenser(2);
                default:
                    if (soulMsg.isEmpty()) {
                        outAlg = algDispenser(1);
                    } else {
                        outAlg = diSkillUtil.verbatimGorithm("r_me", new APVerbatim(soulMsg));
                    }
                    break;
            }
        }
    }

    private Algorithm algDispenser(int num) {
        switch (num) {
            case 0:
                // introduce give details and befriend
                ArrayList<String> list1 = new ArrayList<>();
                list1.add("my name is " + this.person.getName());
                list1.add(person.getJutsu() + " " + person.getJutsu());
                list1.add("lets be friends");
                return diSkillUtil.verbatimGorithm("r_me", new APVerbatim(list1));
            case 1:
                // general attention 1
                return diSkillUtil.verbatimGorithm("r_me", new APVerbatim(person.getJutsu() + " " + person.getJutsu()));
            case 2:
                // seeking friend
                return diSkillUtil.verbatimGorithm("r_me", new APVerbatim("my name is " + this.person.getName()));
            default:
                break;
        }
        return null;
    }
}
