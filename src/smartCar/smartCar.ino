#include <Smartcar.h>
#include <BluetoothSerial.h>

//Include the webserver file
#include "Webserver.h"

//BluetoothSerial bluetooth;

boolean nu = true;

const int FRONT_TRIGGER_PIN = 4; 
const int FRONT_ECHO_PIN = 2; 
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

SmartCar car(control, gyroscope, leftOdometer, rightOdometer);

void setup()
{
    //bluetooth.begin("SMARTCAR");
    Serial.begin(115200);
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

    //Loop the webserver creation
    //Which is the same code that would have been in the loop if webserver was a .ino file.
    webserverCreation();
}





void manualControlling() { //handle serial input if there is any
  if (client.available()) {
    char input;
    float speedPMPS;
    input = client.read(); //read till last character
    switch (input) {
      
       case 'w': //increase speed
        speedPMPS = car.getSpeed() + 0.3;
        car.setSpeed(speedPMPS);
        break;

              
      case 'b'://decrease Speed 
        speedPMPS = car.getSpeed() - 0.3;
        car.setSpeed(speedPMPS);
        break;
        
      case 'l': 
        car.setAngle(-50);
        break;
        
      case 'r': //turn clock-wise
        car.setAngle(50);  //75 to the right
        break;

      case 's': //stop
        speedPMPS = 0;
        car.setSpeed(speedPMPS);
        car.setAngle(0);
        nu = true;
        break;

      case 'a': //automated mode
        speedPMPS = 1;
        car.setSpeed(speedPMPS);
        car.setAngle(0);
        nu = false;
        break;
         
      default: //if you receive something that you don't know, just stop
        car.setAngle(0);
        break;
    }
  }
}

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

void checkObstacle(){
    int distance = front_sensor.getDistance();
    
    if(distance > 0 && distance < 25){
      car.setSpeed(0);
  }
}
