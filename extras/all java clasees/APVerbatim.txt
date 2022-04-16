package chobit;

import java.util.ArrayList;

public class APVerbatim extends AbsAlgPart {
	/*
	 * this algorithm part says each past param verbatim
	 */
	private ArrayList<String> sentences = new ArrayList<String>();
	private int at = 0;

	public APVerbatim(String... sentences) {
		for (int i = 0; i < sentences.length; i++) {
			this.sentences.add(sentences[i]);
		}
		if (0 == sentences.length) {
			at = 30;
		}
	}

	public APVerbatim(ArrayList<String> list1) {
		this.sentences = new ArrayList<String>(list1);
		if (0 == this.sentences.size()) {
			at = 30;
		}
	}

	@Override
	public String action(String ear, String skin, String eye) {
		// TODO Auto-generated method stub
		String axnStr = "";
		if (this.at < this.sentences.size()) {
			axnStr = this.sentences.get(at);
			at++;
		}
		return axnStr;
	}

	@Override
	public enumFail failure(String input) {
		// TODO Auto-generated method stub
		return enumFail.ok;
	}

	@Override
	public Boolean completed() {
		// TODO Auto-generated method stub
		return at >= this.sentences.size();
	}

	@Override
	public AbsAlgPart clone() {
		// TODO Auto-generated method stub
		return new APVerbatim(this.sentences);
	}

	@Override
	public Boolean itemize() {
		// TODO add logic
		// at home
		return true;
	}
}