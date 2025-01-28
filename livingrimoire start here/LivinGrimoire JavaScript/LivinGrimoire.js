// livingrimoire.js

class AbsDictionaryDB {
    save(key, value) {
        // Save to DB (override me)
    }

    load(key) {
        // Override me
        return "null";
    }
}

class Mutatable {
    constructor() {
        this.algKillSwitch = false;
    }

    // Abstract methods (to be overridden by subclasses)
    Action(ear, skin, eye) {
        throw new Error('Action method must be overridden');
    }

    Completed() {
        throw new Error('Completed method must be overridden');
    }

    MyName() {
        // Returns the class name
        return this.constructor.name;
    }
}

class APSay extends Mutatable {
    constructor(repetitions, param) {
        super();
        this.param = param;
        this.at = repetitions > 10 ? 10 : repetitions;
    }

    Action(ear, skin, eye) {
        let axnStr = "";
        if (this.at > 0) {
            if (ear.toLowerCase() !== this.param.toLowerCase()) {
                axnStr = this.param;
                this.at--;
            }
        }
        return axnStr;
    }

    Completed() {
        return this.at < 1;
    }
}

class APVerbatim extends Mutatable {
    constructor(...sentences) {
        super();
        this.sentences = sentences.length > 0 ? sentences : [];
        this.at = sentences.length === 0 ? 30 : 0;
    }

    Action(ear, skin, eye) {
        let axnStr = "";
        if (this.at < this.sentences.length) {
            axnStr = this.sentences[this.at];
            this.at++;
        }
        return axnStr;
    }

    Completed() {
        return this.at >= this.sentences.length;
    }
}

class GrimoireMemento {
    constructor(absDictionaryDB) {
        this.absDictionaryDB = absDictionaryDB;
    }

    SimpleLoad(key) {
        return this.absDictionaryDB.load(key);
    }

    SimpleSave(key, value) {
        if (!key || !value) {
            return;
        }
        this.absDictionaryDB.save(key, value);
    }
}

class Algorithm {
    constructor(algParts) {
        this.algParts = algParts;
    }

    GetAlgParts() {
        return this.algParts;
    }

    GetSize() {
        return this.algParts.length;
    }
}
class Kokoro {
    constructor(absDictionaryDB) {
        this.emot = "";
        this.grimoireMemento = new GrimoireMemento(absDictionaryDB);
        this.toHeart = new Map();
    }

    GetEmot() {
        return this.emot;
    }

    SetEmot(emot) {
        this.emot = emot;
    }
}

class Neuron {
    constructor() {
        this.defcons = new Map();
        for (let i = 1; i <= 5; i++) {
            this.defcons.set(i, []);
        }
    }

    InsertAlg(priority, alg) {
        if (priority > 0 && priority < 6) {
            const algList = this.defcons.get(priority);
            if (algList.length < 4) {
                algList.push(alg);
            }
        }
    }

    GetAlg(defcon) {
        const algList = this.defcons.get(defcon);
        if (algList && algList.length > 0) {
            return algList.shift();
        }
        return null;
    }
}

class Skill {
    constructor() {
        this.kokoro = null; // consciousness, shallow ref class to enable interskill communications
        this.outAlg = null; // skills output
        this.outpAlgPriority = -1; // defcon 1->5
    }

    Input(ear, skin, eye) {
        // Virtual method to be overridden by subclasses
    }

    Output(noiron) {
        if (this.outAlg !== null) {
            noiron.InsertAlg(this.outpAlgPriority, this.outAlg);
            this.outpAlgPriority = -1;
            this.outAlg = null;
        }
    }

    SetKokoro(kokoro) {
        this.kokoro = kokoro;
    }

    SetVerbatimAlg(priority, ...sayThis) {
        this.outAlg = this.SimpleVerbatimAlgorithm(...sayThis);
        this.outpAlgPriority = priority; // 1->5 1 is the highest algorithm priority
    }

    SetSimpleAlg(...sayThis) {
        this.outAlg = this.SimpleVerbatimAlgorithm(...sayThis);
        this.outpAlgPriority = 4; // 1->5 1 is the highest algorithm priority
    }

    SetVerbatimAlgFromList(priority, sayThis) {
        this.outAlg = this.AlgBuilder(new APVerbatim(sayThis));
        this.outpAlgPriority = priority; // 1->5 1 is the highest algorithm priority
    }

