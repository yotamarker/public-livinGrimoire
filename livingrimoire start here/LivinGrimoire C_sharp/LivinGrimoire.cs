using System;
using System.Collections.Generic;
using System.Collections;
public class AbsDictionaryDB
{
    public virtual void Save(string key, string value)
    {
        // Save to DB (override me)
    }

    public virtual string Load(string key)
    {
        // Override me
        return "null";
    }
}
public abstract class Mutatable
{
    public bool algKillSwitch = false;

    public abstract string Action(string ear, string skin, string eye);
    public abstract bool Completed();

    public string MyName()
    {
        // Returns the class name
        return GetType().Name;
    }
}
public class APSay : Mutatable
{
    // It speaks something x times
    // A most basic skill.
    // Also fun to make the chobit say what you want.

    protected string param;
    private int at;

    public APSay(int repetitions, string param)
    {
        if (repetitions > 10)
        {
            repetitions = 10;
        }
        this.at = repetitions;
        this.param = param;
    }

    public override string Action(string ear, string skin, string eye)
    {
        // TODO: Implement your logic here
        string axnStr = "";
        if (this.at > 0)
        {
            if (!string.Equals(ear, param, StringComparison.OrdinalIgnoreCase))
            {
                axnStr = param;
                this.at--;
            }
        }
        return axnStr;
    }

    public override bool Completed()
    {
        // TODO: Implement your logic here
        return this.at < 1;
    }

}
public class APVerbatim : Mutatable
{
    private List<string> sentences = new List<string>();
    private int at = 0;

    public APVerbatim(params string[] sentences)
    {
        foreach (string sentence in sentences)
        {
            this.sentences.Add(sentence);
        }

        if (sentences.Length == 0)
        {
            at = 30;
        }
    }

    public APVerbatim(List<string> list1)
    {
        this.sentences = new List<string>(list1);

        if (this.sentences.Count == 0)
        {
            at = 30;
        }
    }

    public override string Action(string ear, string skin, string eye)
    {
        string axnStr = "";
        if (at < sentences.Count)
        {
            axnStr = sentences[at];
            at++;
        }
        return axnStr;
    }

    public override bool Completed()
    {
        return at >= sentences.Count;
    }
}
public class GrimoireMemento
{
    private AbsDictionaryDB absDictionaryDB;

    public GrimoireMemento(AbsDictionaryDB absDictionaryDB)
    {
        this.absDictionaryDB = absDictionaryDB;
    }

    public string SimpleLoad(string key)
    {
        return this.absDictionaryDB.Load(key);
    }

    public void SimpleSave(string key, string value)
    {
        if (key == "" || value == "")
        {
            return;
        }
        this.absDictionaryDB.Save(key, value);
    }
}
public class Algorithm
{
    private List<Mutatable> algParts = new List<Mutatable>();

    public Algorithm(List<Mutatable> algParts)
    {
        this.algParts = algParts;
    }

    public List<Mutatable> GetAlgParts()
    {
        return algParts;
    }

    public int GetSize()
    {
        return algParts.Count;
    }
}
public class Kokoro
{
    private string emot = "";

    public string GetEmot()
    {
        return emot;
    }

    public void SetEmot(string emot)
    {
        this.emot = emot;
    }

    public GrimoireMemento grimoireMemento;
    public Hashtable toHeart = new Hashtable();

    public Kokoro(AbsDictionaryDB absDictionaryDB)
    {
        this.grimoireMemento = new GrimoireMemento(absDictionaryDB);
    }
}
public class Neuron
{
    private Dictionary<int, List<Algorithm>> defcons = new Dictionary<int, List<Algorithm>>();

    public Neuron()
    {
        for (int i = 1; i <= 5; i++)
        {
            defcons[i] = new List<Algorithm>();
        }
    }

    public void InsertAlg(int priority, Algorithm alg)
    {
        if (0 < priority && priority < 6)
        {
            if (defcons[priority].Count < 4)
            {
                defcons[priority].Add(alg);
            }
        }
    }

    public Algorithm? GetAlg(int defcon)
    {
        if (defcons[defcon].Count > 0)
        {
            Algorithm temp = defcons[defcon][0];
            defcons[defcon].RemoveAt(0);
            return temp;
        }
        return null;
    }
}
public class Skill
{
    protected Kokoro? kokoro = null; // consciousness, shallow ref class to enable interskill communications
    protected Algorithm? outAlg = null; // skills output
    protected int outpAlgPriority = -1; // defcon 1->5

    public Skill()
    {
    }

    public virtual void Input(string ear, string skin, string eye)
    {
    }

    public virtual void Output(Neuron noiron)
    {
        if (outAlg != null)
        {
            noiron.InsertAlg(outpAlgPriority, outAlg);
            outpAlgPriority = -1;
            outAlg = null;
        }
    }

    public virtual void SetKokoro(Kokoro kokoro)
    {
        this.kokoro = kokoro;
    }

