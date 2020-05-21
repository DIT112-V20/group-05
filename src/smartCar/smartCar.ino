#include <Smartcar.h>
#include <BluetoothSerial.h>
#include <iostream>
#include <HTTP_Method.h>
#include <WebServer.h>
#include <WiFi.h>
#include <ESPmDNS.h>

//VARIABLES FOR WEBSERVER
const char* ssid = "Yake";
const char* password = "403ccad8";
WebServer server(12345);
WiFiClient client;
String header;

//PINS
const int FRONT_TRIGGER_PIN = 4; 
const int FRONT_ECHO_PIN = 2; 

//Variables for statistics
int originalDistance = 200;
int distanceToDrive = originalDistance;
int correctHeadingDriven = 0;
int incorrectHeadingDriven = 0;
int correctHeading = 0;

//SMARTCAR VARIABLES
const unsigned int FRONT_MAX_DISTANCE = 100;
SR04 front_sensor(FRONT_TRIGGER_PIN, FRONT_ECHO_PIN, FRONT_MAX_DISTANCE);
BrushedMotor leftMotor(smartcarlib::pins::v2::leftMotorPins);
BrushedMotor rightMotor(smartcarlib::pins::v2::rightMotorPins);
DifferentialControl control(leftMotor, rightMotor);
GY50 gyroscope(2);
const auto pulsesPerMeter = 600;
DirectionlessOdometer leftOdometer(
    smartcarlib::pins::v2::leftOdometerPin, []() { leftOdometer.update(); }, pulsesPerMeter);
DirectionlessOdometer rightOdometer(
    smartcarlib::pins::v2::rightOdometerPin, []() { rightOdometer.update(); }, pulsesPerMeter);

//CONTROLLER VARIABLES
double LOW_SPEED = 0.50;
double MED_SPEED = 1;
double HIGH_SPEED = 1.5;
double CURRENT_SPEED = LOW_SPEED;
boolean controllerMode = true; //MANUAL = TRUE; AUTOMATIC = FALSE

//INITIALIZE THE SMARTCAR
SmartCar car(control, gyroscope, leftOdometer, rightOdometer);

void setup()
{
    //Set the baudrate
    Serial.begin(115200);

    //The speed of the car is in m/s.
    car.enableCruiseControl();

    correctHeading = gyroscope.getHeading();

    //Setup the webserver
    webserverInit();
}

void loop()
{
    car.update();
    
    if(controllerMode == false){
      automatedControl();
    }

    pinMode(LED_BUILTIN, OUTPUT);
    
    //Update the webserver
    webserverCreation();

    checkDestination();
}

//Automated controls (WIP - Work in Progress)
void automatedControl(){
  
 //Get distance of the obstacle
  int distance = front_sensor.getDistance();
  Serial.println(distance);
  //Turn on the LED at when the obstacle is within a pre-determined distance
  if(distance <= 60 && distance != 0){
    digitalWrite(LED_BUILTIN, HIGH);
  }
  //Functionality to gradually increase speed
  //as well as go back if the object comes closer
  if(distance <= 5 && distance != 0){
    CURRENT_SPEED = -0.75;
  }else if(distance <= 10 && distance != 0){
    CURRENT_SPEED = -0.5;
  }else if(distance <= 15 && distance != 0){
    CURRENT_SPEED = -0.25;
  }else if(distance <= 20 && distance != 0){
    CURRENT_SPEED = 0;
  }else if(distance <= 60 && distance != 0){
    CURRENT_SPEED = LOW_SPEED;
  }else if(distance <= 100 && distance != 0){
    CURRENT_SPEED = MED_SPEED;
  }else if(distance == 0){
    CURRENT_SPEED = HIGH_SPEED;
    digitalWrite(LED_BUILTIN, LOW);
  }
  //Set the speed so the car moves forward
  car.setSpeed(CURRENT_SPEED);
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
  server.on("/disconnect", handle_OnDisconnect);
  server.on("/forward", forwardEndpoint);
  server.on("/backward", backwardEndpoint);
  server.on("/turnLeft", turnLeftEndpoint);
  server.on("/turnRight", turnRightEndpoint);
  server.on("/submit", distanceSubmitted);
  server.on("/autoOff", turnOffAutomation);
  server.on("/AutoOn", turnOnAutomation);
  server.on("/setGear", setGear);
  server.on("/sensor", sensorEndpoint);
  server.on("/stop", stopCar);
  server.on("/resetAngle", resetAngle);
  server.on("/increase", increaseSpeed);
  server.on("/decrease", decreaseSpeed);
  server.on("/destinationReached", destinationReached);
  server.on("/sendInfo", sendInfo);

  //Print local IP address to the serial monitor and start the web server
  Serial.println("");
  Serial.println("WiFi connected.");
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());
  server.begin();
  Serial.println("Server started!");

  //Add the service to the MDNS-SD
  MDNS.addService("http", "tcp", 12345);
}

