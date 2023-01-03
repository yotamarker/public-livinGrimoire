package LivinGrimoire;

import java.util.ArrayList;

// a step-by-step plan to achieve a goal
public class Algorithm {
    private String goal;
    private String representation;
    private ArrayList<Mutatable> algParts = new ArrayList<>();

    public Algorithm(String goal, String representation, ArrayList<Mutatable> algParts) {
        super();
        this.goal = goal.isEmpty()? "unknown": goal;
        this.representation = representation.isEmpty()? "unknown":representation;
        this.algParts = algParts;
    }
    // *constract with string and goal

    public String getGoal() {
        return goal;
    }

    public String getRepresentation() {
        return representation;
    }

    public ArrayList<Mutatable> getAlgParts() {
        return algParts;
    }

    public int getSize() {
        return algParts.size();
    }

    public Algorithm clone() {
        // returns a deep copy algorithm
        ArrayList<Mutatable> parts = new ArrayList<>();
        for (Mutatable absAlgPart : this.algParts) {
            parts.add(absAlgPart.clone());
        }
        return new Algorithm(this.goal, getRepresentation(), parts);
    }
}