    protected void SetVerbatimAlg(int priority, params string[] sayThis)
    {
        outAlg = SimpleVerbatimAlgorithm(sayThis);
        outpAlgPriority = priority; // 1->5 1 is the highest algorithm priority
    }

    protected void SetSimpleAlg(params string[] sayThis)
    {
        outAlg = SimpleVerbatimAlgorithm(sayThis);
        outpAlgPriority = 4; // 1->5 1 is the highest algorithm priority
    }

    protected void SetVerbatimAlgFromList(int priority, List<string> sayThis)
    {
        outAlg = AlgBuilder(new APVerbatim(sayThis));
        outpAlgPriority = priority; // 1->5 1 is the highest algorithm priority
    }

    protected void AlgPartsFusion(int priority, params Mutatable[] algParts)
    {
        outAlg = AlgBuilder(algParts);
        outpAlgPriority = priority; // 1->5 1 is the highest algorithm priority
    }

    public bool PendingAlgorithm()
    {
        return outAlg != null;
    }
    // skill utils (alg building methods)
    // Alg part based algorithm building methods (var args param)
    public Algorithm AlgBuilder(params Mutatable[] algParts)
    {
        // Returns an algorithm built with the algPart varargs
        List<Mutatable> algParts1 = new List<Mutatable>();
        foreach (Mutatable part in algParts)
        {
            algParts1.Add(part);
        }
        Algorithm algorithm = new Algorithm(algParts1);
        return algorithm;
    }

    // String based algorithm building methods
    public Algorithm SimpleVerbatimAlgorithm(params string[] sayThis)
    {
        // Returns an algorithm that says the sayThis Strings verbatim per think cycle
        return AlgBuilder(new APVerbatim(sayThis));
    }

    public string StrContainsList(string str1, List<string> items)
    {
        // Returns the 1st match between words in a string and values in a list.
        foreach (string temp in items)
        {
            if (str1.Contains(temp))
            {
                return temp;
            }
        }
        return "";
    }
    public virtual string SkillNotes(string param)
    {
        return "notes unknown";
    }

}
public class DiHelloWorld : Skill
{
    // hello world skill for testing purposes
    public DiHelloWorld() : base()
    {
    }

    public override void Input(string ear, string skin, string eye)
    {
        switch (ear)
        {
            case "hello":
                base.SetVerbatimAlg(4, "hello world"); // 1->5 1 is the highest algorithm priority
                break;
        }
    }
    public override string SkillNotes(string param)
    {
        if (param == "notes")
        {
            return "plain hello world skill";
        }
        else if (param == "triggers")
        {
            return "say hello";
        }
        return "note unavailable";
    }

}
public class Cerabellum
{
    // Runs an algorithm
    private int fin;
    private int at;
    private bool incrementAt = false;
    public Algorithm? alg;
    private bool ia = false; // isActive attribute
    private string emot = "";

    public void AdvanceInAlg()
    {
        if (incrementAt)
        {
            incrementAt = false;
            at++;
            if (at == fin)
            {
                ia = false;
            }
        }
    }

    public int GetAt()
    {
        return at;
    }

    public string GetEmot()
    {
        return emot;
    }

    public bool SetAlgorithm(Algorithm algorithm)
    {
        if (!IsActive() && algorithm.GetAlgParts().Count != 0)
        {
            alg = algorithm;
            at = 0;
            fin = algorithm.GetSize();
            ia = true;
            emot = alg.GetAlgParts()[at].MyName(); // Updated line
            return false;
        }
        return true;
    }

    public bool IsActive()
    {
        return ia;
    }

    public string Act(string ear, string skin, string eye)
    {
        string axnStr = "";
        if (!IsActive())
        {
            return axnStr;
        }
        if (at < fin)
        {
            axnStr = alg!.GetAlgParts()[at].Action(ear, skin, eye);
            emot = alg!.GetAlgParts()[at].MyName();
            if (alg!.GetAlgParts()[at].Completed())
            {
                incrementAt = true;
            }
        }
        return axnStr;
    }

    public void DeActivation()
    {
        ia = IsActive() && !alg!.GetAlgParts()[at].algKillSwitch;
    }
}
public class Fusion
{
    private string emot = "";
    private string result = "";
    private Cerabellum[] ceraArr = new Cerabellum[5];

    public Fusion()
    {
        for (int i = 0; i < 5; i++)
        {
            ceraArr[i] = new Cerabellum();
        }
    }

    public string GetEmot()
    {
        return emot;
    }

    public void LoadAlgs(Neuron neuron)
    {
        for (int i = 1; i <= 5; i++)
        {
            if (!ceraArr[i - 1].IsActive())
            {
                Algorithm temp = neuron.GetAlg(i)!;
                if (temp != null)
                {
                    ceraArr[i - 1].SetAlgorithm(temp);
                }
            }
        }
    }

