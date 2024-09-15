#pragma once
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