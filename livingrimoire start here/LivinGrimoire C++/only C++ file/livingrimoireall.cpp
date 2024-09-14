#include <iostream>
#include <cctype>
#include <string>
#include <typeinfo>
#include <cctype>
#include <algorithm>
#include <vector>
#include <memory>
#include <map>
#include <unordered_map>
#include <array>
#include <queue>

using namespace std;

//Utility functions
namespace lgUtil {
	//Source: https://stackoverflow.com/questions/11635/case-insensitive-string-comparison-in-c
	inline bool ichar_equals(char a, char b)
	{
		return std::tolower(static_cast<unsigned char>(a)) ==
			std::tolower(static_cast<unsigned char>(b));
	}

	//Case insensitive comparison
	inline bool iequals(const std::string& a, const std::string& b)
	{
		return std::equal(a.begin(), a.end(), b.begin(), b.end(), ichar_equals);
	}
}

class AbsDictionaryDB {
public:
    inline void save(const string& key, const string& value) {
        // save to DB (override me)
    }

    inline string load(const string& key) {
        // override me
        return "null";
    }
};

class Mutable
{
public:
    Mutable() : algKillSwitch(false) {}
    virtual ~Mutable() {}

	virtual string action(const string& ear, const string& skin, const string& eye) = 0;
    virtual bool completed() = 0;
    string myName() {
        // Returns the class name
        return typeid(this).name();
    }
    
    bool algKillSwitch;
};

class APSay : public Mutable
{
public:
    APSay(int repetitions, const string& param);

    virtual bool completed();
    virtual string action(const string& ear, const string& skin, const string& eye);

protected:
    string param;
private:

    int at;
};

class APVerbatim : public Mutable
{
public:

    APVerbatim(initializer_list<string> initlist);
    APVerbatim(vector<string>& list1);

    virtual string action(const string& ear, const string& skin, const string& eye);
    virtual bool completed();
private:
    int at;
    vector<string> sentences;
};

class GrimoireMemento
{
public:
    GrimoireMemento(shared_ptr<AbsDictionaryDB>);
    ~GrimoireMemento() {}

    string simpleLoad(const string& key);
    void simpleSave(const string& key, const string& value);
private:
    shared_ptr<AbsDictionaryDB> absDictionaryDB;
};

// a step-by-step plan to achieve a goal
class Algorithm
{
public:
    Algorithm(vector<shared_ptr<Mutable>>& algParts);

    vector<shared_ptr<Mutable>>& getAlgParts();
    int getSize();
private:
    vector<shared_ptr<Mutable>> algParts;
};

class CldBool {
    //cloudian : this class is used to provide shadow reference to a boolean variable
public:
    CldBool() : modeActive(false) {}
    ~CldBool() {}

    bool getModeActive() {
        return modeActive;
    }

    void setModeActive(bool bModeActive) {
        modeActive = bModeActive;
    }

private:
    bool modeActive;
};

class APCldVerbatim : public Mutable
{
public:
    APCldVerbatim(CldBool* cldBool, initializer_list<string> initlist);
    APCldVerbatim(CldBool* cldBool, vector<string>& list1);

    virtual string action(const string& ear, const string& skin, const string& eye);
    virtual bool completed();
private:
    vector<string> sentences;
    int at = 0;
    CldBool* cldBool;
};

class Kokoro {
public:
    Kokoro(shared_ptr<AbsDictionaryDB> absDictionaryDB);
    ~Kokoro() {}

    string getEmot();

    void setEmot(const string& emot);

    GrimoireMemento* getGrimoireMemento();

    unordered_map<string, string> toHeart;

private:
    string emot;
    unique_ptr<GrimoireMemento> grimoireMemento;
};

class Neuron
{
public:
    Neuron();

    void insertAlg(int priority, const shared_ptr<Algorithm> alg);
    shared_ptr<Algorithm> getAlg(int defcon);

private:
    array<queue<shared_ptr<Algorithm>>, 6> defcons;
};

