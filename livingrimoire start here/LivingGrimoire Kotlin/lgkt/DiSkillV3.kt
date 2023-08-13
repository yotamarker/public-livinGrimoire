package lgkt

class DiSkillV3: DiSkillV2() {
// algorithm will be loaded with priority to run
// use for fight or flight type skills

    // negativeAlgParts take priority to run over algParts
    // (used in the super class DiSkillV2)
    override fun output(noiron: Neuron) {
        if (outAlg != null) {
            noiron.negativeAlgParts.add(outAlg!!)
            outAlg = null
        }
    }
}