## NOTICE

This repository contains the public FTC SDK for the Ultimate Goal (2020-2021) competition season.

Formerly this software project was hosted [here](https://github.com/FIRST-Tech-Challenge/Skystone).  Teams who are competing in the Ultimate Goal Challenge should use this [new FtcRobotController repository](https://github.com/FIRST-Tech-Challenge/FtcRobotController) instead of last season's (no longer updated) Skystone repository.

## Welcome!
This GitHub repository contains the source code that is used to build an Android app to control a *FIRST* Tech Challenge competition robot.  To use this SDK, download/clone the entire project to your local computer.

## Getting Started
If you are new to robotics or new to the *FIRST* Tech Challenge, then you should consider reviewing the [FTC Blocks Tutorial](https://github.com/FIRST-Tech-Challenge/FtcRobotController/wiki/Blocks-Tutorial) to get familiar with how to use the control system:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[FTC Blocks Online Tutorial](https://github.com/FIRST-Tech-Challenge/FtcRobotController/wiki/Blocks-Tutorial)

Even if you are an advanced Java programmer, it is helpful to start with the [FTC Blocks tutorial](https://github.com/FIRST-Tech-Challenge/FtcRobotController/wiki/Blocks-Tutorial), and then migrate to the [OnBot Java Tool](https://github.com/FIRST-Tech-Challenge/FtcRobotController/wiki/OnBot-Java-Tutorial) or to [Android Studio](https://github.com/FIRST-Tech-Challenge/FtcRobotController/wiki/Android-Studio-Tutorial) afterwards.

## Downloading the Project
If you are an Android Studio programmer, there are several ways to download this repo.  Note that if you use the Blocks or OnBot Java Tool to program your robot, then you do not need to download this repository.

* If you are a git user, you can clone the most current version of the repository:

<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;git clone https://github.com/FIRST-Tech-Challenge/FtcRobotController.git</p>

* Or, if you prefer, you can use the "Download Zip" button available through the main repository page.  Downloading the project as a .ZIP file will keep the size of the download manageable.

* You can also download the project folder (as a .zip or .tar.gz archive file) from the Downloads subsection of the [Releases](https://github.com/FIRST-Tech-Challenge/FtcRobotController/releases) page for this repository.

Once you have downloaded and uncompressed (if needed) your folder, you can use Android Studio to import the folder  ("Import project (Eclipse ADT, Gradle, etc.)").

## Getting Help
### User Documentation and Tutorials
*FIRST* maintains online documentation with information and tutorials on how to use the *FIRST* Tech Challenge software and robot control system.  You can access this documentation using the following link:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[FtcRobotController Online Documentation](https://github.com/FIRST-Tech-Challenge/FtcRobotController/wiki)

Note that the online documentation is an "evergreen" document that is constantly being updated and edited.  It contains the most current information about the *FIRST* Tech Challenge software and control system.

### Javadoc Reference Material
The Javadoc reference documentation for the FTC SDK is now available online.  Click on the following link to view the FTC SDK Javadoc documentation as a live website:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[FTC Javadoc Documentation](https://first-tech-challenge.github.io/FtcRobotController)

Documentation for the FTC SDK is also included with this repository.  There is a subfolder called "doc" which contains several subfolders:

 * The folder "apk" contains the .apk files for the FTC Driver Station and FTC Robot Controller apps.
 * The folder "javadoc" contains the JavaDoc user documentation for the FTC SDK.

### Online User Forum
For technical questions regarding the Control System or the FTC SDK, please visit the FTC Technology forum:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[FTC Technology Forum](https://ftcforum.firstinspires.org/forum/ftc-technology)

# Release Information

## Version 6.1 (20201209-113742)
* Makes the scan button on the configuration screen update the list of Expansion Hubs connected via RS-485
    * Fixes [SkyStone issue #143](https://github.com/FIRST-Tech-Challenge/SkyStone/issues/143)
* Improves web interface compatibility with older browser and Android System WebView versions.
* Fixes issue in UVC driver where some cameras (e.g. certain MS Lifecams) which reported frame intervals as rounded rather than truncated values (e.g. `666667*100ns` instead of `666666*100ns` for 15FPS) would fail to start streaming.
* Adds support in UVC driver for virtual PTZ control
* Adds support in UVC driver for gain (ISO) control
* Adds support in UVC driver for enabling/disable AE priority. This setting provides a means to tell the camera firmware either
    * A) It can undershoot the requested frame rate in order to provide a theoretically better image (i.e. with a longer exposure than the inter-frame period of the selected frame rate allows)
    * B) It *must* meet the inter-frame deadline for the selected frame rate, even if the image may be underexposed as a result
* Adds support for the Control Hub OS 1.1.2 Robot Controller watchdog
    * The Robot Controller app will be restarted if it stops responding for more than 10 seconds
* Adds support for using the Driver Station app on Android 10+
* Introduces an automatic TeleOp preselection feature
    * For details and usage guide, please see [this wiki entry](https://github.com/FIRST-Tech-Challenge/FtcRobotController/wiki/Automatically-Loading-a-Driver-Controlled-Op-Mode)
* Shows icon next to OpMode name in the OpMode list dropdown on the Driver Station to indicate the source of the OpMode (i.e. the programming tool used to create it)
* Fixes issue where the Driver Station app would exit after displaying the Configuring WiFi Direct screen
* Fixes Blocks and OnBotJava prompts when accessed via the REV Hardware Client

## Version 6.0 (20200921-085816)

### Important Notes
* Version 6.0 is the version for the Ultimate Goal season.
* Requires Android Studio 4.0.
* Android Studio users need to be connected to the Internet the first time they build the app (in order to download needed packages for the build).
* Version 5.5 was a moderately large off-season, August 2020, drop.  It's worth reviewing those release notes below also.
* Version 5.5 and greater will not work on older Android 4.x and 5.x phones.  Users must upgrade to an approved Android 6.x device or newer.
* The default PIDF values for REV motors have been reverted to the default PID values that were used in the 2018-2019 season
    * This change was made because the 2018-2019 values turned out to work better for many mechanisms
    * This brings the behavior of the REV motors in line with the behavior of all other motors
    * If you prefer the 2019-2020 season's behavior for REV motors, here are the PIDF values that were in place, so that you can manually set them in your OpModes:
      <br>
      **HD Hex motors (all gearboxes):**
      Velocity PIDF values: `P = 1.17`, `I = 0.117`, `F = 11.7`
      Position PIDF values: `P = 5.0`
      **Core Hex motor:**
      Velocity PIDF values: `P = 4.96`, `I = 0.496`, `F = 49.6`
      Position PIDF values: `P = 5.0`

### New features
* Includes TensorFlow inference model and sample op modes to detect Ultimate Goal Starter Stacks (four rings vs single ring stack).
* Includes Vuforia Ultimate Goal vision targets and sample op modes.
* Introduces a digital zoom feature for TensorFlow object detection (to detect objects more accurately at greater distances).
* Adds configuration entry for the REV UltraPlanetary HD Hex motor

### Enhancements
* Adds setGain() and getGain() methods to the NormalizedColorSensor interface
    * By setting the gain of a color sensor, you can adjust for different lighting conditions.
      For example, if you detect lower color values than expected, you can increase the gain.
    * The gain value is only applied to the argb() and getNormalizedColors() methods, not to the raw color methods.
      The getNormalizedColors() method is recommended for ease-of-use and clarity, since argb() has to be converted.
    * Updates SensorColor Java sample to demonstrate gain usage
* Merges SensorREVColorDistance Java sample into SensorColor Java sample, which showcases best practices for all color sensors
* Improves retrieving values from the REV Color Sensor V3
    * Updates the normalization calculation of the RGB channels
    * Improves the calculation of the alpha channel (can be used as an overall brightness indicator)
    * Fixes the default sensor resolution, which caused issues with bright environments
    * Adds support for changing the resolution and measuring rate of the Broadcom sensor chip
    * Removes IR readings and calculations not meant for the Broadcom sensor chip

### Bug fixes
* Improves reliability of BNO055IMU IMU initialization to prevent random initialization failures (which manifested as `Problem with 'imu'`).
