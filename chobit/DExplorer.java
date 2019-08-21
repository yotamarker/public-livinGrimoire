package chobit;
//D class responsible for exploring :
// learning, mutating algorithms, requiping APs with objects or skill mods
// the sould resides here

public class DExplorer extends AbsCmdReq implements Neuronable {
    private int failureCounter = 0;
    private String prevAP = "";
	public GameShark gameShark = new GameShark();
    @Override
    public void output(Neuron noiron) {
        // TODO Auto-generated method stub

    }

    @Override
	public void input(String ear, String skin, String eye) {
        // TODO Auto-generated method stub

    }

	public void mutate(Cerabellum cera, enumFail failure) {
        String AP = cera.getEmot();
        // group
        AP = GroupAP(AP);
        // give up ? :
        if (prevAP.contains(AP) && !failure.toString().equals(enumFail.ok.toString())) {
            failureCounter++;
            switch (AP) {
                case "APSay":
                    break;
                case "APMoan":
                    if (failureCounter > 1) {
                        cera.setActive(false);
                    }
                    break;
                default:
                    break;
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
		case requip:
			gameShark.outerMutate(AP);
			break;
		case cloudian:
			cera.setActive(false);
			break;
            default:
                break;
        }
    }

    private String GroupAP(String aP) {
        String result = "";
        if (aP.contains("APMoan")) {
            result = "APMoan";
        }
        return result;
    }

	public void requip(Cerabellum fusionCera, Cerabellum mainCera) {
		for (AbsAlgPart algPart : mainCera.alg.getAlgParts()) {
			if (algPart.itemize()) {
				this.gameShark.addMcode(algPart.getClass().getSimpleName());
			}
		}
		fusionCera.setAlgorithm(this.gameShark.requipAlg);
		this.gameShark.setGameShark();
	}
}
