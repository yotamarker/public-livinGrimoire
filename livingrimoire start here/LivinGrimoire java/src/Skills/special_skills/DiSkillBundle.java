package Skills.special_skills;

import Auxiliary_Modules.AXSkillBundle;
import Auxiliary_Modules.AlgorithmV2;
import Auxiliary_Modules.UniqueResponder;
import LivinGrimoire.Skill;
import LivinGrimoire.Kokoro;

import java.util.Hashtable;

public class DiSkillBundle extends Skill {
    protected final AXSkillBundle axSkillBundle = new AXSkillBundle();
    protected final Hashtable<String, UniqueResponder> notes = new Hashtable<String, UniqueResponder>() {{
        put("triggers", new UniqueResponder());
    }};


    @Override
    public void input(String ear, String skin, String eye) {
        AlgorithmV2 a1 = axSkillBundle.dispenseAlgorithm(ear, skin, eye);
        if(a1 == null){return;}
        this.outAlg = a1.getAlg();
        this.outpAlgPriority = a1.getPriority();
    }

    @Override
    public void setKokoro(Kokoro kokoro) {
        super.setKokoro(kokoro);
        axSkillBundle.setKokoro(kokoro);
    }
    public void manualAddResponse(String key, String value) {
        notes.computeIfAbsent(key, k -> new UniqueResponder(value)).addResponse(value);
    }



    public void addSkill(Skill skill) {
        axSkillBundle.addSkill(skill);
        for (int i = 0; i < 10; i++) {
            this.notes.get("triggers").addResponse("grind " + skill.skillNotes("triggers"));
        }
    }
    public void setDefaultNote() {
        notes.put("notes", new UniqueResponder("a bundle of several skills"));
    }

    @Override
    public String skillNotes(String param) {
        if (notes.containsKey(param)) {
            return notes.get(param).getAResponse();
        }
        return "notes unavailable";
    }

}
