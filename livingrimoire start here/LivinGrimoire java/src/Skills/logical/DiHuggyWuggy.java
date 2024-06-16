package Skills.logical;

import Auxiliary_Modules.DrawRnd;

public class DiHuggyWuggy {
    private DiActivity o1 = new DiActivity();

    public DiHuggyWuggy() {
        o1.addActivity(new DrawRnd("approaches you","wide grin"));
        o1.addActivity(new DrawRnd("hugs you"));
        o1.addActivity(new DrawRnd("hugs you tighter","nuzzles","snuggles","plays with your hair"));
    }
    public DiActivity retSkill(){return this.o1;}
}
