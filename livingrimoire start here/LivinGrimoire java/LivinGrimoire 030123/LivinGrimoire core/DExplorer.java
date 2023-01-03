package LivinGrimoire;

//D class responsible for :
// learning, mutating algorithms, requiping APs with objects or skill mods
// the should resides here

public class DExplorer {
    private int failureCounter = 0;
    private String prevAP = "";
    public void mutate(Cerabellum cera, enumFail failure) {
        String AP = cera.getEmot();
        /*
         * group relies on a naming convention each class in a mutation series must have
         * the same class name concated with a number : APMoan1, APMoan2, APMaon3 ...
         */
        AP = AP.replaceAll("\\d+", "");
        // give up ? :
        if (prevAP.contains(AP) && !failure.toString().equals(enumFail.ok.toString())) {
            failureCounter++;
            if (failureCounter > cera.getMutationLimitOfActiveAlgPart()) {
                cera.setActive(false);
                // this.failureCounter = 0;
            }
        }
        else {
            if (!prevAP.contains(AP)) {
                failureCounter = 0;
            }
        }
        prevAP = AP;
        switch (failure) {
            case fail:
                Mutatable mutant = (Mutatable) cera.alg.getAlgParts().get(cera.getAt());
                cera.alg.getAlgParts().set(cera.getAt(), mutant.mutation());
                break;
            case cloudian:
                cera.setActive(false);
                break;
            default:
                break;
        }
    }
}
