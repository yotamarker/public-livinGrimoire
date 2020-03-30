package chobit;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Fusion {
    /*
     * fuses algorithms and sends needed algorithm to a designated cerabellum
     * cobject for activation
     */
	private Hashtable<String, Integer> AlgDurations;
	private Hashtable<String, Integer> AlgDurations2 = new Hashtable<>();
    private String emot = "";
    private PriorityQueue<Algorithm> algQueue = new PriorityQueue<Algorithm>();
    private PriorityQueue<Algorithm> dangerAlgQueue = new PriorityQueue<Algorithm>();
    private Boolean reqOverload = false; // too many requests
    private Boolean repReq = false; // chobit has already accepted this request
    private HashSet<String> represantations = new HashSet<String>();
    DExplorer dExplorer = new DExplorer();
    // private Cerabellum mainCera = new Cerabellum();
    // private Cerabellum dangerCera = new Cerabellum();
	private String[] goalsToTrack = { "", "" }; // dangerCera, mainCera
	// cerabellums :
	private Cerabellum dangerCera = new Cerabellum();
	// requip cera
	private FusionCera fusionCera;
	private Cerabellum mainCera = new Cerabellum();
	// home cera
	Cerabellum[] cera = new Cerabellum[3];
	// end cerabellums

	public Fusion(Hashtable<String, Integer> algDurations) {
		super();
		AlgDurations = algDurations;
		// fusionCera = (Cerabellum) (new FusionCera(algQueue));
		fusionCera = new FusionCera(algQueue);
		cera[0] = dangerCera;
		cera[1] = fusionCera;
		cera[2] = mainCera;
		// cera = { dangerCera, fusionCera, mainCera };
	}

	public void setAlgQueue(Neuron shinkei) {
        for (Algorithm algorithm : shinkei.negativeAlgParts) {
            if (this.dangerAlgQueue.size() < 1) {
                this.dangerAlgQueue.add(algorithm.clone());
            } else {
                break;
            }
        }
        this.repReq = false;
        for (Algorithm algorithm : shinkei.algParts) {
            updateRepresentations();
            if (this.represantations.contains(algorithm.getRepresentation())) {
                this.repReq = true;
                // System.out.println("again with this shit ?");
                continue;
            }
            if (this.algQueue.size() < 5) {
                this.algQueue.add(algorithm.clone());
            } else {
                break;
            }
        }
        this.reqOverload = this.algQueue.size() > 4 && shinkei.algParts.size() > 0;
        // empty Neuron
        shinkei.empty();
		if (!dangerCera.isActive() && !dangerAlgQueue.isEmpty()) {
			dangerCera.setAlgorithm(dangerAlgQueue.poll());
			goalsToTrack[0] = dangerCera.alg.getGoal();
			goalTrack(goalsToTrack[0]);
        }
		if (!mainCera.isActive() && !algQueue.isEmpty()) {
			mainCera.setAlgorithm(algQueue.poll());
			goalsToTrack[1] = mainCera.alg.getGoal();
			goalTrack(goalsToTrack[1]);
        }
		// fuse if, {fuse}
		// goalsToTrack[0] = goalTrackReset(goalsToTrack[0]);
		// goalsToTrack[1] = goalTrackReset(goalsToTrack[1]);
		fuze();
    }

    public Boolean getRepReq() {
        return repReq;
    }

    public Boolean getReqOverload() {
        return reqOverload;
    }

	public String act(String ear, String skin, String eye) {
		String result = "";
        for (int i = 0; i < cera.length; i++) {
			if (cera[i].isActive()) {
				result = cera[i].act(ear, skin, eye);
				dExplorer.mutate(cera[i], cera[i].getFailType());
				cera[i].advanceInAlg();
                this.emot = cera[i].getEmot();
				if (i > 1) {
				int n1 = AlgDurations2.get(cera[i].alg.getGoal());
					AlgDurations2.put(cera[i].alg.getGoal(), n1 + 1);
				}
                break;
            }
            // else(cera notactive) try go home
        }
		return result;
    }

    public String getEmot() {
        return emot;
    }

    private void updateRepresentations() {
        this.represantations = new HashSet<String>();
        for (Algorithm algorithm : this.algQueue) {
            this.represantations.add(algorithm.getRepresentation());
        }
    }

	private void goalTrack(String goal) {
		if (!AlgDurations2.containsKey(goal)) {
			// try to load, if failed :
			AlgDurations.put(goal, 0);
			AlgDurations2.put(goal, 0);
		} else {
			AlgDurations.put(goal, AlgDurations2.get(goal));
		}
	}

	private String goalTrackReset(String goal) {
		if (!goal.equals("")) {
			AlgDurations2.put(goal, 0);
		}
		return "";
	}

	private void fuze() {
		if (mainCera.isActive() && !fusionCera.isActive()) {
			int algRunTime = AlgDurations.get(mainCera.alg.getGoal());
			algRunTime = algRunTime / 2;
			Algorithm alg1;
			String g1 = "";
			int time1 = 0;
			Iterator<Algorithm> iterator = algQueue.iterator();
			while (iterator.hasNext()) {
				alg1 = iterator.next();
				g1 = alg1.getGoal();
				goalTrack(g1);
				time1 = AlgDurations.get(g1);
				if (time1 < algRunTime) {
					fusionCera.setAlgorithm(alg1);
					algQueue.remove(alg1);
					fusionCera.setAbort(time1);
					goalTrackReset(g1);
					break;
				}
				// System.out.println(iterator.next());
			}
		}
		// TODO
		// del those lines from setalgqueue
		// and replace with this function.
		// uncheck algQueue.remove(super.alg); at FusionCera
		goalsToTrack[0] = goalTrackReset(goalsToTrack[0]);
		goalsToTrack[1] = goalTrackReset(goalsToTrack[1]);

	}
}
