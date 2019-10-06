package chobit;

import java.util.ArrayList;
import java.util.Hashtable;

public class Chobit {
	protected String emot = ""; // emotion
	protected ArrayList<AbsCmdReq> dClassesLv1 = new ArrayList<>();
	protected ArrayList<AbsCmdReq> dClassesLv2 = new ArrayList<>();
	protected ArrayList<AbsCmdReq> dClassesLv3 = new ArrayList<>();
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
	protected DCFilter dcFilter = new DCFilter(); // used for the filter func
	protected Person activePerson = new Person();
	protected PrimoCera primoCera = new PrimoCera();
	// added :
	protected Kokoro kokoro = new Kokoro(); // soul
	protected Person master = new Person();
    public Chobit() {
		super();
		noiron = new Neuron();
		this.inner = new InnerClass(); // sleep var
		DAlarmer dAlarmer = new DAlarmer();
		// add a skill here, only 1 line needed !!!
		dClassesLv1.add(new Detective(fusion));
		dClassesLv1.add(new DJirachi());

		dClassesLv1.add(new DHungry());
		dClassesLv1.add(dPermitter);
		dClassesLv1.add(new DRules((new APSleep(24)), inner));
		dClassesLv1.add(new DSpeller());
		dClassesLv1.add(new DCalculatorV1());
		dClassesLv1.add(dAlarmer);
		dClassesLv2.add(new DSayer());
		dClassesLv3.add(dAlarmer);
		dClassesLv3.add(new DDirtyTalker());
		// dClassesLv3.add(new DIMommyGf(kokoro, this.master));
		dClassesLv3.add(new DIJirachi(master, kokoro));
    }

	protected String doIt2(String ear, String skin, String eye) {
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
		DCStrPair<String> result = new DCStrPair<String>();
		result = fusion.act(ear, skin, eye);
		this.emot = fusion.getEmot();
		return itemFilter(result);
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

	protected String itemFilter(DCStrPair<String> pair) {
		if (!pair.key.equals(dcFilter.prevKey)) {
			// fold prev key(item) with switch case :
			// tie bike, put phone in place...
			// dcFilter.ignoreCounter = 3; // longer tick for longer action
		}
		// fold on key change;
		String result = "";
		// ter(item) cases
		switch (pair.key) {
		case "itemLess":
			result = pair.value;
			break;
		case "nyaa":
			if (!activePerson.getActive()) {
				// send SMS if target guy is not actively available/responding
			} else {
				result = pair.value + " nyaa";
			}
			dcFilter.imutable = true; // constant action (no need to try other movements)
			// * dcFilter.ignoreCounter = 3; // longer tick for longer action
		default:
			break;
		}
		return result;
	}

	public String doIt(String ear, String skin, String eye) {
		if (dcFilter.danger.contains(ear) || dcFilter.danger.contains(eye)) {
			return doIt2(ear, skin, eye);
		}
		if (dcFilter.ignoreCounter > 0) {
			dcFilter.ignoreCounter--;
		} else if (dcFilter.imutable) {
			return doIt2(ear, skin, eye);
		} else if (eye.contains(dcFilter.pair.toString())) {
			primoCera.saveAction(dcFilter.pair.toString(), dcFilter.actioNum);
			// savekeyvalnum (successful actual action chosen)
		} else {
			dcFilter.actioNum++;
			if (dcFilter.actioNum > primoCera.getFinalActionCode()) {
				dcFilter.actioNum = 0;
			}
			primoCera.saveAction(dcFilter.pair.toString(), dcFilter.actioNum);
			// save key++,num
			// the action did not result in the needed imidiate outcome, use a different
			// action next time
		}
		return doIt2(ear, skin, eye);
	}
	protected String translateIn() {
		return "";
	}

	protected String translateOut() {
		return "";
	}
}
