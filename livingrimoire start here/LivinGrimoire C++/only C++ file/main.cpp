#include <iostream>
#include "livingrimoire.cpp"
using namespace std;

int main() 
{
    Brain b1;
    b1.addLogicalSkill(new DiHelloWorld());
    b1.addHardwareSkill(new DiSysOut());
    b1.doIt( "hello", "", "" );
    b1.getLogicChobit()->clearSkills();
    b1.doIt( "", "", "" );
    b1.doIt( "hello", "", "" );
}