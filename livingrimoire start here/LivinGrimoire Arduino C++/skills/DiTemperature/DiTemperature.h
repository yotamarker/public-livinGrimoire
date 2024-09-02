#ifndef DiTemperature_H
#define DiTemperature_H
// using Arduino hardware codes outside main:
#include <Arduino.h>
#include "LivinGrimoireLight.h"

/*
lm35 sketch
prints the temperature to the serial monitor
*/

class DiTemperature : public Skill {
    private:
        int _inPin; // analog pin
    public:
    DiTemperature(int inPin);
    virtual void inOut(byte ear, byte skin, byte eye);
};
#endif