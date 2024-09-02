#include <Arduino.h>
#include "DiTemperature.h"
#include "LivinGrimoireLight.h"

DiTemperature::DiTemperature(int inPin) 
{
  _inPin = inPin;
  Serial.begin(9600); // Start serial communication at 9600 baud
}

void DiTemperature::inOut(byte ear, byte skin, byte eye) {
  int value = analogRead(_inPin);
  float millivolts = (value / 1024.0) * 5000;
  float celsius = millivolts / 10;
  Serial.print(celsius);
  delay(1000);
  // Serial.print(" \xC2\xB0"); // shows degree symbol
  // Serial.println("C");
}