﻿using System.Collections.Generic;

public class DiTime : DiSkillV2
{
    public DiTime()
        : base()
    {
    }

    public override void Input(string ear, string skin, string eye)
    {
        switch (ear)
        {
            case "what is the date":
                SetVerbatimAlg(4, $"{TimeUtils.GetCurrentMonthDay()} {TimeUtils.GetCurrentMonthName()} {TimeUtils.GetCurrentYear()}");
                break;
            case "what is the time":
                SetVerbatimAlg(4, TimeUtils.GetCurrentTimeStamp());
                break;
            case "honey bunny":
                SetVerbatimAlg(4, "bunny honey");
                break;
            case "which day is it":
                SetVerbatimAlg(4, TimeUtils.GetDayOfDWeek());
                break;
            case "good morning":
            case "good night":
            case "good afternoon":
            case "good evening":
                SetVerbatimAlg(4, $"good {TimeUtils.PartOfDay()}"); // fstring
                break;
            case "which month is it":
                SetVerbatimAlg(4, TimeUtils.GetCurrentMonthName());
                break;
            case "which year is it":
                SetVerbatimAlg(4, $"{TimeUtils.GetCurrentYear()}");
                break;
            case "what is your time zone":
                SetVerbatimAlg(4, TimeUtils.GetLocalTimeZone());
                break;
            case "incantation 0":
                SetVerbatimAlg(5, "fly", "bless of magic caster", "infinity wall", "magic ward holy", "life essen");
                break;
        }
    }
}
public class DiBicameral : DiSkillV2
{
    // DiBicameral bicameral = new DiBicameral();
    // bicameral.msgCol.addMSGV2("02:57", "test run ok");
    // Add # for messages that engage other skills

    public TimedMessages msgCol = new TimedMessages();

    public void input(string ear, string skin, string eye)
    {
        msgCol.Tick();

        if (!kokoro.toHeart["dibicameral"].Equals("null"))
        {
            kokoro.toHeart["dibicameral"] = "null";
        }

        if (msgCol.GetMsg())
        {
            string temp = msgCol.GetLastMSG();
            if (!temp.Contains("#"))
            {
                SetSimpleAlg(temp);
            }
            else
            {
                kokoro.toHeart["dibicameral"] = temp.Replace("#", "");
            }
        }
    }

    public new void SetKokoro(Kokoro kokoro)
    {
        base.SetKokoro(kokoro);
        kokoro.toHeart["dibicameral"] = "null";
    }
}
public class DiSkillBundle : DiSkillV2
{
    protected AXSkillBundle axSkillBundle = new AXSkillBundle();

    public override void Input(string ear, string skin, string eye)
    {
        AlgorithmV2 a1 = axSkillBundle.DispenseAlgorithm(ear, skin, eye);
        if (a1 == null)
        {
            return;
        }
        this.outAlg = a1.GetAlg();
        this.outpAlgPriority = a1.GetPriority();
    }

    public new void SetKokoro(Kokoro kokoro)
    {
        base.SetKokoro(kokoro);
        axSkillBundle.SetKokoro(kokoro);
    }

    public void AddSkill(DiSkillV2 skill)
    {
        axSkillBundle.AddSkill(skill);
    }
}
public class SkillBranch : DiSkillV2
{
    // unique skill used to bind similar skills
    /*
    * contains collection of skills
    * mutates active skill if detects conjuration
    * mutates active skill if algorithm results in
    * negative feedback
    * positive feedback negates active skill mutation
    * */
    private Dictionary<string, int> skillRef = new Dictionary<string, int>();
    private SkillHubAlgDispenser skillHub = new SkillHubAlgDispenser();
    private AXLearnability ml;

    public SkillBranch(int tolerance)
    {
        ml = new AXLearnability(tolerance);
    }

    public override void Input(string ear, string skin, string eye)
    {
        // conjuration alg morph
        if (skillRef.ContainsKey(ear))
        {
            skillHub.SetActiveSkillWithMood(skillRef[ear]);
            SetSimpleAlg("hmm");
        }
        // machine learning alg morph
        if (ml.MutateAlg(ear))
        {
            skillHub.CycleActiveSkill();
            SetSimpleAlg("hmm");
        }
        // alg engage
        AlgorithmV2 a1 = skillHub.DispenseAlgorithm(ear, skin, eye);
        if (a1 == null) { return; }
        this.outAlg = a1.GetAlg();
        this.outpAlgPriority = a1.GetPriority();
        ml.PendAlg();
    }

