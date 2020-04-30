#include <WiFi.h>

/*
*
* VARIABLES
*
*/

const char* ssid;
const char* password;
WiFiServer server;
String header;

/*
*
* FUNCTIONS
*
*/

void webserverInit();
void webserverCreation();