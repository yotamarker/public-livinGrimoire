package chobit;

import java.util.ArrayList;

public class Me extends TheSkill {
	public Permission permission;
	public Person person = new Person();
	private DISkillUtils diSkillUtil = new DISkillUtils();
	private int outAlg = 0;
	private Boolean wasMute = false;

	public Me(Kokoro kokoro, AbsDefconV2 absDefCon, ArrayList<String> items) {
		super(kokoro, absDefCon, items, "");
		this.permission = permission;
		this.person.setName(this.permission.getLv1Name());
		this.person.setJutsu("hadouken");
	}

	@Override
	public void input(String ear, String skin, String eye) {
		if (ear.isEmpty()) {
			wasMute = true;
		} else {
			wasMute = false;
		}
		String soulMsg = kokoro.toHeart.getOrDefault("Me", "");
		kokoro.toHeart.put("Me", "");
		if (soulMsg.contains("introduce")) {
			outAlg = 1;

		}
	}

	@Override
	public void output(Neuron noiron) {
		switch (outAlg) {
		case 1:
			if (wasMute) {
			ArrayList<String> list1 = new ArrayList<>();
			list1.add("my name is " + this.person.getName());
			list1.add(person.getJutsu() + " " + person.getJutsu());
			list1.add("lets be friends");
			noiron.algParts.add(diSkillUtil.verbatimGorithm(new APVerbatim(list1)));
				outAlg = 0;
			}
			break;

		default:
			break;
		}
	}

	@Override
	protected void trgAction(String ear, String skin, String eye) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void trgExplore(String ear, String skin, String eye) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void trgPreserve(String ear, String skin, String eye) {
		// TODO Auto-generated method stub

	}
}
