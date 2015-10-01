Secondary Display Sample
========================

This application demonstrates the usage of the Secondary Display API in Android
by giving an example of controlling the contents of the secondary display using
controls in the primary screen.

Demo requirements
-----------------

To run this example you need:

* One compatible device to host the application.
* Network connection between the device and the host PC in order to transfer and
  launch the application.
* Establish remote target connection to your Digi hardware before running this
  application.
* A secondary display attached to your device, preferably HDMI (Full-HD).
* U-Boot configured to display video in the primary and secondary displays.

Demo setup
----------

Make sure U-Boot is configured to display video in the primary and secondary 
displays. Check documentation on how to do it.

Make sure the hardware is set up correctly:

1. The device is powered on.
2. The secondary display is connected to the SBC board.
3. The device is connected directly to the PC or to the Local Area Network (LAN)
   by the Ethernet cable.

Demo run
--------

The example is already configured, so all you need to do is to build and 
launch the project.
  
The application main screen allows you to write and configure the text
parameters to display in the secondary display.

The secondary display shows the text entered in the primary one in a blackboard
format.

Tested on
---------

* ConnectCore 6 SBC
* ConnectCore 6 SBC v2