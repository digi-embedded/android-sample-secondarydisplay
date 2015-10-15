Secondary Display Sample
========================

This application demonstrates the usage of the Secondary Display API in Android
by giving an example of controlling the contents of the secondary display using
controls in the primary screen.

Demo requirements
-----------------

To run this example you need:

* One compatible device to host the application.
* A USB connection between the device and the host PC in order to transfer and
  launch the application.
* A secondary display attached to your device, preferably HDMI (Full-HD).
* U-Boot configured to display video in the primary and secondary displays.

Demo setup
----------

Make sure U-Boot is configured to display video in the primary and secondary 
displays. Check documentation on how to do it.

Make sure the hardware is set up correctly:

1. The device is powered on.
2. The secondary display is connected to the SBC board.
3. The device is connected directly to the PC by the micro USB cable.


Demo run
--------

The example is already configured, so all you need to do is to build and 
launch the project.
  
The application main screen allows you to write and configure the text
parameters to display in the secondary display.

The secondary display shows the text entered in the primary one in a blackboard
format.

Compatible with
---------------

* ConnectCore 6 SBC
* ConnectCore 6 SBC v2

License
-------

This software is open-source software. Copyright Digi International, 2014-2015.

This Source Code Form is subject to the terms of the Mozilla Public License,
v. 2.0. If a copy of the MPL was not distributed with this file, you can obtain
one at http://mozilla.org/MPL/2.0/.
