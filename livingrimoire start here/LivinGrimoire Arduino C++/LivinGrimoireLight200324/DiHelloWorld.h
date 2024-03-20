#ifndef DiHelloWorld_H
#define DiHelloWorld_H
// using Arduino hardware codes outside main:
#include <Arduino.h>
#include "LivinGrimoireLight.h"
class Led
{
private:
 byte pin;
public:
 Led() {} // do not use
 Led(byte pin);
 // init the pin for the LED
 // call this in setup()
 void init();
 void init(byte defaultState);
 // power on the LED
 void on();
 // power off the LED
 void off();
};
// example hello world by blinking default Led #13 once
class DiHelloWorld : public Skill {
    private:
    Led _l1;
    public:
    DiHelloWorld(Led l1);
    virtual void inOut(byte ear, byte skin, byte eye);
};
#endif