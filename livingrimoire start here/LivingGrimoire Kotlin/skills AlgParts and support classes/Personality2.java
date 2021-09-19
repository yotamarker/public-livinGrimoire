package com.yotamarker.lgkotlinfull.skills;

import com.yotamarker.lgkotlinfull.LGCore.*;

public class Personality2 extends Personality {
	public Personality2(AbsDictionaryDB absDictionaryDB) {
        super(absDictionaryDB);
        // add a skill here, only 1 line needed !!!
		// dClassesLv1.add(new Detective(fusion));
		getdClassesLv1().add(new DSayer());
    }

	public Personality2() {
        super();
		getdClassesLv1().add(new DSayer());
		getDClassesLv1().add(new DSpeller());
    }
}
