#include <Smartcar.h>
#include <BluetoothSerial.h>

BluetoothSerial bluetooth;

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
    bluetooth.begin("SMARTCAR");
    Serial.begin(9600);
    car.enableCruiseControl();
}

void loop()
{
    car.update();
    handleInput();
    checkObstacle();
    automatedControl();
}





void handleInput() { //handle serial input if there is any
  if (bluetooth.available()) {
    char input;
    while (bluetooth.available()) { input = bluetooth.read(); }; //read till last character
    switch (input) {
      case 'l': //rotate counter-clockwise going forward
        car.setSpeed(1); //80% of the full speed
        car.setAngle(-75); //75 degrees to the left
        break;
      case 'r': //turn clock-wise
        car.setSpeed(1);
        car.setAngle(75);  //75 to the right
        break;
      case 'f': //go ahead
        car.setSpeed(1);
        car.setAngle(0);
        break;
      case 'b': //go back
        car.setSpeed(-1);
        car.setAngle(0);
        break;
      case 's': //stop
        car.setSpeed(0);
        car.setAngle(0);
        nu = true;
        break;
      case 'i': //increase speed

        break;
      case 'd': //decrease speed

        break;
      case 'a': //automated mode
      car.setSpeed(1);
      car.setAngle(0);
      nu = false;
     
        break;
       case 'd'://decrease Speed 
        car.setSpeed(getSpeed() - .1);
        car.setAngle(getAngle();

         break;
         
      default: //if you receive something that you don't know, just stop
        car.setSpeed(0);
        car.setAngle(0);
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