namespace DISkillUtils
{
    string strContainsList(const string& str1, vector<string>& items);
};

class DiSkillV2
{
public:
    DiSkillV2() : outAlg(nullptr), lpApVerb(nullptr), outpAlgPriority(-1), kokoro(nullptr) {}
    virtual ~DiSkillV2() {}

    // skill triggers and algorithmic logic
    virtual void input(const string& ear, const string& skin, const string& eye) = 0;

    bool pendingAlgorithm();
    void output(Neuron* neuron);
    void setKokoro(Kokoro* kokoro);
    string myName();

protected:
    void setVerbatimAlg(int priority, initializer_list<string> sayThis);
    void setSimpleAlg(initializer_list<string> sayThis);
    void setVerbatimAlgFromList(int priority, vector<string> sayThis);
    void algPartsFusion(int priority, initializer_list<shared_ptr<Mutable>> algParts);

    // alg part based algorithm building methods
    // var args param
    void algBuilder(initializer_list<shared_ptr<Mutable>> algParts);

    // string based algorithm building methods
    void simpleVerbatimAlgorithm(initializer_list<string> sayThis);
    void simpleVerbatimAlgorithm(vector<string>& sayThis);
    // string part based algorithm building methods with cloudian (shallow ref object to inform on alg completion)
    void simpleCloudiandVerbatimAlgorithm(CldBool* cldBool, initializer_list<string> sayThis);

    Kokoro* kokoro; // consciousness, shallow ref class to enable interskill communications
    shared_ptr<Algorithm> outAlg; // skills output
    shared_ptr<Mutable> lpApVerb;
    int outpAlgPriority; // defcon 1->5
private:
};
// logical skill for testing
class DiHelloWorld : public DiSkillV2
{
public:
    DiHelloWorld() : DiSkillV2() {}

    virtual void input(const string& ear, const string& skin, const string& eye);
};

class Cerabellum
{
public:
    Cerabellum() : fin(0), at(0), incrementAt(false), bIsActive(false), alg(nullptr) {}
    ~Cerabellum() {}

    int getAt();
    void advanceInAlg();
    string getEmot();
    bool setAlgorithm(const shared_ptr<Algorithm> algorithm);
    bool isActive();
    string act(const string& ear, const string& skin, const string& eye);


    void deActivation();

private:
    shared_ptr<Algorithm> alg;
    int fin;
    int at;
    bool incrementAt;
    bool bIsActive;
    string emot;

};

class Fusion
{
public:
    Fusion();
    ~Fusion() {};

    string getEmot();
    void loadAlgs(Neuron* neuron);
    string runAlgs(const string& ear, const string& skin, const string& eye);


private:
    string emot;
    array<Cerabellum, 5> ceraArr;
};

class Thinkable {
public:
    virtual string think(const string& ear, const string& skin, const string& eye) = 0;
};

class Chobits : public Thinkable
{
public:
    Chobits();
    ~Chobits();

    void setDataBase(shared_ptr<AbsDictionaryDB> absDictionaryDB);
    Chobits* addSkill(DiSkillV2* skill);
    void clearSkills();
    void addSkills(initializer_list<DiSkillV2*> skills);
    void removeSkill(DiSkillV2* skill);
    bool containsSkill(DiSkillV2* skill);
    virtual string think(const string& ear, const string& skin, const string& eye);
    string getSoulEmotion();
    shared_ptr<Kokoro> getKokoro();
    void setKokoro(shared_ptr<Kokoro> kokoro);
    Fusion* getFusion();
    vector<string> getSkillList(vector<string>&);
protected:
    void inOut(DiSkillV2* dClass, const string& ear, const string& skin, const string& eye);

    vector<DiSkillV2*> dClasses;
    unique_ptr<Fusion> fusion;
    unique_ptr<Neuron> neuron;
    shared_ptr<Kokoro> kokoro; // consciousness
};

class Brain
{
public:
    Brain();
    ~Brain() {}