    AlgPartsFusion(priority, ...algParts) {
        this.outAlg = this.AlgBuilder(...algParts);
        this.outpAlgPriority = priority; // 1->5 1 is the highest algorithm priority
    }

    PendingAlgorithm() {
        return this.outAlg !== null;
    }

    // skill utils (alg building methods)
    // Alg part based algorithm building methods (var args param)
    AlgBuilder(...algParts) {
        // Returns an algorithm built with the algPart varargs
        const algParts1 = [];
        for (const part of algParts) {
            algParts1.push(part);
        }
        return new Algorithm(algParts1);
    }

    // String based algorithm building methods
    SimpleVerbatimAlgorithm(...sayThis) {
        // Returns an algorithm that says the sayThis Strings verbatim per think cycle
        return this.AlgBuilder(new APVerbatim(...sayThis));
    }

    StrContainsList(str1, items) {
        // Returns the 1st match between words in a string and values in a list.
        for (const temp of items) {
            if (str1.includes(temp)) {
                return temp;
            }
        }
        return "";
    }

    SkillNotes(param) {
        return "notes unknown";
    }
}

class DiHelloWorld extends Skill {
    // hello world skill for testing purposes
    constructor() {
        super();
    }

    Input(ear, skin, eye) {
        switch (ear) {
            case "hello":
                super.SetVerbatimAlg(4, "hello world"); // 1->5 1 is the highest algorithm priority
                break;
        }
    }

    SkillNotes(param) {
        if (param === "notes") {
            return "plain hello world skill";
        } else if (param === "triggers") {
            return "say hello";
        }
        return "note unavailable";
    }
}

class Cerabellum {
    constructor() {
        this.fin = 0;
        this.at = 0;
        this.incrementAt = false;
        this.alg = null;
        this.ia = false; // isActive attribute
        this.emot = "";
    }

    AdvanceInAlg() {
        if (this.incrementAt) {
            this.incrementAt = false;
            this.at++;
            if (this.at === this.fin) {
                this.ia = false;
            }
        }
    }

    GetAt() {
        return this.at;
    }

    GetEmot() {
        return this.emot;
    }

    SetAlgorithm(algorithm) {
        if (!this.IsActive() && algorithm.GetAlgParts().length !== 0) {
            this.alg = algorithm;
            this.at = 0;
            this.fin = algorithm.GetSize();
            this.ia = true;
            this.emot = this.alg.GetAlgParts()[this.at].MyName(); // Updated line
            return false;
        }
        return true;
    }

    IsActive() {
        return this.ia;
    }

    Act(ear, skin, eye) {
        let axnStr = "";
        if (!this.IsActive()) {
            return axnStr;
        }
        if (this.at < this.fin) {
            axnStr = this.alg.GetAlgParts()[this.at].Action(ear, skin, eye);
            this.emot = this.alg.GetAlgParts()[this.at].MyName();
            if (this.alg.GetAlgParts()[this.at].Completed()) {
                this.incrementAt = true;
            }
        }
        return axnStr;
    }

    DeActivation() {
        this.ia = this.IsActive() && !this.alg.GetAlgParts()[this.at].algKillSwitch;
    }
}

class Fusion {
    constructor() {
        this.emot = "";
        this.result = "";
        this.ceraArr = Array.from({ length: 5 }, () => new Cerabellum());
    }

    GetEmot() {
        return this.emot;
    }

    LoadAlgs(neuron) {
        for (let i = 1; i <= 5; i++) {
            if (!this.ceraArr[i - 1].IsActive()) {
                const temp = neuron.GetAlg(i);
                if (temp !== null) {
                    this.ceraArr[i - 1].SetAlgorithm(temp);
                }
            }
        }
    }

    RunAlgs(ear, skin, eye) {
        this.result = "";
        for (let i = 0; i < 5; i++) {
            if (!this.ceraArr[i].IsActive()) {
                continue;
            }
            this.result = this.ceraArr[i].Act(ear, skin, eye);
            this.ceraArr[i].AdvanceInAlg();
            this.emot = this.ceraArr[i].GetEmot();
            this.ceraArr[i].DeActivation(); // Deactivation if Mutatable.algKillSwitch = true
            return this.result;
        }
        this.emot = "";
        return this.result;
    }
}

