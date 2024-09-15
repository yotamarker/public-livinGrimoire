// LivinGrimoire.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include "livingrimoire.h"

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
