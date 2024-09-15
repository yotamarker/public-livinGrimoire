#include <iostream>
#include "livingrimoire.h"

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