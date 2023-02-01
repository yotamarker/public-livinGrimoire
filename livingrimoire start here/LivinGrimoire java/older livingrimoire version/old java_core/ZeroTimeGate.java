package LG_Core;

import java.util.Calendar;
import java.util.Date;

public class ZeroTimeGate {
	// a gate that only opens x minutes after it has been set
	private int pause = 5;
	private Date openedGate = new Date();
	private Date checkPoint = new Date();

	public ZeroTimeGate(int minutes) {
		super();
		this.pause = minutes;
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	public ZeroTimeGate() {
	}

	public Boolean isClosed() {
		return openedGate.before(new Date());
	}

	public Boolean isOpen() {
		return !openedGate.before(new Date());
	}

	public void open() {
		this.openedGate = addMinutesToJavaUtilDate(new Date(), pause);
	}

	public void open(int minutes) {
		Date now = new Date();
		openedGate = addMinutesToJavaUtilDate(now, minutes);
	}

	private Date addMinutesToJavaUtilDate(Date date, int minutes) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minutes);
		return calendar.getTime();
	}

	public void setPause(int pause) {
		if (pause < 60 && pause > 0) {
			this.pause = pause;
		}
	}

	public void resetCheckPoint() {
		this.checkPoint = new Date();
	}

	public int givenTwoDateTimesInJava8_whenDifferentiatingInSeconds_thenWeGetTen() {
		Date now = new Date();
		long diff = now.getTime() - this.checkPoint.getTime();
		long diffSeconds = diff / 1000 % 60;
		// long diffMinutes = diff / (60 * 1000) % 60;
		// long diffHours = diff / (60 * 60 * 1000) % 24;
		// long diffDays = diff / (24 * 60 * 60 * 1000);
		// System.out.print(diffDays + " days, ");
		// System.out.print(diffHours + " hours, ");
		// System.out.print(diffMinutes + " minutes, ");
		// System.out.print(diffSeconds + " seconds.");
		return (int) diffSeconds;
	}
}

