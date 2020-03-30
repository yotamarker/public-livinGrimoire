package chobit;
import java.util.ArrayList;

// a step by step plan to achieve a goal
public class Algorithm implements Comparable<Algorithm> {
    private String goal;
    private String representation;
    private ArrayList<AbsAlgPart> algParts = new ArrayList<>();

    public Algorithm(String goal, String representation, ArrayList<AbsAlgPart> algParts) {
        super();
        this.goal = goal;
        this.representation = representation;
        this.algParts = algParts;
    }
	// *constract with string and goal

    public String getGoal() {
        return goal;
    }

    public String getRepresentation() {
        return representation;
    }

    public ArrayList<AbsAlgPart> getAlgParts() {
        return algParts;
    }

    public int getSize() {
        return algParts.size();
    }

    public Algorithm clone() {
        ArrayList<AbsAlgPart> parts = new ArrayList<>();
        for (AbsAlgPart absAlgPart : this.algParts) {
            parts.add(absAlgPart.clone());
        }
        return new Algorithm(this.goal, getRepresentation(), parts);
    }

    @Override
    public int compareTo(Algorithm o) {
        // TODO Auto-generated method stub
        return 0;
    }
}
