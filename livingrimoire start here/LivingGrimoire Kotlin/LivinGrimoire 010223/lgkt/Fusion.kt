package lgkt

import java.util.*


class Fusion( /*
     * fuses algorithms and sends needed algorithm to a designated cerabellum
     * object for activation
     */
              private val AlgDurations: Hashtable<String, Int>
) {
    private val AlgDurations2 = Hashtable<String, Int>()
    var emot = ""
        private set
    private val algQueue = PriorityQueue<Algorithm>()
    private val dangerAlgQueue = PriorityQueue<Algorithm>()
    var reqOverload = false // too many requests
        private set
    var repReq = false // chobit has already accepted this request
        private set
    private var represantations = HashSet<String>()
    var dExplorer = DExplorer()

    // private Cerabellum mainCera = new Cerabellum();
    // private Cerabellum dangerCera = new Cerabellum();
    private val goalsToTrack = arrayOf("", "") // dangerCera, mainCera

    // cerabellums :
    private val dangerCera = Cerabellum()

    // requip cera
    private val fusionCera: FusionCera
    private val mainCera = Cerabellum()

    // home cera
    var cera = arrayOfNulls<Cerabellum>(3)

    // end cerabellums
    init {
        // fusionCera = (Cerabellum) (new FusionCera(algQueue));
        fusionCera = FusionCera(algQueue)
        cera[0] = dangerCera
        cera[1] = fusionCera
        cera[2] = mainCera
        // cera = { dangerCera, fusionCera, mainCera };
    }

    fun setAlgQueue(shinkei: Neuron) {
        for (algorithm in shinkei.negativeAlgParts) {
            if (dangerAlgQueue.size < 1) {
                dangerAlgQueue.add(algorithm.clone())
            } else {
                break
            }
        }
        repReq = false
        for (algorithm in shinkei.algParts) {
            updateRepresentations()
            if (represantations.contains(algorithm.representation)) {
                repReq = true
                // System.out.println("again with this shit ?");
                continue
            }
            if (algQueue.size < 5) {
                algQueue.add(algorithm.clone())
            } else {
                break
            }
        }
        reqOverload = algQueue.size > 4 && shinkei.algParts.size > 0
        // empty Neuron
        shinkei.empty()
        if (!dangerCera.isActive && !dangerAlgQueue.isEmpty()) {
            dangerCera.setAlgorithm(dangerAlgQueue.poll())
            goalsToTrack[0] = dangerCera.alg!!.goal
            goalTrack(goalsToTrack[0])
        }
        if (!mainCera.isActive && !algQueue.isEmpty()) {
            mainCera.setAlgorithm(algQueue.poll())
            goalsToTrack[1] = mainCera.alg!!.goal
            goalTrack(goalsToTrack[1])
        }
        // fuse if, {fuse}
        // goalsToTrack[0] = goalTrackReset(goalsToTrack[0]);
        // goalsToTrack[1] = goalTrackReset(goalsToTrack[1]);
        fuze()
    }

    fun act(ear: String?, skin: String?, eye: String?): String {
        var result = ""
        for (i in cera.indices) {
            if (cera[i]!!.isActive) {
                result = cera[i]!!.act(ear!!, skin!!, eye!!)
                dExplorer.mutate(cera[i]!!, cera[i]!!.failType!!)
                cera[i]!!.advanceInAlg()
                emot = cera[i]!!.emot
                if (i > 1) {
                    val n1 = AlgDurations2[cera[i]!!.alg!!.goal]!!
                    AlgDurations2[cera[i]!!.alg!!.goal] = n1 + 1
                }
                break
            }
            // else(cera notactive) try go home
        }
        return result
    }

    private fun updateRepresentations() {
        represantations = HashSet()
        for (algorithm in algQueue) {
            represantations.add(algorithm.representation)
        }
    }

    private fun goalTrack(goal: String) {
        if (!AlgDurations2.containsKey(goal)) {
            // try to load, if failed :
            AlgDurations[goal] = 0
            AlgDurations2[goal] = 0
        } else {
            AlgDurations[goal] = AlgDurations2[goal]
        }
    }

    private fun goalTrackReset(goal: String): String {
        if (goal != "") {
            AlgDurations2[goal] = 0
        }
        return ""
    }

    private fun fuze() {
        if (mainCera.isActive && !fusionCera.isActive) {
            var algRunTime = AlgDurations[mainCera.alg!!.goal]!!
            algRunTime = algRunTime / 2
            var alg1: Algorithm
            var g1 = ""
            var time1 = 0
            val iterator: Iterator<Algorithm> = algQueue.iterator()
            while (iterator.hasNext()) {
                alg1 = iterator.next()
                g1 = alg1.goal
                goalTrack(g1)
                time1 = AlgDurations[g1]!!
                if (time1 < algRunTime) {
                    fusionCera.setAlgorithm(alg1)
                    algQueue.remove(alg1)
                    fusionCera.setAbort(time1)
                    goalTrackReset(g1)
                    break
                }
                // System.out.println(iterator.next());
            }
        }
        // TODO
        // del those lines from setalgqueue
        // and replace with this function.
        // uncheck algQueue.remove(super.alg); at FusionCera
        goalsToTrack[0] = goalTrackReset(goalsToTrack[0])
        goalsToTrack[1] = goalTrackReset(goalsToTrack[1])
    }
}