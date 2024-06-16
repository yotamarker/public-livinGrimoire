package LivinGrimoire;

public class DiHelloWorld extends DiSkillV2{
    // hello world skill for testing purposes
    public DiHelloWorld() {
        super();
    }

    @Override
    public void input(String ear, String skin, String eye) {
        switch (ear){
            case "hello":
                super.setVerbatimAlg(4,"hello world"); // 1->5 1 is the highest algorithm priority
                break;
        }
    }
}
