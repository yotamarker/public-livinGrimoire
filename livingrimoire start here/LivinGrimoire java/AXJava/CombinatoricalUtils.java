package AXJava;

import java.util.ArrayList;
import java.util.List;

public class CombinatoricalUtils {
    public ArrayList<String> result;

    public ArrayList<String> getResult() {
        return result;
    }

    private void generatePermutations(ArrayList<ArrayList<String>> lists, List<String> result, int depth, String current) {
        if (depth == lists.size()) {
            result.add(current);
            return;
        }

        for (int i = 0; i < lists.get(depth).size(); i++) {
            generatePermutations(lists, result, depth + 1, current + lists.get(depth).get(i));
        }
    }
    public void generatePermutations(ArrayList<ArrayList<String>> lists){
        result = new ArrayList<String>();
        generatePermutations(lists, this.result, 0, "");
    }
    public void generatePermutations(ArrayList<String>... lists){
        ArrayList<ArrayList<String>> ll = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < lists.length; i++) {
            ll.add(lists[i]);
        }
        result = new ArrayList<String>();
        generatePermutations(ll, this.result, 0, "");
    }
}
