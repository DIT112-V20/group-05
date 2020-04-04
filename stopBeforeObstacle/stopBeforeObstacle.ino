#include <Smartcar.h>

const int FRONT_TRIGGER_PIN = 18; 
const int FRONT_ECHO_PIN = 5; 
const unsigned int FRONT_MAX_DISTANCE = 100;
SR04 front_sensor(FRONT_TRIGGER_PIN, FRONT_ECHO_PIN, FRONT_MAX_DISTANCE);

BrushedMotor leftMotor(smartcarlib::pins::v2::leftMotorPins);
BrushedMotor rightMotor(smartcarlib::pins::v2::rightMotorPins);
DifferentialControl control(leftMotor, rightMotor);

const auto pulsesPerMeter = 600;

DirectionlessOdometer leftOdometer(
    smartcarlib::pins::v2::leftOdometerPin, []() { leftOdometer.update(); }, pulsesPerMeter);
DirectionlessOdometer rightOdometer(
    smartcarlib::pins::v2::rightOdometerPin, []() { rightOdometer.update(); }, pulsesPerMeter);

DistanceCar car(control, leftOdometer, rightOdometer);

void setup()
{
    Serial.begin(9600);
    car.enableCruiseControl();
}

void loop()
{
    car.update();
    checkObstacle();
}

void checkObstacle(){

    //We set the speed to 1 m/s when the distance to the obstacle is < 25cm
    //OR the distance is 0, we do this because of the sensor "bug"
    //Where the distance is < 1m it outputs 0.
    //Else we stop the car.
    
    int distance = front_sensor.getDistance();
    
    if(distance > 25 || distance == 0){
    
    car.setSpeed(1);

  }else{

    car.setSpeed(0);

  }
}
