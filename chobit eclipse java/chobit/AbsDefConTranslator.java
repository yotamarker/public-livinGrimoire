package chobit;

public abstract class AbsDefConTranslator {
	private DefconTranslator defconTranslator;

	public DefconTranslator getDefconTranslator() {
		return defconTranslator;
	}

	public AbsDefConTranslator(DefconTranslator defconTranslator) {
		super();
		this.defconTranslator = defconTranslator;
	}
	public Algorithm getDefcon(String ear, String skin, String eye) {
		return defconAlg(this.defconTranslator.getDefcon(ear, skin, eye));
	}

	public String getAbsoluteDefcon(String ear, String skin, String eye) {
		String currentDefcon = getDefconTranslator().getSpecificDefcom(ear, skin, eye);
		if (currentDefcon.isEmpty()) {
			return getDefconTranslator().aquiredTarget();
		}
		return currentDefcon;
	}
	public abstract Algorithm defconAlg(int v1);
}
