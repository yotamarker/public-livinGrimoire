package chobit;

public class TestAbsDefconTranslator extends AbsDefConTranslator {
	private DISkillUtils diSkillUtils = new DISkillUtils();

	public TestAbsDefconTranslator() {
		super(TestDefconTranslatorBuilder.testBuild());
	}

	@Override
	public Algorithm defconAlg(int v1) {
		switch (v1) {
		case 1:
			return diSkillUtils.verbatimGorithm(new APVerbatim("detected visual threat"));

		case 2:
			return diSkillUtils.verbatimGorithm(new APVerbatim("detected audio defcon"));
		case 3:
		case 4:
			return diSkillUtils.verbatimGorithm(new APVerbatim("detected deduced defcon"));
		default:
			break;
		}
		return null;
	}

}
