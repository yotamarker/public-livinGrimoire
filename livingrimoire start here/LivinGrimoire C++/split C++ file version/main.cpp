// LivinGrimoire.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include "pch.h"
#include "Brain.h"
#include "DiHelloWorld.h"
#include "DiSysOut.h"

int main()
{
    Brain b1;
    b1.addLogicalSkill(new DiHelloWorld());
    b1.addHardwareSkill(new DiSysOut());
    b1.doIt( "hello", "", "" );
}
