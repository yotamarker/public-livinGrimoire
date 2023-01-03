package LivinGrimoire;

import java.util.ArrayList;

// used to transport algorithms to other classes
public class Neuron {
    public ArrayList<Algorithm> algParts = new ArrayList<>();
    public ArrayList<Algorithm> negativeAlgParts = new ArrayList<>();
    public void empty() {
        this.algParts.clear();
        this.negativeAlgParts.clear();
    }
}
