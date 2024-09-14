package LivinGrimoire;

public class DiSysOut extends Skill {
    @Override
    public void input(String ear, String skin, String eye) {
        if (!ear.isEmpty() & !ear.contains("#")){
            System.out.println(ear);
        }
    }
}