    Chobits* getLogicChobit();
    Chobits* getHardwareChobit();
    string getEmotion();
    string getBodyInfo();
    string getLogicChobitOutput();
    void doIt(const string& ear, const string& skin, const string& eye);
    void addLogicalSkill(DiSkillV2* skill);
    void addHardwareSkill(DiSkillV2* skill);
private:
    string emotion;
    string bodyInfo;
    string logicChobitOutput;
    unique_ptr<Chobits> logicChobit;
    unique_ptr<Chobits> hardwareChobit;
};

class DiSysOut : public DiSkillV2
{
public:
    virtual void input(const string& ear, const string& skin, const string& eye);
};

//**Implementation**//

//AbsDictionaryDB
//Mutatable

//APSay:Mutatable
APSay::APSay(int repetitions, const string& szParam) : Mutable(), param(szParam)
{
    if (repetitions > 10) {
        repetitions = 10;
    }
    at = repetitions;
}

bool APSay::completed()
{
    return at < 1;
}

string APSay::action(const string& ear, const string& skin, const string& eye)
{
    string axnStr = "";
    if (at > 0) {

        if (!lgUtil::iequals(ear, param)) {
            axnStr = param;
            at--;
        }
    }
    return axnStr;
}

//APVerbatim:Mutatable
APVerbatim::APVerbatim(initializer_list<string> initlist) : Mutable(), at(0)
{
    for (auto& word : initlist) {
        sentences.push_back(word);
    }
    if (0 == sentences.size()) {
        at = 30;
    }
}

APVerbatim::APVerbatim(vector<string>& list1) : Mutable(), at(0)
{
    sentences.insert(sentences.end(), list1.begin(), list1.end());
    if (0 == sentences.size()) {
        at = 30;
    }
}

string APVerbatim::action(const string& ear, const string& skin, const string& eye)
{
    string axnStr = "";
    if (at < sentences.size()) {
        axnStr = sentences.at(at);
        at++;
    }
    return axnStr;
}

bool APVerbatim::completed()
{
    return at >= sentences.size();
}

//GrimoireMemento

GrimoireMemento::GrimoireMemento(shared_ptr<AbsDictionaryDB> lpAbsDictionaryDB)
{
    absDictionaryDB = lpAbsDictionaryDB;
}

string GrimoireMemento::simpleLoad(const string& key)
{
    return absDictionaryDB->load(key);
}

void GrimoireMemento::simpleSave(const string& key, const string& value)
{
    if (key.empty() || key.substr(0, 2) == "AP" || value.empty())
        return;
    absDictionaryDB->save(key, value);
}

//Algorithm
Algorithm::Algorithm(vector<shared_ptr<Mutable>>& vecAlgParts)
{
    algParts.insert(algParts.begin(), vecAlgParts.begin(), vecAlgParts.end());
}

vector<shared_ptr<Mutable>>& Algorithm::getAlgParts()
{
    return algParts;
}

int Algorithm::getSize()
{
	return algParts.size();
}

//CldBool

//APCldVerbatim:APVerbatim

APCldVerbatim::APCldVerbatim(CldBool* lpCldBool, initializer_list<string> initlist) : Mutable(), at(0), cldBool(lpCldBool)
{
    for (auto& word : initlist) {
        sentences.push_back(word);
    }
    if (0 == sentences.size()) {
        at = 30;
    }
    cldBool->setModeActive(true);
}

APCldVerbatim::APCldVerbatim(CldBool* lpCldBool, vector<string>& list1) : Mutable(), at(0), cldBool(lpCldBool)
{
    sentences.insert(sentences.end(), list1.begin(), list1.end());
    if (0 == sentences.size()) {
        at = 30;
    }
    cldBool->setModeActive(true);
}

string APCldVerbatim::action(const string& ear, const string& skin, const string& eye)
{
    string axnStr = "";
    if (at < sentences.size()) {
        axnStr = sentences.at(at);
        at++;
    }
    cldBool->setModeActive(!(at >= sentences.size()));
    return axnStr;
}

