/*
    NOTE
    THIS CODE WAS INSPIRED BY; RandomNerdTutorials
    https://randomnerdtutorials.com/esp32-web-server-arduino-ide/
*/

#include <iostream>
#include <HTTP_Method.h>
#include <WebServer.h>
#include "Webserver.h"
#include <WiFi.h>
#include <ESPmDNS.h>

String sendHTML();
void handle_OnConnect();
void handle_NotFound();

//VARIABLES
const char* ssid = "";
const char* password = "";
WebServer server(12345);
WiFiClient client;
String header;

//INITIALIZE THE WEBSERVER
void webserverInit() {

  //Connect to Wi-Fi
  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED){
    Serial.println(".");
    delay(1000);
  }

    //mDNS - might not work as it is suppose to because of port other than 80.
  if(!MDNS.begin("smartcar")){
    Serial.println("Error! mDNS has not been set up.");
  }else{
    Serial.println("mDNS has been setup");
  }

  server.on("/", handle_OnConnect);
  server.onNotFound(handle_NotFound);

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
}

void handle_NotFound() {
  server.send(404, "text/plain", "NOT FOUND");
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
