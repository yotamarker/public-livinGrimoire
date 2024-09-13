// LivinGrimoire.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include "pch.h"
#include "Brain.h"
#include "DiHelloWorld.h"
#include "DiSysOut.h"

int main()
{
    Brain b1;
    unique_ptr< DiHelloWorld> dihello = make_unique< DiHelloWorld>();
    unique_ptr< DiSysOut> disys = make_unique< DiSysOut>();

    b1.addLogicalSkill(dihello.get());
    b1.addHardwareSkill(disys.get());
    b1.doIt( "hello", "", "" );
    //std::cout << "Hello World!\n";
}
