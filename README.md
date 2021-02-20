## Arduino Gyro JFX Display
Gyro sensor data visualization with JavaFX program

## Hardware
Any serial out device. <a href="https://github.com/lochungtin/Arduino-Gyro">Repo</a> for example hardware device
Example hardware:
- Arduino Nano
- GY 521 Accelerometer / Gyro Sensor

## Functionality
1. Reads quaterion data from serial port
2. Applies Rodrigue's rotation formula to 3D object
3. Project 3D object to 2D plane with 42/7 projection mapping
4. Update JavaFX canvas and plot the projection