    public void AddSkill(DiSkillV2 skill)
    {
        skillHub.AddSkill(skill);
    }

    public void AddReferencedSkill(DiSkillV2 skill, string conjuration)
    {
        // the conjuration string will engage its respective skill
        skillHub.AddSkill(skill);
        skillRef[conjuration] = skillHub.GetSize();
    }

    // learnability params
    public void AddDefcon(string defcon) { ml.defcons.Add(defcon); }
    public void AddGoal(string goal) { ml.goals.Add(goal); }
    // while alg is pending, cause alg mutation ignoring learnability tolerance:
    public void AddDefconLV5(string defcon5) { ml.defcon5.Add(defcon5); }

    public new void SetKokoro(Kokoro kokoro)
    {
        base.SetKokoro(kokoro);
        skillHub.SetKokoro(kokoro);
    }
}
public class GamiPlus : DiSkillV2
{
    // The grind side of the game, see GamificationN for the reward side
    private readonly int gain;
    private readonly DiSkillV2 skill;
    private readonly AXGamification axGamification;

    public GamiPlus(DiSkillV2 skill, AXGamification axGamification, int gain)
    {
        this.skill = skill;
        this.axGamification = axGamification;
        this.gain = gain;
    }

    public override void Input(string ear, string skin, string eye)
    {
        skill.Input(ear, skin, eye);
    }

    public override void Output(Neuron noiron)
    {
        // Skill activation increases gaming credits
        if (skill.PendingAlgorithm())
        {
            axGamification.IncrementBy(gain);
        }
        skill.Output(noiron);
    }

    public override void SetKokoro(Kokoro kokoro)
    {
        this.skill.SetKokoro(kokoro);
    }
}

public class GamiMinus : DiSkillV2
{
    private readonly AXGamification axGamification;
    private readonly int cost;
    private readonly DiSkillV2 skill;

    public GamiMinus(DiSkillV2 skill, AXGamification axGamification, int cost)
    {
        this.skill = skill;
        this.axGamification = axGamification;
        this.cost = cost;
    }

    public override void Input(string ear, string skin, string eye)
    {
        // Engage skill only if a reward is possible
        if (axGamification.Surplus(cost))
        {
            skill.Input(ear, skin, eye);
        }
    }

    public override void Output(Neuron noiron)
    {
        // Charge reward if an algorithm is pending
        if (skill.PendingAlgorithm())
        {
            axGamification.Reward(cost);
            skill.Output(noiron);
        }
    }

    public override void SetKokoro(Kokoro kokoro)
    {
        this.skill.SetKokoro(kokoro);
    }
}

public class DiGamificationSkillBundle : DiSkillBundle
{
    private readonly AXGamification axGamification = new AXGamification();
    private int gain = 1;
    private int cost = 2;

    public void SetGain(int gain)
    {
        if (gain > 0)
        {
            this.gain = gain;
        }
    }

    public void SetCost(int cost)
    {
        if (cost > 0)
        {
            this.cost = cost;
        }
    }

    public void AddGrindSkill(DiSkillV2 skill)
    {
        axSkillBundle.AddSkill(new GamiPlus(skill, axGamification, gain));
    }

    public void AddCostlySkill(DiSkillV2 skill)
    {
        axSkillBundle.AddSkill(new GamiMinus(skill, axGamification, cost));
    }

    public AXGamification GetAxGamification()
    {
        return axGamification;
    }
}

public class DiGamificationScouter : DiSkillV2
{
    private int lim = 2; // Minimum for mood
    private readonly AXGamification axGamification;
    private readonly Responder noMood = new Responder("bored", "no emotions detected", "neutral");
    private readonly Responder yesMood = new Responder("operational", "efficient", "mission ready", "awaiting orders");

    public DiGamificationScouter(AXGamification axGamification)
    {
        this.axGamification = axGamification;
    }

    public void SetLim(int lim)
    {
        this.lim = lim;
    }

    public override void Input(string ear, string skin, string eye)
    {
        if (!ear.Equals("how are you"))
        {
            return;
        }
        if (axGamification.GetCounter() > lim)
        {
            SetSimpleAlg(yesMood.GetAResponse());
        }
        else
        {
            SetSimpleAlg(noMood.GetAResponse());
        }
    }
}