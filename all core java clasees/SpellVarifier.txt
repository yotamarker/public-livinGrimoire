package chobit;

public class SpellVarifier {
	private String spell = "";
	private Boolean spellCast = false;
	private String spellLeftOver = "";
	private Double minSpell = 20.0;

	public SpellVarifier(String spell) {
		super();
		this.spell = spell.toLowerCase();
		this.spellLeftOver = spell.toLowerCase();
	}

	public void reset() {
		spellCast = false;
		spellLeftOver = this.spell;
	}

	public void spellAbsorb(String attempt) {
		spellLeftOver = spellLeftOver.replace(attempt, "");
		double x = spellLeftOver.length();
		double x2 = spell.length();
		double n = x / x2;
		spellCast = n < 0.5;
	}

	public String getSpell() {
		return spell;
	}

	public void setSpell(String spell) {
		this.spell = spell.toLowerCase();
	}

	public Double getMinSpell() {
		return minSpell;
	}

	public void setMinSpell(Double minSpell) {
		if (minSpell > 99 || minSpell < 10) {
			this.minSpell = 20.0;
			return;
		}
		this.minSpell = minSpell;
	}

	public Boolean getSpellCast() {
		return spellCast;
	}

}
