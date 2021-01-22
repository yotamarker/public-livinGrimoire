package chobit;

import java.util.ArrayList;
import java.util.Random;

public class DISoulV2 extends DISkill {
	// skill enables chobit to talk like a chobit, say hi to engage, or auto engages
	// at envoke times, waits for any reply
	private ArrayList<String> pain = new ArrayList<String>();
	private String prevInput = "hagane";
	private CloudianMode cloudianMode = new CloudianMode();
	private String algShipment = "";

	private ArrayList<String> greet = new ArrayList<String>();
	private ArrayList<String> envoke = new ArrayList<String>();
	private String lastEnvoke = "";
	private PlayGround playGround = new PlayGround();

	private ArrayList<String> talks = new ArrayList<String>();

	private int c = 0;
	private int silenceLength = 4; // max no input length during convo to end convo
	private int counter = 0;
	private int convoLength = 6;// max convo length
	private Random rand = new Random();
	private Boolean dish = true;

	private String plusTrigger = "";
	private Boolean soughtAttention = false;

	public DISoulV2(Kokoro kokoro) {
		super(kokoro);
		pain.add("pain");
		greet.add("hi");
		envoke.add("7:00");
		envoke.add("16:05");
		envoke.add("18:30");
		talks.add("chiinormal");
		talks.add("chiihappy");
		talks.add("chiiexcited");
		talks.add("chiicurious");
		talks.add("chiiangry");
	}

	@Override
	public void input(String ear, String skin, String eye) {
		// store warning input as needed
		if (pain.contains(ear) || pain.contains(skin)) {
			if (!talks.contains(prevInput)) {
				talks.add(prevInput);
			}
		} else {
			prevInput = ear;
		}

		// select mode *************************************************************
		if (cloudianMode.getMode() == 0) {
			if (greet.contains(ear)) {
				algShipment = "hello";
				plusTrigger = this.playGround.timeInXMinutes(-5);
			} else if ((envoke.contains(playGround.getCurrentTimeStamp())
					|| plusTrigger.equals(playGround.getCurrentTimeStamp())) && !envoke.contains(lastEnvoke)) {
				algShipment = "attention";
				soughtAttention = true;
				lastEnvoke = playGround.getCurrentTimeStamp();
			} else if (!ear.isEmpty() && soughtAttention) {
				cloudianMode.setMode(2);
				soughtAttention = false;
			}
		}
		// **************************************************************************
		switch (cloudianMode.getMode()) {
		case 1:
			if (!ear.isEmpty()) {
				bureiki();
				c = 0;
				if (ear.contains("chii") || !talks.contains(ear)) {
					reply();
				} else {
					algShipment = "chiisad";
				}
			} else {
				c++;
				if (c > silenceLength) {
					reset();
				}
			}
			break;
		case 2:
			if (dish) {
				reply();
				dish = false;
			} else {
				if (ear.isEmpty()) {
					c++;
					if (c > silenceLength) {
						reset();
					} else {
						dish = true;
						bureiki();
					}
				}
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void output(Neuron noiron) {
		if (!algShipment.isEmpty()) {
			if (algShipment.equals("attention")) {
				noiron.algParts.add(attentionAlg());
				algShipment = "";
			} else {
				noiron.algParts.add(sayGorithm(algShipment));
				if (algShipment.equals("hello")) {
					cloudianMode.setMode(1);
				}
				algShipment = "";
			}
		}
}

	private void reply() {
		int x = rand.nextInt(talks.size());// to methode
		algShipment = talks.get(x);
		if (!algShipment.contains("chii")) {
			talks.remove(algShipment);
		}
	}

	private Algorithm sayGorithm(String toSay) {
		// returns a simple algorithm for saying sent parameter
		AbsAlgPart itte = new APSay(1, toSay);
		String representation = "say";
		ArrayList<AbsAlgPart> algParts1 = new ArrayList<>();
		algParts1.add(itte);
		Algorithm algorithm = new Algorithm("say", representation, algParts1);
		return algorithm;
	}

	private Algorithm attentionAlg() {
		// returns a simple algorithm for saying sent parameter
		AbsAlgPart itte = new APAttentionForAnyReply(4, 3, 3, "hi");
		String representation = "attention";
		ArrayList<AbsAlgPart> algParts1 = new ArrayList<>();
		algParts1.add(itte);
		Algorithm algorithm = new Algorithm("attention", representation, algParts1);
		return algorithm;
	}

	private void reset() {
		cloudianMode.setMode(0);
		counter = 0;
	}

	private void bureiki() {
		counter++;
		if (counter > convoLength) {
			reset();
		}
	}
}