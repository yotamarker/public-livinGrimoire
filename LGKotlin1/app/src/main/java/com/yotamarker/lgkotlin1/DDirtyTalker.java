package com.yotamarker.lgkotlin1;


import java.util.ArrayList;

// generates an algorithm with dirty talking and then moaning too.
public class DDirtyTalker extends AbsCmdReq implements Neuronable {
    private String choice = "";
    ArrayList<String> choices = new ArrayList<>();

    public DDirtyTalker() {
        super();
        choices.add("fuck");
    }

    private String param;
    private RegexUtil regexUtil = new RegexUtil();
    private ArrayList<AbsAlgPart> algParts;
    private String represantation = "";
    @Override
    public void output(Neuron noiron) {
        // TODO Auto-generated method stub
        switch (choice) {
            case "fuck":
                APDirtyTalk apDirtyTalk = new APDirtyTalk(new MockDB().getHashtable());
                APMoan1 moan = new APMoan1(); // default
                algParts = new ArrayList<>();
                algParts.add(apDirtyTalk);
                algParts.add(moan);
                represantation = "dirty talk fuck";
                Algorithm algorithm = new Algorithm("fuck", represantation, algParts);
                noiron.algParts.add(algorithm);
                this.choice = "";
                break;

            default:
                break;
        }
    }

    @Override
	public void input(String ear, String skin, String eye) {
        // TODO Auto-generated method stub
        // regex here
		String param = regexUtil.regexChecker("(?<=dirty talk)(.*)", ear);
        if (choices.contains(param)) {
            choice = param;
        }

    }

}
