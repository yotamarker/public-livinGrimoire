package com.yotamarker.lgkotlin1;

import java.util.ArrayList;
import java.util.Hashtable;

public class Chobit {
	protected String emot = ""; // emotion
	protected ArrayList<AbsCmdReq> dClassesLv1 = new ArrayList<>();// can engage with anyone
	protected ArrayList<AbsCmdReq> dClassesLv2 = new ArrayList<>();// can engage with friends and work related
	protected ArrayList<AbsCmdReq> dClassesLv3 = new ArrayList<>();// can engage only by user
	protected ArrayList<AbsCmdReq> dClassesAuto = new ArrayList<>();// automatically added and engaged by time
	// algorithms fusion (polymarization)
	protected Hashtable<String, Integer> AlgDurations = new Hashtable<>();
	protected Fusion fusion = new Fusion(AlgDurations);
	// region essential DClasses
	protected Permission permission = Permission.newInstance("xxx", "chii", "liron");
	protected DPermitter dPermitter = new DPermitter(permission);
	// endregion
	protected Neuron noiron;
	// sleep vars :
	protected InnerClass inner;
	protected Person activePerson = new Person();
	protected PrimoCera primoCera = new PrimoCera();
	// added :
	protected Kokoro kokoro; // soul
	protected Person master = new Person();
	protected String lastOutput = "";

	public Chobit(AbsDictionaryDB absDictionaryDB) {
		super();
		this.kokoro = new Kokoro(absDictionaryDB);
		noiron = new Neuron();
		this.inner = new InnerClass(); // sleep var
		// add a skill here, only 1 line needed !!!
		dClassesLv1.add(new Detective(fusion));
		// dClassesLv1.add(new DJirachi());
		// dClassesLv1.add(new DIAutomatic(kokoro, master));
		// dClassesLv1.add(new DIBedTime(kokoro));
		// dClassesLv1.add(new DHungry());
		// dClassesLv1.add(new DIBurper(kokoro));
		dClassesLv1.add(dPermitter);
		dClassesLv1.add(new DRules((new APSleep(24)), inner));
		dClassesLv1.add(new DIJoker(kokoro));
		dClassesLv1.add(new DSpeller());
		dClassesLv1.add(new DISoulV2(kokoro));
		// dClassesLv1.add(new DCalculatorV1());
		dClassesLv2.add(new DSayer());
		// dClassesLv3.add(new DAlarmer());
		dClassesLv3.add(new DIDirty(kokoro));
		// dClassesLv3.add(new DIJirachi(master, kokoro));
		formAutoClassesList();
	}

	public Chobit() {
		super();
		this.kokoro = new Kokoro(new AbsDictionaryDBShadow());
		noiron = new Neuron();
		this.inner = new InnerClass(); // sleep var
		// add a skill here, only 1 line needed !!!
		dClassesLv1.add(new Detective(fusion));
		// dClassesLv1.add(new DJirachi());
		// dClassesLv1.add(new DIAutomatic(kokoro, master));
		// dClassesLv1.add(new DIBedTime(kokoro));
		// dClassesLv1.add(new DHungry());
		// dClassesLv1.add(new DIBurper(kokoro));
		dClassesLv1.add(dPermitter);
		dClassesLv1.add(new DRules((new APSleep(24)), inner));
		dClassesLv1.add(new DIJoker(kokoro));
		dClassesLv1.add(new DSpeller());
		dClassesLv1.add(new DISoulV2(kokoro));
		// dClassesLv1.add(new DCalculatorV1());
		dClassesLv2.add(new DSayer());
		// dClassesLv3.add(new DAlarmer());
		dClassesLv3.add(new DIDirty(kokoro));
		// dClassesLv3.add(new DIJirachi(master, kokoro));
		formAutoClassesList();
	}

	protected void formAutoClassesList() {
		// adds automatic skills so they can be engaged by time
		for (AbsCmdReq dCls : dClassesLv2) {
			if (dCls.auto()) {
				dClassesAuto.add(dCls);
			}
		}
		for (AbsCmdReq dCls : dClassesLv3) {
			if (dCls.auto()) {
				dClassesAuto.add(dCls);
			}
		}
	}
	public String doIt(String ear, String skin, String eye) {
		ear = translateIn(ear);
		for (AbsCmdReq dCls : dClassesAuto) {
			inOut(dCls, "", skin, eye);
		}
		for (AbsCmdReq dCls : dClassesLv1) {
			inOut(dCls, ear, skin, eye);
		}
		if (dPermitter.getPermissionLevel() > 0) {
			// works with friends
			for (AbsCmdReq dCls : dClassesLv2) {
				inOut(dCls, ear, skin, eye);

			}
		}
		if (dPermitter.getPermissionLevel() > 1) {
			// only works with owner
			for (AbsCmdReq dCls : dClassesLv3) {
				inOut(dCls, ear, skin, eye);
			}
		}
		fusion.setAlgQueue(noiron);
		return translateOut(fusion.act(ear, skin, eye));
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

	protected String sleep() {
		// data save load should go here and run while chobit is sleeping
		return "haha I can sleep !";
	}

	protected void inOut(AbsCmdReq dClass, String ear, String skin, String eye) {
		dClass.input(ear, skin, eye); // new
		dClass.output(noiron);
	}

	protected class InnerClass {
		public String nemure() {
			return sleep();
		}
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
		}
		return outResult;
	}
}
