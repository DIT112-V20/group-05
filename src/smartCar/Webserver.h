#ifndef WEBSERVER_H
#define WEBSERVER_H


#include <iostream>
#include <HTTP_Method.h>
#include <WebServer.h>
#include <WiFi.h>
#include <ESPmDNS.h>

class Webserver {

  private:

//VARIABLES
const char* ssid;
const char* password;
WebServer server();
WiFiClient client;
String header;

  public:

  Webserver() {
    
  };

//INITIALIZE THE WEBSERVER
void webserverInit() {}

//CREATE AND UPDATE THE WEBSERVER
void webserverCreation() {}

void handle_OnConnect() {}

void handle_NotFound() {}

String sendHTML() {}
  
};

/*

  FUNCTIONS

*/

void webserverInit();
void webserverCreation();

#endif
