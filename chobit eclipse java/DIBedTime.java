package chobit;

import java.util.ArrayList;

public class DIBedTime extends DISkill {
	/*
	 * a simple skill at bed time she will sing a lullabye next if the user
	 * addresses the AGI she will scold him the skill ensures the AGI has quite time
	 * the scold mode is active for 1 hour after the lullabye the user can reset the
	 * trigger bedtime by saying "lv2name intHour is bedtime" this is an automatic
	 * lv2 skill can be stand alone or as a set of the DIJirachi skill the time
	 * trigger cannot be set at the time it is set.
	 */
	private boolean standbye = true;
	private PlayGround playGround = new PlayGround();
	private int trigHour = 20;
	private int algToUse = 0; // 0 nothing 1 lullabye, 2 scold
	private RegexUtil regexUtil = new RegexUtil();
	public DIBedTime(Kokoro kokoro) {
		super(kokoro);
	}

	@Override
	public void input(String ear, String skin, String eye) {
		int nowHour = playGround.getHoursAsInt(); // the current hour
		// set up alg to summon :
		if (standbye) {
			if (trigHour == nowHour) {
				algToUse = 1;
				this.setSentAlg(true);
				standbye = false;
				return;
			}
		} else {
			if (nowHour != trigHour) {
				standbye = true;
			} else if (!ear.isEmpty()) {
				algToUse = 2;
				this.setSentAlg(true);
			}
			// return;
		}
		// check if user wants a different bedtime :
		String bedTime = regexUtil.regexChecker("(\\d+)(?= is bedtime)", ear);
		if (bedTime != "") {
			trigHour = Integer.parseInt(bedTime);
			if (trigHour > 24 || trigHour < 0) {
				trigHour = 20;
			}
		}
	}

	@Override
	public void output(Neuron noiron) {
		switch (algToUse) {
		case 1:
			noiron.algParts.add(lullabye());
			// this.setSentAlg(true);
			break;
		case 2:
			noiron.algParts.add(scold());
			// this.setSentAlg(true);
			break;
		default:
			break;
		}
		algToUse = 0;
	}

	private Algorithm lullabye() {
		AbsAlgPart itte = new Chi(this.kokoro, this.getClass().getSimpleName(), new APSay(1, "lullabye"));
		String representation = "lullabye";
		ArrayList<AbsAlgPart> algParts1 = new ArrayList<>();
		algParts1.add(itte);
		Algorithm algorithm = new Algorithm("lullabye", representation, algParts1);
		return algorithm;
	}

	private Algorithm scold() {
		AbsAlgPart itte = new Chi(this.kokoro, this.getClass().getSimpleName(), new APSay(1, "go to sleep"));
		String representation = "scold";
		ArrayList<AbsAlgPart> algParts1 = new ArrayList<>();
		algParts1.add(itte);
		Algorithm algorithm = new Algorithm("scold", representation, algParts1);
		return algorithm;
	}

	@Override
	public Boolean auto() {
		// TODO Auto-generated method stub
		return true;
	}
}
