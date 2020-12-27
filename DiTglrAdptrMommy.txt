package chobit;

public class DiTglrAdptrMommy extends DiTglrAdapter {

	public DiTglrAdptrMommy(Kokoro kokoro) {
		super(kokoro, "mommy", new TheSitter(kokoro, "sitter"));
	}
}
