package LivinGrimoire;

import LivinGrimoire.DiSkillV2;

public class DiSysOut extends DiSkillV2 {
    @Override
    public void input(String ear, String skin, String eye) {
        if (!ear.isEmpty() & !ear.contains("#")){
            System.out.println(ear);
        }
    }
}
