package auxiliary_modules

import livinGrimoire.*
import java.util.*

class SkillHubAlgDispenser(vararg skillsParams: Skill) {
    //     super class to output an algorithm out of a selection of skills
    //      engage the hub with dispenseAlg and return the value to outAlg attribute
    //      of the containing skill (which houses the skill hub)
    //      this module enables using a selection of 1 skill for triggers instead of having the triggers engage on multible skill
    //       the methode is ideal for learnability and behavioral modifications
    //       use a learnability auxiliary module as a condition to run an active skill shuffle or change methode
    //       (rndAlg , cycleAlg)
    //       moods can be used for specific cases to change behavior of the AGI, for example low energy state
    //       for that use (moodAlg)
    private val skills: ArrayList<Skill> = ArrayList<Skill>()
    private var activeSkill = 0
    private val tempN: Neuron = Neuron()
    private val rand = Random()
    private var kokoro: Kokoro = Kokoro(AbsDictionaryDB())

    init {
        for (skill in skillsParams) {
            skill.kokoro = kokoro
            skills.add(skill)
        }
    }

    fun setKokoro(kokoro: Kokoro) {
        this.kokoro = kokoro
        for (skill in skills) {
            skill.kokoro = kokoro
        }
    }

    fun addSkill(skill: Skill): SkillHubAlgDispenser {
        // builder pattern
        skill.kokoro = kokoro
        skills.add(skill)
        return this
    }

    fun dispenseAlgorithm(ear: String, skin: String, eye: String): AlgorithmV2? {
        // return value to outAlg param of (external) summoner DiskillV2
        skills[activeSkill].input(ear, skin, eye)
        skills[activeSkill].output(tempN)
        for (i in 1..5) {
            val temp: Algorithm? = tempN.getAlg(i)
            if (temp != null) {
                return AlgorithmV2(i, temp)
            }
        }
        return null
    }

    fun randomizeActiveSkill() {
        activeSkill = rand.nextInt(skills.size)
    }

    fun setActiveSkillWithMood(mood: Int) {
        // mood integer represents active skill
        // different mood = different behavior
        if (mood > -1 && mood < skills.size) {
            activeSkill = mood
        }
    }

    fun cycleActiveSkill() {
        // changes active skill
        // I recommend this method be triggered with a Learnability or SpiderSense object
        activeSkill++
        if (activeSkill == skills.size) {
            activeSkill = 0
        }
    }

    val size: Int
        get() = skills.size

    fun activeSkillRef(): Skill {
        return skills[activeSkill]
    }
}