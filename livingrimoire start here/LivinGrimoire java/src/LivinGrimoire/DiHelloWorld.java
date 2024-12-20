package LivinGrimoire;

public class DiHelloWorld extends Skill {
    // hello world skill for testing purposes
    public DiHelloWorld() {
        super();
    }

    @Override
    public void input(String ear, String skin, String eye) {
        switch (ear){
            case "hello":
                super.setSimpleAlg("hello world"); // 1->5 1 is the highest algorithm priority
                break;
        }
    }
    @Override
    public String skillNotes(String param) {
        if ("notes".equals(param)) {
            return "plain hello world skill";
        } else if ("triggers".equals(param)) {
            return "say hello";
        }
        return "note unavailable";
    }
}
