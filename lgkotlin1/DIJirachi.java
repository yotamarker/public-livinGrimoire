package com.yotamarker.lgkotlin1;

public class DIJirachi extends Bijuu {
	// a set of skills to make the user happy and grant his wishes
	public DIJirachi(Person master, Kokoro kokoro) {
		super(master, kokoro, new DIMommyGf(kokoro, master));

	}

}
