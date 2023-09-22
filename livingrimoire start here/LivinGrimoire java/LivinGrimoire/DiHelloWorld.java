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
                this.outAlg = this.diSkillUtils.simpleVerbatimAlgorithm("hello world");
                this.outpAlgPriority = 4;
                break;
        }
    }
}
