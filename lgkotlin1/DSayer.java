package chobit;


import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// very simple Dclass for creating a say something x times algorithm
public class DSayer extends AbsCmdReq implements Neuronable {
    private int times;
    private String param;

    public DSayer() {
        super();
        this.times = 1;
        this.param = "";
    }

    public static String regexChecker(String theRegex, String str2Check) {
        Pattern checkRegex = Pattern.compile(theRegex);
        Matcher regexMatcher = checkRegex.matcher(str2Check);
        while (regexMatcher.find()) {
            if (regexMatcher.group().length() != 0) {
                return regexMatcher.group().trim();
            }
        }
        return "";
    }

    @Override
	public void input(String ear, String skin, String eye) {
        int foo = 1;
		String myString = regexChecker("(\\d+)(?= times)", ear);
		String toSay = regexChecker("(?<=say)(.*)(?=\\d)", ear);
        if (myString != "") {
            foo = Integer.parseInt(myString);
        } else {
			toSay = regexChecker("(?<=say)(.*)", ear);
        }
        this.param = toSay;
        this.times = foo;
    }

    @Override
    public void output(Neuron noiron) {
        // TODO Auto-generated method stub
        if (!param.isEmpty()) {
            AbsAlgPart itte = new APSay(this.times, this.param);
            String representation = "say " + param;
            if (this.times > 1) {
                representation += " " + this.times + " times";
            }
            ArrayList<AbsAlgPart> algParts1 = new ArrayList<>();
            algParts1.add(itte);
            Algorithm algorithm = new Algorithm("say", representation, algParts1);
            noiron.algParts.add(algorithm);
        }

    }
}
