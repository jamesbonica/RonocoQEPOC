# RonocoQEPOC
This is a test project using:

>> Selenium Grid

>> TestNG

>> Page Object Design

>> Page Factory Pattern

Here is the process for starting the Selenium Grid hub and nodes:

In a cmd prompt or terminal on the machine which will host the hub, go to the directory in which the Selenium Grid jar is located

Enter the following command:

  java -jar selenium-server-standalone-{version}.jar -role node
  
For each machine that will act as a node, you must ensure the appropriate browsers are installed and corresponding drivers are downloaded

In a cmd prompt or terminal on each machine which will host a node, go to the directory in which the Selenium Grid jar is located

Enter the following command (the example is for using Chrome, Firefox and Edge on the node:

  java -Dwebdriver.chrome.driver="{full path to the folder}chromedriver.exe" -Dwebdriver.gecko.driver="{full path to the folder}geckodriver.exe" -Dwebdriver.edge.driver="{full path to the folder}MicrosoftWebDriver.exe" -jar selenium-server-standalone-{version}.jar -role node -hub {ip address and port of Hub machine}/grid/register -nodeConfig "{full path to the folder}\custom_node.json"
  
The custom JSON file is necessary to enable the Node for Edge. 

Here are the contents of the file:

 {
  "capabilities": [
    {
      "browserName": "MicrosoftEdge",
      "platform": "WIN10",
      "maxInstances": 1
    },
    {
      "browserName": "firefox",
      "platform": "WIN10",
      "maxInstances": 5
    },
    {
      "browserName": "chrome",
      "platform": "WIN10",
      "maxInstances": 5
    },
    {
      "browserName": "internet explorer",
      "platform": "WIN10",
      "maxInstances": 1
    }
  ],
  "hub": "http://selenium-hub-host:4444"
}

Many thanks to the following YouTube Channels:

  Naveen AutomationLabs
  
  Automation Step by Step
  
  edureka!


