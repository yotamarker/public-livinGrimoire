#include <Arduino.h>
#include "DiHelloWorld.h"
#include "LivinGrimoireLight.h"

Led::Led(byte pin)
{
 this->pin = pin;
}
void Led::init()
{
 pinMode(pin, OUTPUT);
}
void Led::init(byte defaultState)
{
 init();
  if (defaultState == HIGH) {
 on();
 }
  else {
 off();
 }
}
void Led::on()
{
 digitalWrite(pin, HIGH);
}
void Led::off()
{
 digitalWrite(pin, LOW);
}
DiHelloWorld::DiHelloWorld(Led l1)
{
  _l1 = l1;_l1.init();
}
void DiHelloWorld::inOut(byte ear, byte skin, byte eye)
{ 
      // turn the LED on (HIGH is the voltage level)
 _l1.on();
 delay(1000); // Wait for 1000 millisecond(s)
 // turn the LED off by making the voltage LOW
 _l1.off();
 delay(1000); // Wait for 1000 millisecond(s)
}