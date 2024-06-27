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
        if (key.StartsWith("AP") || key == "" || value == "")
        {
            return;
        }
        this.absDictionaryDB.Save(key, value);
    }
}
public class CldBool
{
    // cloudian: this class is used to provide shadow reference to a boolean variable
    private bool modeActive = false;

    public bool GetModeActive()
    {
        return modeActive;
    }

    public void SetModeActive(bool modeActive)
    {
        this.modeActive = modeActive;
    }
}
public class APCldVerbatim : Mutatable
{
    // This algorithm part says each past param verbatim
    private List<string> sentences = new List<string>();
    private int at = 0;
    private CldBool cldBool; // Access via shallow reference

    public APCldVerbatim(CldBool cldBool, params string[] sentences)
    {
        foreach (string sentence in sentences)
        {
            this.sentences.Add(sentence);
        }
        if (sentences.Length == 0)
        {
            at = 30;
        }
        this.cldBool = cldBool;
        this.cldBool.SetModeActive(true);
    }

    public APCldVerbatim(CldBool cldBool, List<string> list1)
    {
        this.sentences = new List<string>(list1);
        if (this.sentences.Count == 0)
        {
            at = 30;
        }
        this.cldBool = cldBool;
        this.cldBool.SetModeActive(true);
    }

    public override string Action(string ear, string skin, string eye)
    {
        string axnStr = "";
        if (at < sentences.Count)
        {
            axnStr = sentences[at];
            at++;
        }
        cldBool.SetModeActive(!(at >= sentences.Count));
        return axnStr;
    }

    public override bool Completed()
    {
        return at >= sentences.Count;
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

    public Algorithm GetAlg(int defcon)
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
public class DISkillUtils
{
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

    // String part based algorithm building methods with cloudian (shallow ref object to inform on alg completion)
    public Algorithm SimpleCloudianVerbatimAlgorithm(CldBool cldBool, params string[] sayThis)
    {
        // Returns an algorithm that says the sayThis Strings verbatim per think cycle
        return AlgBuilder(new APCldVerbatim(cldBool, sayThis));
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
}
public class DiSkillV2
{
    protected Kokoro kokoro = new Kokoro(new AbsDictionaryDB()); // consciousness, shallow ref class to enable interskill communications
    protected DISkillUtils diSkillUtils = new DISkillUtils();
    protected Algorithm outAlg = null; // skills output
    protected int outpAlgPriority = -1; // defcon 1->5

    public DiSkillV2()
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

    public void SetKokoro(Kokoro kokoro)
    {
        this.kokoro = kokoro;
    }

    protected void SetVerbatimAlg(int priority, params string[] sayThis)
    {
        outAlg = diSkillUtils.SimpleVerbatimAlgorithm(sayThis);
        outpAlgPriority = priority; // 1->5 1 is the highest algorithm priority
    }

    protected void SetSimpleAlg(params string[] sayThis)
    {
        outAlg = diSkillUtils.SimpleVerbatimAlgorithm(sayThis);
        outpAlgPriority = 4; // 1->5 1 is the highest algorithm priority
    }

    protected void SetVerbatimAlgFromList(int priority, List<string> sayThis)
    {
        outAlg = diSkillUtils.AlgBuilder(new APVerbatim(sayThis));
        outpAlgPriority = priority; // 1->5 1 is the highest algorithm priority
    }

    protected void AlgPartsFusion(int priority, params Mutatable[] algParts)
    {
        outAlg = diSkillUtils.AlgBuilder(algParts);
        outpAlgPriority = priority; // 1->5 1 is the highest algorithm priority
    }

    public bool PendingAlgorithm()
    {
        return outAlg != null;
    }
}
public class DiHelloWorld : DiSkillV2
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
}
public class Cerabellum
{
    // Runs an algorithm
    private int fin;
    private int at;
    private bool incrementAt = false;
    public Algorithm alg;
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
            axnStr = alg.GetAlgParts()[at].Action(ear, skin, eye);
            emot = alg.GetAlgParts()[at].MyName();
            if (alg.GetAlgParts()[at].Completed())
            {
                incrementAt = true;
            }
        }
        return axnStr;
    }

    public void DeActivation()
    {
        ia = IsActive() && !alg.GetAlgParts()[at].algKillSwitch;
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
                Algorithm temp = neuron.GetAlg(i);
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
public class Thinkable
{
    public string Think(string ear, string skin, string eye)
    {
        // Override me
        return "";
    }
}
public class Chobits : Thinkable
{
    protected List<DiSkillV2> dClasses = new List<DiSkillV2>();
    protected Fusion fusion;
    protected Neuron noiron;
    protected Kokoro kokoro = new Kokoro(new AbsDictionaryDB()); // consciousness

    public Chobits() : base()
    {
        fusion = new Fusion();
        noiron = new Neuron();
    }

    public void SetDataBase(AbsDictionaryDB absDictionaryDB)
    {
        kokoro = new Kokoro(absDictionaryDB);
    }

    public Chobits AddSkill(DiSkillV2 skill)
    {
        skill.SetKokoro(kokoro);
        dClasses.Add(skill);
        return this;
    }

    public void ClearSkills()
    {
        // Remove all skills
        dClasses.Clear();
    }

    public void AddSkills(params DiSkillV2[] skills)
    {
        foreach (DiSkillV2 skill in skills)
        {
            skill.SetKokoro(kokoro);
            dClasses.Add(skill);
        }
    }

    public void RemoveSkill(DiSkillV2 skill)
    {
        dClasses.Remove(skill);
    }

    public bool ContainsSkill(DiSkillV2 skill)
    {
        return dClasses.Contains(skill);
    }

    public new string Think(string ear, string skin, string eye)
    {
        foreach (DiSkillV2 dCls in dClasses)
        {
            InOut(dCls, ear, skin, eye);
        }
        fusion.LoadAlgs(noiron);
        return fusion.RunAlgs(ear, skin, eye);
    }
    public string GetSoulEmotion()
    {
        return fusion.GetEmot();
    }

    protected void InOut(DiSkillV2 dClass, string ear, string skin, string eye)
    {
        dClass.Input(ear, skin, eye); // New
        dClass.Output(noiron);
    }

    public Fusion GetFusion()
    {
        return fusion;
    }

    public List<string> GetSkillList()
    {
        List<string> result = new List<string>();
        foreach (DiSkillV2 skill in dClasses)
        {
            result.Add(skill.GetType().Name);
        }
        return result;
    }

    public Kokoro GetKokoro()
    {
        return kokoro;
    }

    public void SetKokoro(Kokoro kokoro)
    {
        this.kokoro = kokoro;
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
}
public class DiPrinter : DiSkillV2
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
