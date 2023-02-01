package LG_Core;

import java.util.ArrayList;
import java.util.Hashtable;

public class ChobitsLight implements thinkable {
	protected String emot = ""; // emotion
	protected ArrayList<AbsCmdReq> dClassesLv1;
	// algorithms fusion (polymarization)
	protected Hashtable<String, Integer> AlgDurations;
	protected Fusion fusion;
	// region essential DClasses
	protected Permission permission;
	protected DPermitter dPermitter;
	// endregion
	protected Neuron noiron;
	// sleep vars :
	protected Person activePerson = new Person();
	// added :
	protected Kokoro kokoro; // soul
	protected Person master = new Person();
	protected String lastOutput = "";
	// standBy phase 260320
	protected ZeroTimeGate timeGate = new ZeroTimeGate();

	public ChobitsLight(PersonalityLight personality) {
		super();
		this.AlgDurations = personality.getAlgDurations();
		this.fusion = personality.getFusion();
		this.kokoro = personality.getKokoro();
		noiron = new Neuron();
		dClassesLv1 = personality.getdClassesLv1();
	}

	public void loadPersonality(PersonalityLight personality) {
		this.AlgDurations = personality.getAlgDurations();
		this.fusion = personality.getFusion();
		this.kokoro = personality.getKokoro();
		noiron = new Neuron();
		dClassesLv1 = personality.getdClassesLv1();
	}

	public String doIt(String ear, String skin, String eye) {
		ear = translateIn(ear);
		for (AbsCmdReq dCls : dClassesLv1) {
			inOut(dCls, ear, skin, eye);
		}
		fusion.setAlgQueue(noiron);
		return translateOut(fusion.act(ear, skin, eye));
	}

	public String getSoulEmotion() {
		return kokoro.getEmot();
	}

	public String getEmot() {
		// emot (emotion for display)
		String x1 = emot;
		switch (this.emot) {
		case "APCuss ":
			x1 = "angry";
			break;
		case "APDirtyTalk":
			x1 = "grinny";
			break;
		case "APMoan":
			x1 = "horny";
			break;
		case "APSay":
			x1 = "speaking";
			break;
		case "APSleep0":
			x1 = "dreaming";
			break;
		case "APSleep":
			x1 = "asleep";
			break;
		case "APSpell":
			x1 = "blank";
			break;
		default:
			break;
		}
		emot = "";
		return x1;
	}

	protected void inOut(AbsCmdReq dClass, String ear, String skin, String eye) {
		dClass.input(ear, skin, eye); // new
		dClass.output(noiron);
	}

	protected String translateIn(String earIn) {
		// makes sure the chobit doesn't feedback on her own output
		if (earIn.equals(lastOutput)) {
			return "";
		}
		return earIn;
	}

	protected String translateOut(String outResult) {
		// save last output served
		if (!outResult.isEmpty()) {
			lastOutput = outResult;
			this.timeGate.open();
			this.kokoro.standBy = false;
		}
		// standBy :
		else {
			if (this.timeGate.isClosed()) {
				this.kokoro.standBy = true;
				this.timeGate.open();
			} else {
				this.kokoro.standBy = false;
			}
		}
		return outResult;
	}

	@Override
	public String think(String ear, String skin, String eye) {
		return doIt(ear, skin, eye);
	}

	public Boolean getStandby() {
		return kokoro.standBy;
	}
}
