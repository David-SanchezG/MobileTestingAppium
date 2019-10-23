# Description

This is a mobile testing project that gives you the possibility to run different types of tests (fragmented/functional) sequentially or in parallel.<br> 
It includes cucumber features as tests and TestNG as runner<br>
It works for both Android and iOS.

# Requirements
`npm install -g appium`<br>
To run on iOS go to Xcode and download simulator of your preference<br>
`brew install ios-deploy`<br>
`brew install carthage`
- add carthage to path<br>

If when executing iOS test you get “An unknown server-side error occurred while processing the command. Original error: Could not determine iOS SDK version: Could not get Xcode version. /Library/Developer/Info.plist does not exist on disk” run:<br>
`sudo xcode-select -s /Applications/Xcode.app`

# Install
- Java JDK 1.8
- Maven
- Appium
- Android Studio
- Android emulators (from visual studio - AVD Manager)
- Add paths to java, android emulator, mvn ... to $PATH

# Run
#### First start the emulators and grid

Emulators/Simulators

###### Android emulator
`emulator -list-avds`<br>
`emulator @Pixel_7.0 -port 5554`<br>
`emulator @Pixel_3 -port 5554`

###### IOS simulator
`ios-sim showdevicetypes`<br>
`ios-sim start --devicetypeid "iPhone-6, 11.4" &`<br>
`ios-sim start --devicetypeid "iPhone-XS-Max, 12.2" &`<br>
`xcrun simctl list ---> devices - state - udid`

##### Then run your tests
Right click on Unit.xml/Functional.xml/Fragmented.xml and run<br>
or <br>
`mvn clean test -DtestSuite="Unit"`<br>
`mvn clean test -Dcucumber.options='--tags "@test and @test2"'`<br>
with report and all parameters included<br>
`mvn clean post-integration-test -DtestSuite="Fragmented" -Dcucumber.options='--tags "@test1"' -DrunOn=grid -DappVersion=staging`

# GRID
This framework is prepared to use always the grid and appium nodes through it<br>
### HUB
`java -jar ~/Testing/selenium-server-standalone-3.141.59.jar -role hub &`

### Nodes set up
Before starting the nodes you should know the emulator udid, platform and version for each of them, you can use commands provided above to list devices connected<br>
The port is up to you but must match the one included inside the json (configuration.port and configuration.url) created for each device<br>
- `appium --nodeconfig ~/Testing/Android7.json -p 4730 --session-override --default-capabilities '{ "udid": "emulator-5554", "automationName": "uiautomator2", "deviceName": "Pixel_7.0", "systemPort": 8120, "platformVersion":"7.0" , "platformName":"Android", "newCommandTimeout":20 }' &`
- `appium --nodeconfig ~/Testing/Android9.json -p 4732 --session-override --default-capabilities '{ "udid": "emulator-5556", "automationName": "uiautomator2", "deviceName": "Pixel_3", "systemPort": 8130, "platformVersion":"9.0" , "platformName":"Android", "newCommandTimeout":20 }' &`

Local
- `appium --nodeconfig ~/Testing/iPhone6.json -p 4734 --session-override --default-capabilities '{ "udid": "7B8CEACC-3E31-4E3F-858A-66E04631117C", "automationName": "XCUITest", "deviceName": "iPhone 6", "wdaLocalPort": 8140, "platformVersion":"11.4" , "platformName":"iOS", "newCommandTimeout":20 }' &`
- `appium --nodeconfig ~/Testing/iPhonexsmax.json -p 4736 --session-override --default-capabilities '{ "udid": "DA8AF230-0E15-44B4-A318-3B10A46DA127", "automationName": "XCUITest", "deviceName": "iPhone Xs Max", "wdaLocalPort": 8150, "platformVersion":"12.2" , "platformName":"iOS", "newCommandTimeout":20 }' &`

Grid
- `appium --nodeconfig ~/Testing/iPhone6.json -p 4734 --session-override --default-capabilities '{ "udid": "6949054D-B1AA-4FE7-A7FA-E529E91DD4AF", "automationName": "XCUITest", "deviceName": "iPhone 6", "wdaLocalPort": 8140, "platformVersion":"11.4" , "platformName":"iOS", "newCommandTimeout":20 }' &`
- `appium --nodeconfig ~/Testing/iPhonexsmax.json -p 4736 --session-override --default-capabilities '{ "udid": "B2EAD9FF-0878-4363-B507-07732E373CBB", "automationName": "XCUITest", "deviceName": "iPhone Xs Max", "wdaLocalPort": 8150, "platformVersion":"12.2" , "platformName":"iOS", "newCommandTimeout":20 }' &`

Each of the json should include properties as per below example:
```
{
  "capabilities":
      [
        {
          "browserName": "Pixel 2-7.0",
          "version":"7.0",
          "maxInstances": 1,
          "platform":"ANDROID"
        }
      ],
  "configuration":
  {
    "cleanUpCycle":2000,
    "timeout":30000,
    "proxy": "org.openqa.grid.selenium.proxy.DefaultRemoteProxy",
    "url":"http:127.0.0.1:4730/wd/hub",
    "host": "127.0.0.1",
    "port": 4730,
    "maxSession": 1,
    "register": true,
    "registerCycle": 5000,
    "hubPort": 4444,
    "hubHost": "127.0.0.1",
    "hubProtocol": "http"
  }
}
```

# Reporter
https://github.com/trivago/cluecumber-report-plugin

### Run reporter standalone
If you don't want to run using phase post-integration-test, you can always use mvn test and generate the report afterwards with<br>
`mvn cluecumber-report:reporting`