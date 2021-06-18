package com.yotamarker.lgkotlin1;

public class Personality1 extends Personality{
    public Personality1(AbsDictionaryDB absDictionaryDB) {
        super(absDictionaryDB);
        // add a skill here, only 1 line needed !!!
        dClassesLv1.add(new Detective(fusion));
        // dClassesLv1.add(new DJirachi());
        // dClassesLv1.add(new DIAutomatic(kokoro, master));
        // dClassesLv1.add(new DIBedTime(kokoro));
        // dClassesLv1.add(new DHungry());
        // dClassesLv1.add(new DIBurper(kokoro));
        dClassesLv1.add(dPermitter);
        dClassesLv1.add(new DIJoker(kokoro));
        dClassesLv1.add(new DIEliza(kokoro));
        dClassesLv1.add(new DILively(kokoro));
        dClassesLv1.add(new DIBurper(kokoro));
        dClassesLv1.add(new DIWeather(kokoro));
        dClassesLv1.add(new DICurrency(kokoro));
        dClassesLv1.add(new DIGamer(kokoro));
        dClassesLv1.add(new DSpeller());
        //dClassesLv1.add(new DiSoulV3(kokoro));
        dClassesLv1.add(new DiPrefer(kokoro));
        dClassesLv1.add(new DIBukubukuchagama(kokoro));
        dClassesLv1.add(new DIAlerter(kokoro));
        dClassesLv1.add(new DiTglrAdapter(kokoro,"mommy",new TheSitterV2(kokoro, "mommy")));
        dClassesLv1.add(new DiMiniGamer(kokoro));
        dClassesLv1.add(new ThePet(kokoro));
        dClassesLv1.add(new DiMemoryGame(kokoro));
        dClassesLv1.add(new DiSaladSuggestor(kokoro));
        dClassesLv1.add(new DiB8Tri(kokoro));
        dClassesLv1.add(new DiResponder(kokoro));
        dClassesLv1.add(new Me(kokoro,permission));
        dClassesLv1.add(new DiRapey(kokoro));
        dClassesLv1.add(new Empathy(kokoro));
        dClassesLv1.add(new DiParrot(kokoro));
        //dClassesLv1.add(new DiMedicator(kokoro));
        //dClassesLv1.add(new DiVictim(kokoro));
        // dClassesLv1.add(new DCalculatorV1());
        //dClassesLv2.add(new DSayer());
        dClassesLv2.add(new DiSayer(kokoro));
        // dClassesLv3.add(new DAlarmer());
        dClassesLv3.add(new DIDirty(kokoro));
        dClassesLv3.add(new DIHomer(kokoro));
        dClassesLv3.add(new DILifeFueler(kokoro));
        dClassesLv3.add(new DiTglrSkill(kokoro, "mommy"));
    }

    public Personality1() {
        super();
        dClassesLv1.add(new Detective(fusion));
        // dClassesLv1.add(new DJirachi());
        // dClassesLv1.add(new DIAutomatic(kokoro, master));
        // dClassesLv1.add(new DIBedTime(kokoro));
        // dClassesLv1.add(new DHungry());
        // dClassesLv1.add(new DIBurper(kokoro));
        dClassesLv1.add(dPermitter);
        dClassesLv1.add(new DIJoker(kokoro));
        dClassesLv1.add(new DSpeller());
        dClassesLv1.add(new DISoulV2(kokoro));
        // dClassesLv1.add(new DCalculatorV1());
        dClassesLv2.add(new DSayer());
        // dClassesLv3.add(new DAlarmer());
        dClassesLv3.add(new DIDirty(kokoro));
    }
}
