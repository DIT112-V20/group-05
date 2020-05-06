#include <Smartcar.h>
#include <BluetoothSerial.h>

//Include the webserver file
#include "Webserver.h"

//MANUAL (true) OR AUTOMATIC (false) MODE
boolean nu = true;

//PINS
const int FRONT_TRIGGER_PIN = 4; 
const int FRONT_ECHO_PIN = 2; 

//SMARTCAR VARIABLES
const unsigned int FRONT_MAX_DISTANCE = 100;
SR04 front_sensor(FRONT_TRIGGER_PIN, FRONT_ECHO_PIN, FRONT_MAX_DISTANCE);
BrushedMotor leftMotor(smartcarlib::pins::v2::leftMotorPins);
BrushedMotor rightMotor(smartcarlib::pins::v2::rightMotorPins);
DifferentialControl control(leftMotor, rightMotor);
GY50 gyroscope(37);
const auto pulsesPerMeter = 600;
DirectionlessOdometer leftOdometer(
    smartcarlib::pins::v2::leftOdometerPin, []() { leftOdometer.update(); }, pulsesPerMeter);
DirectionlessOdometer rightOdometer(
    smartcarlib::pins::v2::rightOdometerPin, []() { rightOdometer.update(); }, pulsesPerMeter);

//INITIALIZE THE SMARTCAR
SmartCar car(control, gyroscope, leftOdometer, rightOdometer);

void setup()
{
    //Set the baudrate
    Serial.begin(115200);

    //The speed of the car is in m/s.
    car.enableCruiseControl();

    //Setup the webserver
    webserverInit();
}

void loop()
{
    car.update();
    manualControlling();
    checkObstacle();
    automatedControl();

    //Update the webserver
    webserverCreation();
}

//Manual controller (WIP - Work in Progress)
void manualControlling() {
  if (client.available()) {
    
    char input;
    float speedPMPS;
    input = client.read();
    
    switch (input) {

      //Increase the speed
       case 'w':
        speedPMPS = car.getSpeed() + 0.3;
        car.setSpeed(speedPMPS);
        break;

      //Decrease the speed
      case 'b':
        speedPMPS = car.getSpeed() - 0.3;
        car.setSpeed(speedPMPS);
        break;

      //Turn left
      case 'l': 
        car.setAngle(-50);
        break;

      //Turn right
      case 'r':
        car.setAngle(50);
        break;

      //Stop the car
      case 's':
        speedPMPS = 0;
        car.setSpeed(speedPMPS);
        car.setAngle(0);
        nu = true;
        break;

      //Automate the car
      case 'a':
        speedPMPS = 1;
        car.setSpeed(speedPMPS);
        car.setAngle(0);
        nu = false;
        break;

      //Reset angle
      default:
        car.setAngle(0);
        break;
    }
  }
}

//Automated controls (WIP - Work in Progress)
void automatedControl(){
  
  if(nu == false){
    car.setSpeed(1);
        car.setAngle(0);
    while(front_sensor.getDistance() > 0 && front_sensor.getDistance() < 35){
           int degrees = 90;
          float speed = 1;
          speed = smartcarlib::utils::getAbsolute(speed);
    degrees %= 360; 
    if (degrees == 0)
    {
        return;
    }

    car.setSpeed(speed);
    if (degrees > 0)
    {
        car.setAngle(90);
    }
    else
    {
        car.setAngle(-90);
    }

    const auto initialHeading    = car.getHeading();
    bool hasReachedTargetDegrees = false;
    while (!hasReachedTargetDegrees)
    {
        car.update();
        auto currentHeading = car.getHeading();
        if (degrees < 0 && currentHeading > initialHeading)
        {
            
            currentHeading -= 360;
        }
        else if (degrees > 0 && currentHeading < initialHeading)
        {
            
            currentHeading += 360;
        }
        
        int degreesTurnedSoFar  = initialHeading - currentHeading;
        hasReachedTargetDegrees = smartcarlib::utils::getAbsolute(degreesTurnedSoFar)
                                  >= smartcarlib::utils::getAbsolute(degrees);
    }

    car.setSpeed(0);
    }
  }
}

//Check for an obstacle
void checkObstacle(){
    int distance = front_sensor.getDistance();
    
    if(distance > 0 && distance < 25){
      car.setSpeed(0);
  }
}
