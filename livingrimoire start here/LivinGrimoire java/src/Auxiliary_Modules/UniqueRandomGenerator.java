package Auxiliary_Modules;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UniqueRandomGenerator {
    private final List<Integer> numbers;
    private List<Integer> remainingNumbers;

    public UniqueRandomGenerator(int n1) {
        this.numbers = new ArrayList<>();
        for (int i = 0; i < n1; i++) {
            this.numbers.add(i);
        }
        this.remainingNumbers = new ArrayList<>();
        reset();
    }

    public void reset() {
        this.remainingNumbers = new ArrayList<>(this.numbers);
        Collections.shuffle(this.remainingNumbers);
    }

    public int getUniqueRandom() {
        if (this.remainingNumbers.isEmpty()) {
            reset();
        }
        return this.remainingNumbers.remove(this.remainingNumbers.size() - 1);
    }
}

