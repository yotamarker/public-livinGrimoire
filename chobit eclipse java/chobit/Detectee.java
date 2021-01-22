package chobit;

import java.awt.Rectangle;
import java.util.HashSet;
import java.util.Iterator;

public class Detectee {
	private Rectangle rectangle;
	private HashSet<Integer> points = new HashSet(); // x,y

	public Detectee(Line l1) {
		super();
		this.points = (HashSet<Integer>) l1.getPoints();
		int maxx = -1;
		int maxy = -1;
		int minx = 90001;
		int miny = 90001;
		Iterator<Integer> i = this.points.iterator();
		while (i.hasNext()) {
			int p1 = i.next();
			int x = p1 / 100;
			int y = p1 % 100;
			maxx = maxer(x, maxx);
			maxy = maxer(y, maxy);
			minx = miner(x, minx);
			miny = miner(y, miny);
		}
		this.rectangle = new RectangleCtor(minx, miny, maxx, maxy);
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public static int miner(int... a) {
		// returns array minimum
		int minimum = a[0];
		for (int i = 1; i < a.length; i++) {
			if (a[i] < minimum) {
				minimum = a[i];
			}
		}
		return minimum;
	}

	public static int maxer(int... a) {
		// returns array maximum
		int maximum = a[0];
		for (int i = 1; i < a.length; i++) {
			if (a[i] > maximum) {
				maximum = a[i];
			}
		}
		return maximum;
	}
}
