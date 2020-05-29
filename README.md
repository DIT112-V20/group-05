# What?

A system that allows the user to select a destination which will prompt an automatic car to travel to that destination. Alternatively, the user can manually steer the car remotely via an application. The system will also evaluate and visualize the data during the trip.

# Why?

To optimize the way the user chooses a certain route to get to a destination.<br/>
To provide the most secure driving experience for the user, automated and precise maneuver around obstacles.<br/>
To provide live data keeping the user updated about the trip.

# How?

We have an android application that the users can use to control a smart car. <br/>
The car acts as a web server that allows the application to communicate/control the car.<br/>
The car uses an HC-SR04 Ultrasonic sensor to detect obstacles. <br/>

Whilst in automatic mode, the car will stop before an obstacle and an LED attached to the vehicle will light up.<br/>
The car starts in automatic mode and travels towards a chosen destination. If prompted, the car will switch over to manual mode to let the user control the car through the application.<br/>
The user could either wait for the obstacle to move or switch to manual mode and maneuver around the obstacle.

# Software and hardware architecture;

The three main components of the system are; the car, a web server, and the application. The web server is responsible for the communication and sending data between the car and the application.

The hardware used for the smart car includes;<br/>
1x Arduino Smart car attached with an ESP32 microcontroller<br/>
1x HC-SR04 Ultrasonic sensor<br/>
1x Breadboard<br/>
8x AA batteries<br/>
Wires

The code for the car is written with the Arduino IDE which is based on C++. It uses the Smart car shield library for the vehicle itself and the WebServer library for the webserver.

The application is written using Android Studio with Kotlin as the chosen language.


# Get started!

Download the repository: https://github.com/DIT112-V20/group-05 

Install the application:
  1. Install Android Studio.
  2. Import the GitHub repository using the link above.
  3. Go to Build and press Build APK.
  4. Locate the file and transfer it to your android smartphone.
  5. Install the APK.

Set-up your Arduino smart car;
  1. Assemble the smart car using an ESP32 microcontroller.
  2. Attach your breadboard and install the HC-SR04 Ultrasonic Sensor.
  3. Connect the sensor with slots 16 and 4 for the Echo and Trigger pins respectively as well as one to GRND and one to 5V.

Upload the Arduino code;
  1. Install the Arduino IDE
  2. Follow these instructions - https://github.com/espressif/arduino-esp32/blob/master/docs/arduino-ide/boards_manager.md
  3. Choose the board<br/>
    a. Tools<br/>
    b. Board<br/>
    c. Choose “DOIT ESP32 devkit v.1”
  4. Install library<br/>
    a. Open library manager<br/>
    b. Install “Smartcar Shield”
  5. On line 7 and 8, enter the access point SSID name and password respectively
  6. Upload the code


# User manual

 -> The first appearing window will be the start page. Here you will find options such as, “Connect”, “Disconnect”, “Settings”, “Get started”.

 -> Go into the settings and set the IP-Address of the car as well as the Port of the web server.

 -> Go back to the main menu and press “Connect”, a green light will indicate that you have been connected to the car.

 -> Once you have been connected to the car you will be able to press “Get Started”, this will prompt a new window which will allow you to select the destination. <br/>
        &nbsp; &nbsp; a. The destination can be selected via the list. <br/>
        &nbsp; &nbsp; b. Alternatively, you can search for a destination in the search box.

 -> A toast will show on the screen once you have selected a destination.

 -> Press “OK” to confirm.<br/>
        &nbsp; &nbsp; a. This will initialize the car and prompt it to move in automatic mode.

 -> On the automatic mode screen, you will be able to observe live telematics given from the car.<br/>
        &nbsp; &nbsp; a. This includes the current speed of the car, the distance to the destination as well as the time it has taken.

 -> Manual mode is available whenever you want to steer on your own.<br/>
        &nbsp; &nbsp; a. NOTE: This will mode does not automatically stop when an obstacle gets close.

 -> If an obstacle is detected in the automatic mode, the car will stop and a LED will light up indicating that an obstacle has been detected.<br/>
        &nbsp; &nbsp; a. You can either wait for the obstacle to disappear.<br/>
        &nbsp; &nbsp; b. Alternatively, you could change to manual mode to maneuver the obstacle.


# YouTube video

https://www.youtube.com/watch?v=_7KRx0TPhbg&feature=youtu.be