class Chobits {
    constructor() {
        this.dClasses = [];
        this.fusion = new Fusion();
        this.noiron = new Neuron();
        this.kokoro = new Kokoro(new AbsDictionaryDB()); // consciousness
        this.isThinking = false;
        this.awareSkills = [];
    }

    SetDataBase(absDictionaryDB) {
        this.kokoro = new Kokoro(absDictionaryDB);
    }

    AddSkill(skill) {
        // add a skill (builder design patterned func)
        if (this.isThinking) {
            return this;
        }
        skill.SetKokoro(this.kokoro);
        this.dClasses.push(skill);
        return this;
    }

    AddSkillAware(skill) {
        // add a skill with Chobit Object in their constructor
        skill.SetKokoro(this.kokoro);
        this.awareSkills.push(skill);
        return this;
    }

    ClearSkills() {
        // remove all skills
        if (this.isThinking) {
            return;
        }
        this.dClasses = [];
    }

    AddSkills(...skills) {
        if (this.isThinking) {
            return;
        }
        for (const skill of skills) {
            skill.SetKokoro(this.kokoro);
            this.dClasses.push(skill);
        }
    }

    RemoveSkill(skill) {
        if (this.isThinking) {
            return;
        }
        const index = this.dClasses.indexOf(skill);
        if (index > -1) {
            this.dClasses.splice(index, 1);
        }
    }

    ContainsSkill(skill) {
        return this.dClasses.includes(skill);
    }

    Think(ear, skin, eye) {
        this.isThinking = true;
        for (const dCls of this.dClasses) {
            this.InOut(dCls, ear, skin, eye);
        }
        this.isThinking = false;
        for (const dCls2 of this.awareSkills) {
            this.InOut(dCls2, ear, skin, eye);
        }
        this.fusion.LoadAlgs(this.noiron);
        return this.fusion.RunAlgs(ear, skin, eye);
    }

    GetSoulEmotion() {
        // get the last active AlgPart name
        // the AP is an action, and it also represents
        // an emotion
        return this.fusion.GetEmot();
    }

    InOut(dClass, ear, skin, eye) {
        dClass.Input(ear, skin, eye); // new
        dClass.Output(this.noiron);
    }

    GetKokoro() {
        // several chobits can use the same soul
        // this enables telepathic communications
        // between chobits in the same project
        return this.kokoro;
    }

    SetKokoro(kokoro) {
        // use this for telepathic communication between different chobits objects
        this.kokoro = kokoro;
    }

    GetFusion() {
        return this.fusion;
    }

    GetSkillList() {
        const result = [];
        for (const skill of this.dClasses) {
            result.push(skill.constructor.name);
        }
        return result;
    }
}

class Brain {
    constructor() {
        this.logicChobit = new Chobits();
        this.hardwareChobit = new Chobits();
        this.hardwareChobit.SetKokoro(this.logicChobit.GetKokoro());
        this.emotion = "";
        this.bodyInfo = "";
        this.logicChobitOutput = "";
    }

    get GetEmotion() {
        return this.emotion;
    }

    get GetBodyInfo() {
        return this.bodyInfo;
    }

    get GetLogicChobitOutput() {
        return this.logicChobitOutput;
    }

    DoIt(ear, skin, eye) {
        if (this.bodyInfo) {
            this.logicChobitOutput = this.logicChobit.Think(ear, this.bodyInfo, eye);
        } else {
            this.logicChobitOutput = this.logicChobit.Think(ear, skin, eye);
        }
        this.emotion = this.logicChobit.GetSoulEmotion();
        // Case: Hardware skill wishes to pass info to logical chobit
        this.bodyInfo = this.hardwareChobit.Think(this.logicChobitOutput, skin, eye);
    }

    AddLogicalSkill(skill) {
        this.logicChobit.AddSkill(skill);
    }

    AddHardwareSkill(skill) {
        this.hardwareChobit.AddSkill(skill);
    }
}

class DiPrinter extends Skill {
    // hello world skill for testing purposes
    constructor() {
        super();
    }

    Input(ear, skin, eye) {
        if (ear === "") {
            return;
        }
        console.log(ear);
    }
}


module.exports = { AbsDictionaryDB, Mutatable, APSay, APVerbatim, GrimoireMemento, Algorithm, Kokoro, Neuron, Skill, DiHelloWorld, Cerabellum, Fusion, Chobits, Brain, DiPrinter };
