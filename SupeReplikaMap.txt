package chobit;

import java.awt.Point;
import java.util.Hashtable;
import java.util.Stack;

public class SupeReplikaMap {
	private Stack<String> objPoint = new Stack<String>();
	// private ArrayList<String> objPointGPS = new ArrayList<String>();
	private Hashtable<String, String> pointDefcon = new Hashtable<>();
	private Hashtable<String, String> PointLog = new Hashtable<>();// sensory log
	private Hashtable<String, String> logPoint = new Hashtable<>();// traverse log
	private Compass compass = new Compass();
	private ZeroTimeGate zeroTimeGate = new ZeroTimeGate(1);
	private Point xy = new Point(0, 0);
	private RegexUtil regexUtil = new RegexUtil();
	public void input(String item, String defcon, String sensory) {
		// save
		// timegate pause = 1;
		zeroTimeGate.setPause(1);
		Boolean defconOverride = (pointDefcon.getOrDefault(xy.toString(), "").isEmpty()) && !defcon.isEmpty();
		// ^ no defcon data for this point
		if ((zeroTimeGate.isClosed() || defconOverride) && Compass.isActive()) {
			this.xy = pointUpdate(this.xy);
			zeroTimeGate.open();
			objPoint.add(xy.toString() + item);
			pointDefcon.put(xy.toString(), defcon);
			PointLog.put(xy.toString(), sensory);
		}

	}
	public Point strToPoint(String s1) {
		return regexUtil.pointRegex(s1);
	}
	private Point pointUpdate(Point p1) {
		Point endPoint = new Point(p1.x, p1.y);
		switch (this.compass.getDirection()) {
		case 0:
			endPoint.y++;
			break;
		case 2:
			endPoint.x++;
			break;
		case 4:
			endPoint.y--;
			break;
		case 6:
			endPoint.x--;
			break;
		default:
			break;
		}
		return endPoint;
	}

	public void recycleThreat(String item, String defcon, String sensory) {
		// when predicting a goal, or explore traversing
		zeroTimeGate.setPause(5);
		objPoint.add(xy.toString() + item);
		pointDefcon.put(xy.toString(), defcon);
		PointLog.put(xy.toString(), sensory);
	}
	public void sleep() {
		// save as long term memory logs
	}

	// outputs :
	public Integer timeCost() {
		return 0;
	}
	public String answer(String question) {
		switch (question) {
		case "describe":
			return PointLog.getOrDefault(xy.toString(), "hey listen");
		case "what":
			return pointDefcon.getOrDefault(xy.toString(), "hey watch out");
		default:
			break;
		}
		return "";
	}

	public String where(String item) {
		String answer = "";
		for (String temp : objPoint) {
			if (temp.contains(item)) {
				Point p1 = regexUtil.pointRegex(temp.replace(item, ""));
				if (p1.equals(this.xy)) {
					return "it is here";
				}
				String dir = "";
				if (xy.y < p1.y) {
					dir = "north ";
				}
				if (xy.y > p1.y) {
					dir = "south ";
				}
				if (xy.x < p1.x) {
					dir += "east";
				}
				if (xy.x > p1.x) {
					dir += "west";
				}
				return "it is " + dir;
			} // TODO convert to address
		}
		return "";
	}

	public String walkThrough(String question) {
		return "";
	}

	public void logPoint(String log) {
		// move point by compass
		// summon with algPart
		logPoint.put(log, this.xy.toString());
	}

	public String getPoint(String log) {
		return logPoint.getOrDefault(log, "");
	}

	public void deleteKey(String pointItem) {
		objPoint.remove(pointItem);
		/*
		 * on return home delete failed pointTransportItem.
		 */
		// TODO sql side
	}

	public void setHome() {
		this.xy = new Point(0, 0);
	}
}
