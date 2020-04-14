package chobit;

import java.util.ArrayList;

public class AEyeClassifier {
	/*
	 * resulting object of a processed image string representations of objects
	 * within an image
	 */
	private ArrayList<String> lv1 = new ArrayList<String>(); // all captured items
	private String lv2; // outline of biggest item
	private String lv3; // insides of captured item
	private boolean connectable;
}