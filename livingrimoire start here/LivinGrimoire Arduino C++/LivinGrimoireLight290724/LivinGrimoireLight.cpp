#include <Arduino.h>
#include "LivinGrimoireLight.h"
// Skill class
void Skill::inOut(byte ear, byte skin, byte eye) 
{
  // override me
}
// ListOfSkills class
void ListOfSkills::append(Skill* item) 
{
  if (length < 16) data[length++] = item;
}
void ListOfSkills::remove(byte index) {
    if (index >= length) return;
    memmove(&data[index], &data[index + 1], (length - index - 1) * sizeof(Skill*));
    length--;
}

// Chobit class
Chobit::~Chobit() {
    delete dSkills;
}

// Chobit class
void Chobit::addSkill(Skill* s1)
{
    dSkills->append(s1);
}
void Chobit::clearSkills()
  {
    delete dSkills;
    dSkills = new ListOfSkills();
  }
void Chobit::think(byte ear, byte skin, byte eye)
  {
    for(int i=0;i<dSkills->length;i++)
    {
      dSkills->data[i]->inOut(ear, skin, eye);
    }
  }