bool APCldVerbatim::completed()
{
    return at >= sentences.size();
}

//Kokoro

Kokoro::Kokoro(shared_ptr<AbsDictionaryDB> lpAbsDictionaryDB) {
    grimoireMemento = make_unique<GrimoireMemento>(lpAbsDictionaryDB);
}

string Kokoro::getEmot() {
    return emot;
}

void Kokoro::setEmot(const string& szEmot) {
    emot = szEmot;
}

GrimoireMemento* Kokoro::getGrimoireMemento()
{
    return grimoireMemento.get();
}

//Neuron

Neuron::Neuron()
{
}

void Neuron::insertAlg(int priority, const shared_ptr<Algorithm> alg)
{
	defcons[priority].push(alg);
}

shared_ptr<Algorithm> Neuron::getAlg(int defcon)
{
	if (defcons.at(defcon).empty())
		return nullptr;
	shared_ptr<Algorithm> alg = defcons.at(defcon).front();
	defcons.at(defcon).pop();
	return alg;
}

//DiSkillUtils

string DISkillUtils::strContainsList(const string& str1, vector<string>& items) {
    // returns the 1st match between words in a string and values in a list.
    for (string temp : items) {
        if (str1.find(temp)) {
            return temp;
        }
    }
    return "";
}

//DiSkillV2

bool DiSkillV2::pendingAlgorithm()
{
    // is an algorithm pending?
    return outAlg != nullptr;
}

string DiSkillV2::myName() {
    // Returns the class name
    return typeid(this).name();
}

// in skill algorithm building shortcut methods:
void DiSkillV2::setVerbatimAlg(int priority, initializer_list<string> sayThis)
{
    // build a simple output algorithm to speak string by string per think cycle
// uses varargs param
    simpleVerbatimAlgorithm(sayThis);
    outpAlgPriority = priority; // 1->5 1 is the highest algorithm priority
}

void DiSkillV2::setSimpleAlg(initializer_list<string> sayThis)
{
    // based on the setVerbatimAlg method
    // build a simple output algorithm to speak string by string per think cycle
    // uses varargs param
    simpleVerbatimAlgorithm(sayThis);
    outpAlgPriority = 4; // 1->5 1 is the highest algorithm priority
}

void DiSkillV2::setVerbatimAlgFromList(int priority, vector<string> sayThis)
{
    // build a simple output algorithm to speak string by string per think cycle
    // uses list param
    simpleVerbatimAlgorithm(sayThis);
    outpAlgPriority = priority; // 1->5 1 is the highest algorithm priority
}

void DiSkillV2::algPartsFusion(int priority, initializer_list<shared_ptr<Mutable>> algParts)
{
    // build a custom algorithm out of a chain of algorithm parts(actions)
    algBuilder(algParts);
    outpAlgPriority = priority; // 1->5 1 is the highest algorithm priority
}

// extraction of skill algorithm to run (if there is one)
void DiSkillV2::output(Neuron* neuron)
{
    if (outAlg != nullptr) {
        neuron->insertAlg(outpAlgPriority, outAlg);
        outpAlgPriority = -1;
        outAlg = nullptr;
    }
}

void DiSkillV2::setKokoro(Kokoro* lpKokoro)
{
    // use this for telepathic communication between different chobits objects
    kokoro = lpKokoro;
}

void DiSkillV2::algBuilder(initializer_list<shared_ptr<Mutable>> algParts)
{
    // returns an algorithm built with the algPart varargs
    vector<shared_ptr<Mutable>> algParts1;
    for (shared_ptr<Mutable> algPart : algParts) {
        algParts1.push_back(algPart);
    }
    outAlg = make_shared<Algorithm>(algParts1);
}

void DiSkillV2::simpleVerbatimAlgorithm(initializer_list<string> sayThis)
{
    // returns an algorithm that says the sayThis Strings verbatim per think cycle
    lpApVerb = make_shared<APVerbatim>(sayThis);
    algBuilder({ lpApVerb });
}