    public string RunAlgs(string ear, string skin, string eye)
    {
        result = "";
        for (int i = 0; i < 5; i++)
        {
            if (!ceraArr[i].IsActive())
            {
                continue;
            }
            result = ceraArr[i].Act(ear, skin, eye);
            ceraArr[i].AdvanceInAlg();
            emot = ceraArr[i].GetEmot();
            ceraArr[i].DeActivation(); // Deactivation if Mutatable.algkillswitch = true
            return result;
        }
        emot = "";
        return result;
    }
}
public class Chobits
{
    public List<Skill> dClasses = new List<Skill>();
    protected Fusion fusion;
    protected Neuron noiron;
    protected Kokoro kokoro = new Kokoro(new AbsDictionaryDB()); // consciousness
    private bool isThinking = false;
    private readonly List<Skill> awareSkills = new List<Skill>();

    public Chobits()
    {
        // c'tor
        this.fusion = new Fusion();
        this.noiron = new Neuron();
    }

    public void SetDataBase(AbsDictionaryDB absDictionaryDB)
    {
        this.kokoro = new Kokoro(absDictionaryDB);
    }

    public Chobits AddSkill(Skill skill)
    {
        // add a skill (builder design patterned func)
        if (this.isThinking)
        {
            return this;
        }
        skill.SetKokoro(this.kokoro);
        this.dClasses.Add(skill);
        return this;
    }

    public Chobits AddSkillAware(Skill skill)
    {
        // add a skill with Chobit Object in their constructor
        skill.SetKokoro(this.kokoro);
        this.awareSkills.Add(skill);
        return this;
    }

    public void ClearSkills()
    {
        // remove all skills
        if (this.isThinking)
        {
            return;
        }
        this.dClasses.Clear();
    }

    public void AddSkills(params Skill[] skills)
    {
        if (this.isThinking)
        {
            return;
        }
        foreach (Skill skill in skills)
        {
            skill.SetKokoro(this.kokoro);
            this.dClasses.Add(skill);
        }
    }

    public void RemoveSkill(Skill skill)
    {
        if (this.isThinking)
        {
            return;
        }
        this.dClasses.Remove(skill);
    }

    public bool ContainsSkill(Skill skill)
    {
        return this.dClasses.Contains(skill);
    }

    public string Think(string ear, string skin, string eye)
    {
        this.isThinking = true;
        foreach (Skill dCls in this.dClasses)
        {
            InOut(dCls, ear, skin, eye);
        }
        this.isThinking = false;
        foreach (Skill dCls2 in this.awareSkills)
        {
            InOut(dCls2, ear, skin, eye);
        }
        this.fusion.LoadAlgs(this.noiron);
        return this.fusion.RunAlgs(ear, skin, eye);
    }

    public string GetSoulEmotion()
    {
        // get the last active AlgPart name
        // the AP is an action, and it also represents
        // an emotion
        return this.fusion.GetEmot();
    }

    protected void InOut(Skill dClass, string ear, string skin, string eye)
    {
        dClass.Input(ear, skin, eye); // new
        dClass.Output(this.noiron);
    }

    public Kokoro GetKokoro()
    {
        // several chobits can use the same soul
        // this enables telepathic communications
        // between chobits in the same project
        return this.kokoro;
    }

    public void SetKokoro(Kokoro kokoro)
    {
        // use this for telepathic communication between different chobits objects
        this.kokoro = kokoro;
    }

    public Fusion GetFusion()
    {
        return this.fusion;
    }

    public List<string> GetSkillList()
    {
        List<string> result = new List<string>();
        foreach (Skill skill in this.dClasses)
        {
            result.Add(skill.GetType().Name);
        }
        return result;
    }
}
public class Brain
{
    public Chobits logicChobit;
    public Chobits hardwareChobit;
    private string emotion = "";
    private string bodyInfo = "";
    private string logicChobitOutput = "";

    public string GetEmotion => emotion;

    public string GetBodyInfo => bodyInfo;

    public string GetLogicChobitOutput => logicChobitOutput;

    public Brain()
    {
        logicChobit = new Chobits();
        hardwareChobit = new Chobits();
        hardwareChobit.SetKokoro(logicChobit.GetKokoro());
    }

    public void DoIt(string ear, string skin, string eye)
    {
        if (!string.IsNullOrEmpty(bodyInfo))
        {
            logicChobitOutput = logicChobit.Think(ear, bodyInfo, eye);
        }
        else
        {
            logicChobitOutput = logicChobit.Think(ear, skin, eye);
        }
        emotion = logicChobit.GetSoulEmotion();
        // Case: Hardware skill wishes to pass info to logical chobit
        bodyInfo = hardwareChobit.Think(logicChobitOutput, skin, eye);
    }
    public void AddLogicalSkill(Skill skill)
    {
        logicChobit.AddSkill(skill);
    }

    public void AddHardwareSkill(Skill skill)
    {
        hardwareChobit.AddSkill(skill);
    }

}
public class DiPrinter : Skill
{
    // hello world skill for testing purposes
    public DiPrinter() : base()
    {
    }

    public override void Input(string ear, string skin, string eye)
    {
        if (ear == "")
        {
            return;
        }
        Console.WriteLine(ear);
    }
}
