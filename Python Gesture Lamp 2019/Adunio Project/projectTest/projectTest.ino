/*
  WS2812 RGB LED Test
  ws2812-rgb-test.ino
  Uses Adafruit NeoPixel Library
  Functions from Adafruit NeopPixel Strandtest Examples
  DroneBot Workshop 2018
  https://dronebotworkshop.com
*/

// Include Adafruit NeoPixel Library
#include <Adafruit_NeoPixel.h>
#include <Wire.h>
#include "paj7620.h"

//check censor incase of loose wiring

// Define Arduino connection pin
#define LED_PIN 8 
// Define the number of LEDs - change if necessary
#define LED_COUNT 22
#define GES_REACTION_TIME    10       // You can adjust the reaction time according to the actual circumstance.
#define GES_ENTRY_TIME      800       // When you want to recognize the Forward/Backward gestures, your gestures' reaction time must less than GES_ENTRY_TIME(0.8s). 
#define GES_QUIT_TIME     1000

// Create instance of NeoPixel class
// Parameter 1 = number of pixels in leds
// Parameter 2 = Arduino pin number (most are valid)
// Parameter 3 = pixel type flags, add together as needed:
//   NEO_KHZ800  800 KHz bitstream (most NeoPixel products w/WS2812 LEDs)
//   NEO_KHZ400  400 KHz (classic 'v1' (not v2) FLORA pixels, WS2811 drivers)
//   NEO_GRB     Pixels are wired for GRB bitstream (most NeoPixel products)
//   NEO_RGB     Pixels are wired for RGB bitstream (v1 FLORA pixels, not v2)
//   NEO_RGBW    Pixels are wired for RGBW bitstream (NeoPixel RGBW products)
Adafruit_NeoPixel leds = Adafruit_NeoPixel(LED_COUNT, LED_PIN, NEO_GRB + NEO_KHZ800);

void setup()
{
  uint8_t error = 0;
  // Setup Serial Monitor
  Serial.begin(9600);

  Serial.println("\nPAJ7620U2 TEST DEMO: Recognize 7 gestures.");

  error = paj7620Init();      // initialize Paj7620 registers
  if (error) 
  {
    Serial.print("INIT ERROR,CODE:");
    Serial.println(error);
  }
  else
  {
    Serial.println("INIT OK");
  }
  Serial.println("Please input your gestures:\n");  

  // Initialize the LEDs
  leds.begin(); 
  // Clear them all
  clearLEDs();
  // Show the result of clearing the LEDs
  leds.show();
  
}

void loop() {
  uint8_t data = 0, data1 = 0, error;
  
  error = paj7620ReadReg(0x43, 1, &data);       // Read Bank_0_Reg_0x43/0x44 for gesture result.
  if (!error) 
  {
    switch (data)                   // When different gestures be detected, the variable 'data' will be set to different values by paj7620ReadReg(0x43, 1, &data).
    {
      case GES_RIGHT_FLAG:
        delay(GES_ENTRY_TIME);
        paj7620ReadReg(0x43, 1, &data);
        Serial.println("Right");

        // Cycle through Color Wipe Examples
        // Set colors to Red
        Serial.println("Color Wipe - RED");  
        colorWipe(leds.Color(255, 0, 0), 100);
        delay(200);
        break;

      case GES_LEFT_FLAG: 
        delay(GES_ENTRY_TIME);
        paj7620ReadReg(0x43, 1, &data);
        Serial.println("Left");

        // Set colors to Yellow 
        Serial.println("Color Wipe - YELLOW"); 
        colorWipe(leds.Color(255, 255, 0), 100);
        delay(200);      
        break;  
  
      case GES_UP_FLAG:
        delay(GES_ENTRY_TIME);
        paj7620ReadReg(0x43, 1, &data);
        Serial.println("Up");

        // Set colors to Green
        Serial.println("Color Wipe - GREEN"); 
        colorWipe(leds.Color(0, 255, 0), 100);
        delay(200);
        break;

      case GES_DOWN_FLAG:
        Serial.println("Down");

        // Set Colors to Blue
        Serial.println("Color Wipe - BLUE"); 
        colorWipe(leds.Color(0, 0, 255), 100);
        delay(200);
        break;
        
      case GES_CLOCKWISE_FLAG:
        Serial.println("Clockwise");
        
        Serial.println("Rainbow Theater Chase"); 
        theaterChaseRainbow(50);
        break;

      case GES_COUNT_CLOCKWISE_FLAG:
        Serial.println("anti-clockwise");

        Serial.println("Rainbow"); 
        rainbow(20);
        break;

      case GES_FORWARD_FLAG:
        Serial.println("Forward");
        
        // changes color to orange
        Serial.println("Theater Chase - ORANGE"); 
        theaterChase(leds.Color(255, 65, 0), 50);
        delay(200);
        break;

      case GES_BACKWARD_FLAG:
        Serial.println("Backward");

        // changes color to purple
        Serial.println("Theater Chase - Purple"); 
        theaterChase(leds.Color(128, 0, 128), 50);
        delay(200);
        break;
     
      default:
        paj7620ReadReg(0x44, 1, &data1);
        if (data1 == GES_WAVE_FLAG) 
        {
          Serial.println("wave");

          // Set Color to black
        Serial.println("Color Wipe - black"); 
        colorWipe(leds.Color(0, 0, 0), 100);
        delay(200);
        break;
        }
        break;
    
    }
  }
  //delay(100);

  //Finished
  Serial.println("Loop Ended, Demo Finished!"); 
  Serial.println(".................................");

}