//CREATE AND UPDATE THE WEBSERVER
void webserverCreation() {
  server.handleClient();
}

void handle_OnConnect() {
  server.send(200, "text/html", sendHTML('\0'));
  Serial.println("Client has connected");
}

void handle_NotFound() {
  server.send(404, "text/plain", "NOT FOUND");
}

void handle_OnDisconnect(){
  server.send(200, "text/plain", "DISCONNECTED");
  Serial.println("Client has disconnected.");
}

void forwardEndpoint(){
  car.setSpeed(CURRENT_SPEED);
  server.send(200, "text/html", sendHTML('f'));
  Serial.println("for");
}

void backwardEndpoint(){
  car.setSpeed(CURRENT_SPEED * -1);
  server.send(200, "text/html", sendHTML('b'));
}

void turnLeftEndpoint(){
  car.setAngle(-50);
  server.send(200, "text/html", sendHTML('l'));
}

void turnRightEndpoint(){
  car.setAngle(50);
  server.send(200, "text/html", sendHTML('r'));
}

void resetAngle(){
  car.setAngle(0);
}

void distanceSubmitted(){
  distanceToDrive = server.arg(0).toInt();
}

void sendInfo(){
  int speedNow = car.getSpeed();
  int distanceNow = distanceToDrive;
  server.send(200, "text/plain", speedNow+"/"+distanceNow);
}

void stopCar(){
  CURRENT_SPEED = LOW_SPEED;
  car.setSpeed(0);
  car.setAngle(0);
  Serial.println("STOP");
}

void increaseSpeed(){
  CURRENT_SPEED += 0.1;
  car.setSpeed(CURRENT_SPEED);
}

void decreaseSpeed(){
  CURRENT_SPEED -= 0.1;
  car.setSpeed(CURRENT_SPEED);
}

void setGear(){
  int gear = server.arg(0).toInt();
  Serial.println(gear);

  if (gear == 1){
    CURRENT_SPEED = LOW_SPEED;
  }else if(gear == 2){
    CURRENT_SPEED = MED_SPEED;
  }else if(gear == 3){
    CURRENT_SPEED = HIGH_SPEED;
  }

  server.send(200, "text/html", sendHTML('g'));
}

void turnOnAutomation(){
  controllerMode = false;
}

void turnOffAutomation(){
  controllerMode = true;
}

void destinationReached(){
  String result;
  
  server.send(200, "text/plain", result);
}

void sensorEndpoint(){
  server.send(200, "text/plain", String(front_sensor.getDistance()));
}

String sendHTML(char message) {
  String html = "<!DOCTYPE html><html>\n";
  html += "<head><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n";
  html += "<link rel=\"icon\" href=\"data:,\">\n";
  html += "<style>html { font-family: Helvetica; display: inline-block; margin: 0px auto; text-align: center;}\n";
  html += ".button { background-color: #4CAF50; border: none; color: white; padding: 16px 40px;\n";
  html += "text-decoration: none; font-size: 30px; margin: 2px; cursor: pointer;}\n";
  html += ".button2 {background-color: #555555;}</style></head>\n";
  html += "<body><h1>ESP32 Web Server</h1>\n";

  switch(message){
    case 'f':
      html += "<h2>Going forward!</h2>\n";
      break;
    case 'b':
      html += "<h2>Going backward!</h2>\n";
      break;
    case 'r':
      html += "<h2>Turning right!</h2>\n";
      break;
    case 'l':
      html += "<h2>Turning left!</h2>\n";
      break;
    case 'g':
      html += "<h2>Gear changed!</h2>\n";
      break;
  }
  
  
  html += "</body></html>\n";

  return html;
}

void checkDestination(){
  
  if(correctHeadingDriven == distanceToDrive){
    CURRENT_SPEED = 0;
    car.setSpeed(CURRENT_SPEED);
  }
  
  int currentHeading = gyroscope.getHeading();

  if(currentHeading > (correctHeading-10) && currentHeading < (correctHeading+10)){
    correctHeadingDriven = car.getDistance() - incorrectHeadingDriven;
  }else{
    incorrectHeadingDriven = car.getDistance() - correctHeadingDriven;
    distanceToDrive = originalDistance + incorrectHeadingDriven;
  }
}
