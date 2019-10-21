package chobit;

import java.util.ArrayList;
import java.util.Arrays;

public class DIAutomatic extends DISkill {
	/*
	 * this class enables time triggered actions. algorithmic logic examplified with
	 * the bark action: the AGI will bark at some time, and continue doing so every
	 * hour till the user replies with his signature catch frase. thus the AGI will
	 * know the time to expect him home. next she will reply happily. the bark is
	 * the alg while the happy reply is the alter alg.
	 */
	public Person master;
	int algMode[] = new int[1]; // mode of alg per algs: 0,1 alg,alter alg
	int algTime[] = new int[1]; // alg trigger time per alg(hence array).
	String algTosend = "";
	private PlayGround playGround = new PlayGround();

	public DIAutomatic(Kokoro kokoro, Person master) {
		super(kokoro);
		this.master = master;
		Arrays.fill(algMode, 0);
		algTime[0] = playGround.getHoursAsInt();
	}

	@Override
	public void input(String ear, String skin, String eye) {
		int hour = playGround.getHoursAsInt();
		for (int i = 0; i < algMode.length; i++) {
			if (algMode[i] == 0) {
				if (algTime[i] == hour) {
					algTime[i] = playGround.getFutureHour(algTime[i], 1);
					algTosend = i + "";
					specialAlgAction(i);
					return;
				}
				if (alterAlg(i, ear, skin, eye)) {
					algTime[i] = hour;
					algMode[i] = 1;
					break;
				}
			} else {
				if (algTime[i] != hour) {
					algMode[i] = 0;
				}
			}
		}
		if (!algTosend.isEmpty()) {
			this.setSentAlg(true);
		}
	}

	@Override
	public void output(Neuron noiron) {
		switch (algTosend) {
		case "0":
			barkAlg(noiron);
			break;
		case "0mode2":
			masterGreetAlg(noiron);
			break;
		default:
			break;
		}
		algTosend = "";
	}

	protected void specialAlgAction(int algNumber) {
		switch (algNumber) {
		case 0:
			master.setActive(false);
			break;

		default:
			break;
		}
	}

	protected Boolean alterAlg(int algNumber, String ear, String skin, String eye) {
		switch (algNumber) {
		case 0:
			if (ear.contains(master.getJutsu()) && !master.getActive()) {
				master.setActive(true);
				algTosend = algNumber + "mode2";
				return true;
			}
			break;

		default:
			break;
		}
		return false;
	}

	private void barkAlg(Neuron noiron) {
		// example timed alg
		AbsAlgPart itte = new Chi(this.kokoro, this.getClass().getSimpleName(), new APBark(3, master));
		String representation = "bark";
		ArrayList<AbsAlgPart> algParts1 = new ArrayList<>();
		algParts1.add(itte);
		Algorithm algorithm = new Algorithm("bark", representation, algParts1);
		noiron.algParts.add(algorithm);
	}

	private void masterGreetAlg(Neuron noiron) {
		// example alter alg, to fire when respective conditions were met and after a
		// timed alg was fired.
		AbsAlgPart itte = new Chi(this.kokoro, this.getClass().getSimpleName(), new APSay(1, "i missed you"));
		String representation = "welcome";
		ArrayList<AbsAlgPart> algParts1 = new ArrayList<>();
		algParts1.add(itte);
		Algorithm algorithm = new Algorithm("welcome", representation, algParts1);
		noiron.algParts.add(algorithm);
	}
}
