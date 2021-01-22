package chobit;

public class CALOC {
	/*
	 * this class counts how many active lines of code has run in a function 1 : use
	 * the getCLOCVer with the function you want to test as a string parameter 2
	 * paste the resulting code into this class, and delete errors with the code
	 * activeLinesOfCode++; only where it errors tho 3 if the tested function has
	 * private methodes used inside, extend the CALOC class with the original class
	 * containing the tested function. 4 run the pasted function via an instance of
	 * this class : caloc.testFunction(params); 5 caloc.getActiveLinesOfCode to see
	 * how many active line of code for the parameters you have sent
	 */
	private int activeLinesOfCode = 0;

	public int getActiveLinesOfCode() {
		return activeLinesOfCode;
	}

	public void resetActiveLinesOfCode() {
		this.activeLinesOfCode = 0;
	}

	public String getCALOCVer(String function) {
		return function.replace(";", ";activeLinesOfCode++;");
	}

	public int miner(int... a) {
		int minimum = a[0];
		activeLinesOfCode++;
		for (int i = 1; i < a.length; i++) {
			if (a[i] < minimum) {
				minimum = a[i];
				activeLinesOfCode++;
			}
		}
		return minimum;
	}
}
