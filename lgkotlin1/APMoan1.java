package chobit;


public class APMoan1 extends APMoan0 {

    public APMoan1() {
        super();
        interactions.add("do it");
        moans.add("fuck");
        moans.add("fuck yeah");
        groans.add("i love you");
        groans.add("sweet");
    }

    @Override
    public AbsAlgPart clone() {
        // TODO Auto-generated method stub
        return new APMoan1();
    }

    @Override
    public AbsAlgPart mutation() {
        // TODO Auto-generated method stub
        APMoan0 mutant = new APMoan1();
        int x = rand.nextInt(moanList.size() + 1);
        switch (x) {
            case 1:
                mutant = new APMoan1();
                break;
            case 2:
                mutant = new APMoan2();
                break;
            default:
                break;
        }
        return (AbsAlgPart) mutant;
    }
}

