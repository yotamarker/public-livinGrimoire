package chobit;

import java.util.ArrayList;

public class APImprintMaster extends AbsAlgPart {
	// todo : handle inputs regexes
	/*
	 * asks master for vital info, to fill in master object fields like name and
	 * phone number
	 */
	private ArrayList<String> form = new ArrayList<>();
	private int mode = 0;
	private int index = 0;
	private String input = "";
	private Boolean isCompleted = false;
	private String curResult = "";
	private Person master;

	public APImprintMaster(Person master) {
		// default c'tor
		super();
		form.add("nice to meet you, and what would your name be");
		form.add("are you my master");
		form.add("I will input your name");
		form.add("what is your skill");
		form.add("what is your profession");
		form.add("what is your phone number");
		form.add("what is your email address");
		form.add("which is your favorite jutsu");
		form.add("soul spark engaged");
		this.master = master;
	}

	public APImprintMaster(Person master, String... strs) {
		// alternative c'tor
		for (String i : strs) {
			form.add(i);
		}
		this.master = master;
	}

	@Override
	public String action(String ear, String skin, String eye) {
		String result = "";
		switch (mode) {
		case 0:
			result = form.get(index);
			curResult = result;
			if (result.contains("what") || result.contains("which is") || result.contains("please")
					|| result.contains("are you")) {
				mode = 2;
				if (form.get(index).contains("are you")) {
					mode = 4;
				}
			}
			else {
				index++;
			}
			break;
		case 2:
			if (!ear.isEmpty()) {
				mode = 3;
				input = ear;
			}

			break;
		case 3:
			result = input + " yes";
			mode = 4;
			break;
		case 4:
			if (ear.contains("yes")) {
				mode = 0;
				imprint();
				index++;
			}
			if (ear.contains("no")) {
				mode = 0;
			}
			break;
		default:
			break;
		}
		if (index == form.size()) {
			isCompleted = true;
			index--;
		}
		return result;
	}

	public void imprint() {
		switch (curResult) {
		case "nice to meet you, and what would your name be":
			master.setName(input);
			break;
		case "what is your skill":
			master.setSkill(input);
			break;
		case "what is your profession":
			master.setProfession(input);
			break;
		case "what is your phone number":
			master.setPhone(input);
			break;
		case "what is your email address":
			master.setEmail(input);
			break;
		case "which is your favorite jutsu":
			master.setJutsu(input);
			break;
		default:
			break;
		}
	}

	@Override
	public Boolean itemize() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public enumFail failure(String input) {
		// TODO Auto-generated method stub
		return enumFail.ok;
	}

	@Override
	public Boolean completed() {
		// TODO Auto-generated method stub
		return isCompleted;
	}

	@Override
	public AbsAlgPart clone() {
		// ***might glich, clone person ?
		return new APImprintMaster(this.master);
	}

}
