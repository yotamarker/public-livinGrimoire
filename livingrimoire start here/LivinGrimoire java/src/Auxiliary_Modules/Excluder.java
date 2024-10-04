package Auxiliary_Modules;

import java.util.ArrayList;

public class Excluder {
    private final ArrayList<String> startsWith = new ArrayList<>();
    private final ArrayList<String> endsWith = new ArrayList<>();

    public void addStartsWith(String s1) {
        if (!startsWith.contains("^(" + s1 + ").*")) {
            startsWith.add("^(" + s1 + ").*");
        }
    }

    public void addEndsWith(String s1) {
        if (!endsWith.contains("(.*)(?=" + s1 + ")")) {
            endsWith.add("(.*)(?=" + s1 + ")");
        }
    }

    public boolean exclude(String ear) {
        for (String tempStr : startsWith) {
            if (RegexUtil.extractRegex(tempStr, ear).length() > 0) {
                return true;
            }
        }
        for (String tempStr : endsWith) {
            if (RegexUtil.extractRegex(tempStr, ear).length() > 0) {
                return true;
            }
        }
        return false;
    }
}

