# Description

This is a mobile testing project that gives you the possibility to run your tests both in Android and iOS.<br> 
It includes cucumber features as tests and TestNG as runner<br>

# Requirements
`npm install -g appium`<br>
To run on iOS go to Xcode and download simulator of your preference<br>
`brew install carthage`
- add carthage to path<br>

If when executing iOS test you get “An unknown server-side error occurred while processing the command. Original error: Could not determine iOS SDK version: Could not get Xcode version. /Library/Developer/Info.plist does not exist on disk” run:<br>
`sudo xcode-select -s /Applications/Xcode.app`

## Tested on
I have used an android emulator Pixel 2 with version 7.0 and an iOS simulator iPhone 8 with version 13.4.1
Xcode version 11.4.1

# Install
- Java JDK 1.8
- Maven
- Appium
- Android Studio and android emulator (from Android studio - AVD Manager) to run on Android
- Xcode to run on iOS
- Add paths to java, android emulator, mvn ... to $PATH

# Run
#### First start the emulator/simulator

Emulators/Simulators

###### Android emulator
`emulator -list-avds`<br>
`emulator @Pixel2_7.0`<br>

###### IOS simulator
`xcrun simctl list ---> to see the list of the simulators installed`
`/Applications/Xcode.app/Contents/Developer/Applications/Simulator.app/Contents/MacOS/Simulator -CurrentDeviceUDID [your simulator udid]`

##### Then run your tests
Start appium desktop on port 4723 and run:<br>
`mvn clean test -DtestSuite="AndroidApp" -Dcucumber.options='--tags "@Android and @DragAndDrop"'`<br>
`mvn clean test -DtestSuite="IOSApp" -Dcucumber.options='--tags "@iOS and @Stacks"'`<br>
with report included (will be inside target/generated-report)
`mvn clean post-integration-test -DtestSuite="IOSApp" -Dcucumber.options='--tags "@iOS"'`

# Reporter
https://github.com/trivago/cluecumber-report-plugin

### Run reporter standalone
If you don't want to run using phase post-integration-test, you can always use mvn test and generate the report afterwards with<br>
`mvn cluecumber-report:reporting`