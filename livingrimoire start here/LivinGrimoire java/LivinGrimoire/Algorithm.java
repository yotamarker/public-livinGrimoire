package LivinGrimoire;

import java.util.ArrayList;

// a step-by-step plan to achieve a goal
public class Algorithm {
    private ArrayList<Mutatable> algParts = new ArrayList<>();

    public Algorithm(ArrayList<Mutatable> algParts) {
        super();
        this.algParts = algParts;
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
        return new Algorithm(parts);
    }
}