void DiSkillV2::simpleVerbatimAlgorithm(vector<string>& sayThis)
{
    // returns an algorithm that says the sayThis Strings verbatim per think cycle
    lpApVerb = make_shared<APVerbatim>(sayThis);
    algBuilder({ lpApVerb });
}

void DiSkillV2::simpleCloudiandVerbatimAlgorithm(CldBool* cldBool, initializer_list<string> sayThis)
{
    // returns an algorithm that says the sayThis Strings verbatim per think cycle
    lpApVerb = make_shared<APCldVerbatim>(cldBool, sayThis);
    algBuilder({ lpApVerb });
}

void DiSysOut::input(const string& ear, const string& skin, const string& eye)
{
    if (!ear.empty() && ear.find("#") == string::npos) {
        cout << ear;
    }
}

//DiHelloWorld:DiSkillV2

void DiHelloWorld::input(const string& ear, const string& skin, const string& eye)
{
    if (ear == "hello") {
        setVerbatimAlg(4, { "hello world" }); // 1->5 1 is the highest algorithm priority
    }
}

//Cerabellum

int Cerabellum::getAt()
{
    return at;
}

void Cerabellum::advanceInAlg()
{
    if (incrementAt) {
        incrementAt = false;
        at++;
        if (at == fin) {
            bIsActive = false;
        }
    }
}

string Cerabellum::getEmot()
{
    return emot;
}

bool Cerabellum::setAlgorithm(const shared_ptr<Algorithm> algorithm)
{
    if (!bIsActive && (!algorithm->getAlgParts().empty())) {
        alg = algorithm;
        at = 0;
        fin = algorithm->getSize();
        bIsActive = true;
        emot = alg->getAlgParts().at(at)->myName(); // updated line
        return false;
    }
    return true;
}

bool Cerabellum::isActive()
{
    return bIsActive;
}

string Cerabellum::act(const string& ear, const string& skin, const string& eye)
{
    string axnStr = "";
    if (!bIsActive) {
        return axnStr;
    }
    if (at < fin) {
        shared_ptr<Mutable> mut = alg->getAlgParts().at(at);
        axnStr = mut->action(ear, skin, eye);
        emot = alg->getAlgParts().at(at)->myName();
        if (alg->getAlgParts().at(at)->completed()) {
            incrementAt = true;
        }
    }
    return axnStr;
}

void Cerabellum::deActivation()
{
    bIsActive = bIsActive && !alg->getAlgParts().at(at)->algKillSwitch;
}

//Fusion

Fusion::Fusion()
{
}

string Fusion::getEmot()
{
    return emot;
}

void Fusion::loadAlgs(Neuron* neuron)
{
    for (int i = 0; i < ceraArr.size(); i++) {
        if (!ceraArr[i].isActive()) {
            shared_ptr<Algorithm> temp = neuron->getAlg(i);
            if (temp != nullptr) {
                ceraArr[i].setAlgorithm(temp);
            }
        }
    }
}

string Fusion::runAlgs(const string& ear, const string& skin, const string& eye)
{
    string result;
    for (int i = 0; i < 5; i++) {
        if (!ceraArr[i].isActive()) {
            continue;
        }
        result = ceraArr[i].act(ear, skin, eye);
        ceraArr[i].advanceInAlg();
        emot = ceraArr[i].getEmot();
        ceraArr[i].deActivation(); // deactivation if Mutatable.algkillswitch = true
        return result;
    }
    emot.clear();
    return result;
}

//Thinkable

//Chobits

Chobits::Chobits() : Thinkable(), fusion(make_unique<Fusion>()), neuron(make_unique<Neuron>())
{
    kokoro = make_shared<Kokoro>(make_shared<AbsDictionaryDB>());
}

Chobits::~Chobits()
{
    clearSkills();
}

void Chobits::setDataBase(shared_ptr<AbsDictionaryDB> absDictionaryDB)
{
    kokoro = make_shared<Kokoro>(absDictionaryDB);
}

