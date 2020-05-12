#include <Smartcar.h>
#include <BluetoothSerial.h>
#include <iostream>
#include <HTTP_Method.h>
#include <WebServer.h>
#include <WiFi.h>
#include <ESPmDNS.h>

//Tell c++ there will be these functions later.
String sendHTML();
void handle_OnConnect();
void handle_NotFound();
void forwardEndpoint();
void backwardEndpoint();
void turnLeftEndpoint();
void turnRightEndpoint();
void turnOnAutomation();
void turnOffAutomation();
void setCarSpeed();
void sensorEndpoint();

//VARIABLES FOR WEBSERVER
const char* ssid = "";
const char* password = "";
WebServer server(12345);
WiFiClient client;
String header;

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

//CONTROLLER VARIABLES
double CURRENT_SPEED;
double LOW_SPEED = 0.25;
double MED_SPEED = 0.5;
double HIGH_SPEED = 0.75;
boolean controllerMode = true; //MANUAL = TRUE; AUTOMATIC = FALSE

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
    checkObstacle();
    
    if(controllerMode == false){
      automatedControl();
    }

    //Update the webserver
    webserverCreation();
}

//Automated controls (WIP - Work in Progress)
void automatedControl(){
  
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

//Check for an obstacle
void checkObstacle(){
    int distance = front_sensor.getDistance();
    
    if(distance > 0 && distance < 25){
      car.setSpeed(0);
  }
}

//INITIALIZE THE WEBSERVER
void webserverInit() {

  //Connect to Wi-Fi
  WiFi.begin(ssid, password);

  //Print . until WIFI is connected.
  while (WiFi.status() != WL_CONNECTED){
    Serial.println(".");
    delay(1000);
  }

  //mDNS - connect with https://smartcar.local:12345
  if(!MDNS.begin("smartcar")){
    Serial.println("Error! mDNS has not been set up.");
  }else{
    Serial.println("mDNS has been setup");
  }

  //HTTP requests handling
  server.on("/", handle_OnConnect);
  server.onNotFound(handle_NotFound);
  server.on("/forward", forwardEndpoint);
  server.on("/backward", backwardEndpoint);
  server.on("/turnLeft", turnLeftEndpoint);
  server.on("/turnRight", turnRightEndpoint);
  server.on("/autoOff", turnOffAutomation);
  server.on("/AutoOn", turnOnAutomation);
  server.on("/sensor", sensorEndpoint);

  //Print local IP address to the serial monitor and start the web server
  Serial.println("");
  Serial.println("WiFi connected.");
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());
  server.begin();
  Serial.print("Server started!");

  //Add the service to the MDNS-SD
  MDNS.addService("http", "tcp", 12345);
}

//CREATE AND UPDATE THE WEBSERVER
void webserverCreation() {
  server.handleClient();
}

void handle_OnConnect() {
  server.send(200, "text/html", sendHTML());
  Serial.println("Client connected");
}

void handle_NotFound() {
  server.send(404, "text/plain", "NOT FOUND");
}

void forwardEndpoint(){
  car.setSpeed(CURRENT_SPEED);
}

void backwardEndpoint(){
  car.setSpeed(CURRENT_SPEED * -1);
}

void turnLeftEndpoint(){
  car.setAngle(-50);
}

void turnRightEndpoint(){
  car.setAngle(50);
}

void setCarSpeed(){
  int gear;

  if (gear = 1){
    CURRENT_SPEED = LOW_SPEED;
  }else if(gear = 2){
    CURRENT_SPEED = MED_SPEED;
  }else if(gear = 3){
    CURRENT_SPEED = HIGH_SPEED;
  }
}

void turnOnAutomation(){
  controllerMode = false;
}

void turnOffAutomation(){
  controllerMode = true;
}

void sensorEndpoint(){
  server.send(200, "text/plain", String(front_sensor.getDistance()));
}

String sendHTML() {
  String html = "<!DOCTYPE html><html>\n";
  html += "<head><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n";
  html += "<link rel=\"icon\" href=\"data:,\">\n";
  html += "<style>html { font-family: Helvetica; display: inline-block; margin: 0px auto; text-align: center;}\n";
  html += ".button { background-color: #4CAF50; border: none; color: white; padding: 16px 40px;\n";
  html += "text-decoration: none; font-size: 30px; margin: 2px; cursor: pointer;}\n";
  html += ".button2 {background-color: #555555;}</style></head>\n";
  html += "<body><h1>ESP32 Web Server</h1>\n";
  html += "</body></html>\n";

  return html;
}
