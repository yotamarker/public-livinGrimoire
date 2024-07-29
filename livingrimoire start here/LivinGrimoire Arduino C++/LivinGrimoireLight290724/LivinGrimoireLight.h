#ifndef LivinGrimoireLight_H
#define LivinGrimoireLight_H
#include <Arduino.h>
// Skill super class
class Skill {
    public:
    Skill(){}
    virtual void inOut(byte ear, byte skin, byte eye) ;
};
// list of Skill objects
// only the Chobit cls uses it
class ListOfSkills {
public:
  byte length = 0;
  Skill* data[16];
  void append(Skill* item) ;
  void remove(byte index) ;
};
// Chobit
class Chobit
{
private:
  ListOfSkills* dSkills = new ListOfSkills();
public:
  ~Chobit(); // Destructor declaration
  void addSkill(Skill* s1);
  void clearSkills();
  // run all added skills
  void think(byte ear, byte skin, byte eye);
};
#endif