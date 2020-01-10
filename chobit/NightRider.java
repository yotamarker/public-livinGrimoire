package chobit;

public class NightRider {
	private String display = "--------";
	private final String DISPLAYPRIME = display;
	private int i = 0;
	private byte dir = 1;
	private Character char1 = '-';
	private Character char2 = '0';
	private int mode = 1;
	private String siren1 = "";
	private String siren2 = "";
	private boolean sirenMode = false;
	private String battleMode = "";

	public NightRider() {
		super();
		for (int i = 0; i < display.length() / 2; i++) {
			siren1 += char1.toString();
			siren2 += char2.toString();
			battleMode += "!!";
		}
	}

	public void setMode(int mode) {
		if (mode < 1 || mode > 10) {
			this.mode = 1;
			return;
		}
		this.mode = mode;
	}

	public String getDisplay() {
		String result = "";
		switch (mode) {
		case 1:
			result = mode1();
			break;
		case 2:
			result = mode2();
			break;
		case 3:
			result = mode3();
			break;
		case 4:
			result = mode4();
			break;
		case 5:
			result = mode5();
			break;
		case 6:
			result = mode6();
			break;
		case 7:
			result = mode7();
			break;
		case 8:
			result = mode8();
			break;
		case 9:
			result = mode9();
			break;
		case 10:
			result = mode10();
			break;
		default:
			break;
		}
		return result;
	}

	private String mode1() {
		// default night rider mode
		display = display.substring(0, i) + char1 + display.substring(i + 1);
		i += dir;
		display = display.substring(0, i) + char2 + display.substring(i + 1);
		if (i == display.length() - 1 || i == 0) {
			dir *= -1;
		}
		return display;
	}

	private String mode2() {
		// wind shield wiper mode
		int half = display.length() / 2;
		display = display.substring(0, i) + char1 + display.substring(i + 1);
		display = display.substring(0, i + half) + char1 + display.substring(i + 1 + half);
		i += dir;
		display = display.substring(0, i) + char2 + display.substring(i + 1);
		display = display.substring(0, i + half) + char2 + display.substring(i + half + 1);
		if (i == display.length() / 2 - 1 || i == 0) {
			dir *= -1;
		}
		return display;
	}

	private String mode3() {
		// hilix mode
		int max = display.length() - 1;
		display = display.substring(0, i) + char1 + display.substring(i + 1);
		display = display.substring(0, max - i) + char1 + display.substring(max - i + 1);
		i += dir;
		display = display.substring(0, i) + char2 + display.substring(i + 1);
		display = display.substring(0, max - i) + char2 + display.substring(max - i + 1);
		if (i == display.length() / 2 - 1 || i == 0) {
			dir *= -1;
		}
		return display;
	}

	private String mode4() {
		// beads mode
		if (i % 2 == 0) {
			display = display.substring(0, i) + char1 + display.substring(i + 1);
		}
		i += dir;
		if (i == display.length() - 1 || i == 0) {
			display = DISPLAYPRIME;
		}
		display = display.substring(0, i) + char2 + display.substring(i + 1);
		if (i == display.length() - 1 || i == 0) {
			dir *= -1;
		}
		return display;
	}

	private String mode5() {
		// siren mode
		sirenMode = !sirenMode;
		if (sirenMode) {

			return siren1 + siren2;
		} else {
			return siren2 + siren1;
		}
	}

	private String mode6() {
		// fill and clear mode
		i += dir;
		if (i == display.length() - 1 || i == 0) {
			display = DISPLAYPRIME;
		}
		display = display.substring(0, i) + char2 + display.substring(i + 1);
		if (i == display.length() - 1 || i == 0) {
			dir *= -1;
		}
		return display;
	}

	private String mode7() {
		// battle mode !!!!!!
		sirenMode = !sirenMode;
		if (sirenMode) {

			return DISPLAYPRIME;
		} else {
			return battleMode;
		}
	}

	private String mode8() {
		// fat mode _000----
		String temp = "";
		display = display.substring(0, i) + char1 + display.substring(i + 1);
		i += dir;
		display = display.substring(0, i) + char2 + display.substring(i + 1);
		if (i > 0) {
			temp = display.substring(0, i - 1) + char2 + display.substring(i - 1 + 1);
		}
		else {
			temp = display;
		}
		if (i == display.length() - 1 || i == 0) {
			dir *= -1;
		}
		return temp;
	}

	private String mode9() {
		// L to R
		display = display.substring(0, i) + char1 + display.substring(i + 1);
		if (i == display.length() - 1) {
			i = -1;
		}
		i += dir;
		display = display.substring(0, i) + char2 + display.substring(i + 1);

		return display;
	}

	private String mode10() {
		// R to L
		display = display.substring(0, i) + char1 + display.substring(i + 1);
		if (i == 0) {
			i = display.length();
		}
		i += -1;
		display = display.substring(0, i) + char2 + display.substring(i + 1);

		return display;
	}
}
