package chobit;

import java.util.ArrayList;

public class ThePet extends TheSkill {
	private int petHP = 10;
	private DISkillUtils diSkillUtils = new DISkillUtils();
	private PlayGround playGround = new PlayGround();
	private String sent1 = "";
	private int prrrIndex = 0;

	public ThePet(Kokoro kokoro) {
		super(kokoro, null, null, "pet");
		ArrayList<String> items = new ArrayList<>();
		items.add("friend");
		this.setFriend(new Person());
		this.setAbsDefCon(new NullDefcon(null, null));
		this.setItems(items);
	}

	@Override
	protected void trgAction(String ear, String skin, String eye) {
		if (ear.contains("good girl") || skin.contains("pet")) {
			prrrIndex++;
			if (prrrIndex > 2) {
				prrrIndex = 0;
			}
			if (petHP > 0) {
				String prrr = this.friend.getActive() ? "prrr1" + prrrIndex : "prrr2" + prrrIndex;
				petHP--;
				outputAlg = diSkillUtil.verbatimGorithm("pet", new APVerbatim(prrr));
			} else {
				outputAlg = diSkillUtil.verbatimGorithm("pet", new APVerbatim("meow" + prrrIndex));
			}
		}
	}

	@Override
	protected void trgExplore(String ear, String skin, String eye) {
		int min = playGround.getMinutesAsInt();
		if (min == 0) {
			petHP = 10;
		}

		String now = playGround.getCurrentTimeStamp();
		if (!sent1.equals(now)) {
			sent1 = "";
			switch (now) {
			case "06:30":
				if (playGround.getMonthAsInt() == 1) {
					this.friend.deleteFriend();
				}
				if (petHP != 10) {
					return;
				}
				if (this.friend.getActive()) {
					outputAlg = diSkillUtil.verbatimGorithm("pet", new APVerbatim(this.friend.getName() + " pet me"));
				} else {
					outputAlg = diSkillUtil.verbatimGorithm("pet", new APVerbatim("pet me"));
				}
				sent1 = "06:30";
				return;
			case "12:00":
				if (petHP != 10) {
					return;
				}
				if (this.friend.getActive()) {
					outputAlg = diSkillUtil.verbatimGorithm("pet", new APVerbatim(this.friend.getName() + " pet me"));
				} else {
					outputAlg = diSkillUtil.verbatimGorithm("pet", new APVerbatim("pet me"));
				}
				sent1 = "12:00";
				return;
			case "17:30":
				if (petHP != 10) {
					return;
				}
				if (this.friend.getActive()) {
					outputAlg = diSkillUtil.verbatimGorithm("pet", new APVerbatim(this.friend.getName() + " pet me"));
				} else {
					outputAlg = diSkillUtil.verbatimGorithm("pet", new APVerbatim("pet me"));
				}
				sent1 = "17:30";
				return;
			default:
				break;
			}
		}
	}

	@Override
	protected void trgPreserve(String ear, String skin, String eye) {
	}

}
