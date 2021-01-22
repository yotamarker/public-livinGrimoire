package chobit;

import java.awt.Rectangle;

public class RectangleCtor extends Rectangle {
	public RectangleCtor(int x, int y, int x2, int y2) {
		this.x = x;
		this.y = y;
		this.width = x2 - x;
		this.height = y2 - y;
	}
}
