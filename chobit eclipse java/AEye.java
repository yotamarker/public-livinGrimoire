package chobit;

import java.awt.Rectangle;

public class AEye {
	public int outlinePixel = 30; // limit how dark an outline pixel is
	private double clusterPercent = 0.05;// fatness of outline of shapes (sand blast out)
	private int minObjectSize = 40; // how small an objext detected can be
	private int x; // coordinates of grid area to work on
	private int y;
	private AEyeClassifier eyeObj = new AEyeClassifier();
	private boolean[] shiberArray = new boolean[] { false, false, false, false, false, false, false, false, false };
	private int shiberCounter = 0; // active shiber
	public void setXY(int x1, int y1) {
		// change
		x = x1;
		y = y1;
	}
	public void setMinObjectSize(int newVal) {
		// set minimum detectable item size
		if ((newVal >= 13)) {
			minObjectSize = newVal;
		}

	}

	public boolean overlappingRectangles(Rectangle R1, Rectangle R2) {
		// check if 2 rectangles overlap
		if (((R1.getX() > (R2.getX() + R2.getWidth())) || ((R1.getX() + R1.getWidth()) < R2.getX()))) {
			return false;
		}

		if (((R1.getY() > (R2.getY() + R2.getHeight())) || ((R1.getY() + R1.getHeight()) < R2.getY()))) {
			return false;
		}

		return true;
	}

	public void setClusterPercent(double newVal) {
		if (((newVal <= 1) && (newVal >= 0))) {
			clusterPercent = newVal;
		}

	}
}
