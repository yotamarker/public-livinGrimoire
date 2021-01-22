package chobit;

import java.awt.Point;
import java.util.HashSet;
import java.util.Iterator;

public class Line {
	private HashSet<Integer> points = new HashSet(); // x,y
	private HashSet<Integer> activePoints = new HashSet();
	private Boolean absorbed = false;

	public Line(Point p1) {
		activePoints.add(p1.x * 100 + p1.y);
		points.add(p1.x * 100 + p1.y);
	}

	public Boolean touchingNextLine(Line nxtLine) {
		Iterator<Integer> i = this.activePoints.iterator();
		while (i.hasNext()) {
			Iterator<Integer> i2 = nxtLine.getActivePoints().iterator();
			while (i2.hasNext()) {
				if (i.next() == i2.next() - 1) {
					return true;
				}
			}
		}
		return false;
	}

	public Boolean getAbsorbed() {
		return absorbed;
	}

	public void setAbsorbed(Boolean absorbed) {
		this.absorbed = absorbed;
	}

	public void absorb(Line food) {
		this.points.addAll(food.getPoints());
		food.setAbsorbed(true);
	}

	public void absorbOnLine(Line food) {
		this.activePoints.addAll(food.getActivePoints());
	}

	public void puffLine(Point pn) {
		this.activePoints.add(pn.x * 100 + pn.y);
		this.points.add(pn.x * 100 + pn.y);
	}

	public HashSet<Integer> getPoints() {
		return points;
	}

	public HashSet<Integer> getActivePoints() {
		return activePoints;
	}

}