// Function to set all LEDs off
void clearLEDs()
{
  // Cycle through all LEDs
  for (int i=0; i<LED_COUNT; i++)
  {
    // Set color to zero which is off
    leds.setPixelColor(i, 0);
  }
}

// Fill the dots one after the other with a color
void colorWipe(uint32_t c, uint8_t wait) {
  for(uint16_t i=0; i<leds.numPixels(); i++) {
    leds.setPixelColor(i, c);
    leds.show();
    delay(wait);
  }
}

void rainbow(uint8_t wait) {
  uint16_t i, j;

  for(j=0; j<256; j++) {
    for(i=0; i<leds.numPixels(); i++) {
      leds.setPixelColor(i, Wheel((i+j) & 255));
    }
    leds.show();
    delay(wait);
  }
}

// Slightly different, this makes the rainbow equally distributed throughout
void rainbowCycle(uint8_t wait) {
  uint16_t i, j;

  for(j=0; j<256*5; j++) { // 5 cycles of all colors on wheel
    for(i=0; i< leds.numPixels(); i++) {
      leds.setPixelColor(i, Wheel(((i * 256 / leds.numPixels()) + j) & 255));
    }
    leds.show();
    delay(wait);
  }
}

//Theatre-style crawling lights.
void theaterChase(uint32_t c, uint8_t wait) {
  for (int j=0; j<20; j++) {  //do 20 cycles of chasing
    for (int q=0; q < 3; q++) {
      for (uint16_t i=0; i < leds.numPixels(); i=i+3) {
        leds.setPixelColor(i+q, c);    //turn every third pixel on
      }
      leds.show();

      delay(wait);

      for (uint16_t i=0; i < leds.numPixels(); i=i+3) {
        leds.setPixelColor(i+q, 0);        //turn every third pixel off
      }
    }
  }
}

//Theatre-style crawling lights with rainbow effect
void theaterChaseRainbow(uint8_t wait) {
  for (int j=0; j < 256; j++) {     // cycle all 256 colors in the wheel
    for (int q=0; q < 3; q++) {
      for (uint16_t i=0; i < leds.numPixels(); i=i+3) {
        leds.setPixelColor(i+q, Wheel( (i+j) % 255));    //turn every third pixel on
      }
      leds.show();

      delay(wait);

      for (uint16_t i=0; i < leds.numPixels(); i=i+3) {
        leds.setPixelColor(i+q, 0);        //turn every third pixel off
      }
    }
  }
}

// Input a value 0 to 255 to get a color value.
// The colours are a transition r - g - b - back to r.
uint32_t Wheel(byte WheelPos) {
  WheelPos = 255 - WheelPos;
  if(WheelPos < 85) {
    return leds.Color(255 - WheelPos * 3, 0, WheelPos * 3);
  }
  if(WheelPos < 170) {
    WheelPos -= 85;
    return leds.Color(0, WheelPos * 3, 255 - WheelPos * 3);
  }
  WheelPos -= 170;
  return leds.Color(WheelPos * 3, 255 - WheelPos * 3, 0);
}
