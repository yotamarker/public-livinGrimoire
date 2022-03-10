package chobit;

public class DiTglrAdapter extends DISkill {
	// adapts a skill to be toggled on or off be a DiTglrSkill
	// this skills should be added only as level1 skills, example in the chobit
	// c'tor:
	// dClassesLv1.add(new DiTglrAdptrMommy(kokoro));
	private Boolean alive = true;
	private String conjuration = "";
	private DISkill diSkill;
	public DiTglrAdapter(Kokoro kokoro, String conjuration, DISkill diSkill) {
		super(kokoro);
		this.diSkill = diSkill;
		this.conjuration = conjuration;
	}

	public DiTglrAdapter(Boolean startAsActive, Kokoro kokoro, String conjuration, DISkill diSkill) {
		super(kokoro);
		this.diSkill = diSkill;
		this.conjuration = conjuration;
		this.alive = startAsActive;
	}
	@Override
	public void input(String ear, String skin, String eye) {
		// toggle :
		String meirei = this.kokoro.toHeart.getOrDefault(conjuration, "");
		if (meirei.contains(conjuration + " off")) {
			this.alive = false;
			this.kokoro.toHeart.remove(conjuration);
			return;
		}
		if (meirei.contains(conjuration + " on")) {
			this.alive = true;
			this.kokoro.toHeart.remove(conjuration);
			return;
		}
		// engage :
		if (alive) {
			this.diSkill.input(ear, skin, eye);
		}
	}

	@Override
	public void output(Neuron noiron) {
		if (alive) {
			this.diSkill.output(noiron);
		}
	}

}
