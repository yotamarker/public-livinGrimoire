package chobit;


import java.util.ArrayList;

public class DSpeller extends AbsCmdReq implements Neuronable {
	private String param = "";
    private Boolean active;

    @Override
    public void output(Neuron noiron) {
		switch (this.param) {
		case "about":
			param = "";
			noiron.algParts.add(
					verbatimGorithm(new APVerbatim("I am", "the living grimoire", "I was created by", "moti barski")));
			return;
		case "creator":
			param = "";
			noiron.algParts.add(verbatimGorithm(new APVerbatim("I was created by", "moti barski")));
			return;
		default:
			break;
		}
        if (this.active) {
            APSpell maho = new APSpell(this.param);
            ArrayList<AbsAlgPart> algParts1 = new ArrayList<>();
            algParts1.add(maho);
            Algorithm algorithm = new Algorithm("spell", this.param, algParts1);
            noiron.algParts.add(algorithm);
        }
    }

    @Override
	public void input(String ear, String skin, String eye) {
        // TODO Auto-generated method stub
		switch (ear) {
            case "what is the time":
            case "what is the date":
            case "what is the year":
            case "current seconds":
            case "current minutes":
            case "current hour":
            case "which day is it":
            case "greet":
                active = true;
			this.param = ear;
                break;
		case "what are you":
			this.param = "about";
			break;
		case "who made you":
			this.param = "creator";
			break;
            default:
                active = false;
                break;

        }
    }

	private Algorithm verbatimGorithm(AbsAlgPart itte) {
		// returns a simple algorithm for saying sent parameter
		// AbsAlgPart itte = new APVerbatim("I am");
		String representation = "about";
		ArrayList<AbsAlgPart> algParts1 = new ArrayList<>();
		algParts1.add(itte);
		Algorithm algorithm = new Algorithm("about", representation, algParts1);
		return algorithm;
	}
}