Chobits* Chobits::addSkill(DiSkillV2* skill)
{
    // add a skill (builder design patterned func))
    skill->setKokoro(kokoro.get());
    dClasses.push_back(skill);
    return this;
}

void Chobits::clearSkills()
{
    for_each(dClasses.begin(), dClasses.end(), [](DiSkillV2* lpSkill) { delete lpSkill; });
    dClasses.clear();
}

void Chobits::addSkills(initializer_list<DiSkillV2*> skills)
{
    for (auto skill : skills) {
        skill->setKokoro(kokoro.get());
        dClasses.push_back(skill);
    }
}

void Chobits::removeSkill(DiSkillV2* skill)
{
    if (skill != nullptr) {
        auto it = find(dClasses.begin(), dClasses.end(), skill);
        if (it != dClasses.end())
            delete* it;
        dClasses.erase(it);
    }
}

bool Chobits::containsSkill(DiSkillV2* skill)
{
    return (skill != nullptr && find(dClasses.begin(), dClasses.end(), skill) != dClasses.end());
}

string Chobits::think(const string& ear, const string& skin, const string& eye)
{
    for (DiSkillV2* dCls : dClasses) {
        inOut(dCls, ear, skin, eye);
    }
    fusion->loadAlgs(neuron.get());
    return fusion->runAlgs(ear, skin, eye);
}

string Chobits::getSoulEmotion()
{
    return fusion->getEmot();
}

shared_ptr<Kokoro> Chobits::getKokoro()
{
    return kokoro;
}

void Chobits::setKokoro(shared_ptr<Kokoro> lpKokoro)
{
    kokoro = lpKokoro;
}

Fusion* Chobits::getFusion()
{
    return fusion.get();
}

vector<string> Chobits::getSkillList(vector<string>& result)
{
    for (DiSkillV2* skill : dClasses) {
        result.push_back(skill->myName());
    }
    return result;
}

void Chobits::inOut(DiSkillV2* dClass, const string& ear, const string& skin, const string& eye)
{
    dClass->input(ear, skin, eye); // new
    dClass->output(neuron.get());
}

//Brain

Brain::Brain()
{
    logicChobit = make_unique<Chobits>();
    hardwareChobit = make_unique<Chobits>();
    hardwareChobit->setKokoro(logicChobit->getKokoro());
}

Chobits* Brain::getLogicChobit()
{
    return logicChobit.get();
}

Chobits* Brain::getHardwareChobit()
{
    return hardwareChobit.get();
}

string Brain::getEmotion()
{
    return emotion;
}

string Brain::getBodyInfo()
{
    return bodyInfo;
}

string Brain::getLogicChobitOutput()
{
    return logicChobitOutput;
}

void Brain::doIt(const string& ear, const string& skin, const string& eye)
{
    if (!bodyInfo.empty()) {
        logicChobitOutput = logicChobit->think(ear, bodyInfo, eye);
    }
    else {
        logicChobitOutput = logicChobit->think(ear, skin, eye);
    }
    emotion = logicChobit->getSoulEmotion();
    // case: hardware skill wishes to pass info to logical chobit
    bodyInfo = hardwareChobit->think(logicChobitOutput, skin, eye);
}

void Brain::addLogicalSkill(DiSkillV2* skill)
{
    logicChobit->addSkill(skill);
}

void Brain::addHardwareSkill(DiSkillV2* skill)
{
    hardwareChobit->addSkill(skill);
}

//**Main**//

int main()
{
    Brain b1;
    b1.addLogicalSkill(new DiHelloWorld());
    b1.addLogicalSkill(new DiHelloWorld());
    b1.addHardwareSkill(new DiSysOut());
    b1.doIt("hello", "", "");
    b1.getLogicChobit()->clearSkills();
    b1.addLogicalSkill(new DiHelloWorld());
    b1.doIt("", "", "");
    b1.doIt("hello", "", "");
}
