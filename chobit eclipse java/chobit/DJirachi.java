package chobit;

import java.util.ArrayList;

public class DJirachi extends AbsCmdReq implements Neuronable {
	private ArrayList<String> triggerList = new ArrayList<>();
	final private String startTrigger = "cheer me";
	final private String stopTrigger = "ok";
	final private String cheer = "user fighto";
	private Boolean mode = false;
	private Boolean doIt = false;

	public DJirachi() {
		// TODO Auto-generated constructor stub
		triggerList.add("help me");
		triggerList.add("chii");
	}

	@Override
	public void output(Neuron noiron) {
		// TODO Auto-generated method stub
		if (doIt) {
			AbsAlgPart itte = new APSay(1, cheer);
			String representation = "cheer";
			ArrayList<AbsAlgPart> algParts1 = new ArrayList<>();
			algParts1.add(itte);
			Algorithm algorithm = new Algorithm("cheer", representation, algParts1);
			noiron.algParts.add(algorithm);
			doIt = false;
		}
	}

	@Override
	public void input(String ear, String skin, String eye) {
		if (ear.equals(stopTrigger)) {
			mode = false;
			return;
		}
		if (ear.equals(startTrigger)) {
			mode = true;
			return;
		}
		if (mode && triggerList.contains(ear)) {
			doIt = true;
		}
	}
}
