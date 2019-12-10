package com.yotamarker.lgkotlin1;
import java.util.ArrayList;

/*creates algorithms with alg parts that can become dormant
 * can wake a dormant algorithm
 * should use notify if you add more cloudians to a wake one of many said algorithm
 * */
public class DAlarmer extends AbsCmdReq implements Neuronable {
	private RegexUtil regexUtil = new RegexUtil();
	private String jikan = "";
	private Cloudian cloudian = new Cloudian();
	private String representation = "";
	private Boolean summon = false;
	private String ear = "";

	@Override
	public void output(Neuron noiron) {
		if (summon) {
			// AbsAlgPart itte = new APCldAlarm(cloudian, jikan);
			AbsAlgPart itte = new APCldAlarm(cloudian, jikan);
			ArrayList<AbsAlgPart> algParts1 = new ArrayList<>();
			algParts1.add(itte);
			Algorithm algorithm = new Algorithm("alm", representation, algParts1);
			this.cloudian.setAlgorithm(algorithm); // ***
			noiron.algParts.add(algorithm);
			reset();
			return;
		}
		if (this.cloudian.updateStat(ear, "")) {
			noiron.algParts.add(this.cloudian.getAlgorithm());
		}
	}

	private void reset() {
		representation = "";
		summon = false;
	}

	@Override
	public void input(String ear, String skin, String eye) {
		this.ear = ear;
		jikan = regexUtil.regexChecker("\\d+:\\d+", ear);
		if (eye.contains("wake me at") && !jikan.isEmpty()) {
			summon = true;
			representation = ear;
		}
	}

	@Override
	public Boolean auto() {
		// this skill can be triggered by time
		return true;
	}
}
