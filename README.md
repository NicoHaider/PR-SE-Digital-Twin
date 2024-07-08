# PR-SE-Digital-Twin
Here is a installation guide for our digital twin project. If you follow these steps, you should be able to run our programm at your device. The steps marked with optional are for people who do not already have installed the needed programms.

Step 1 (optional) - Install Java JDK 11 or Higher : 
  Go to the Oracle JDK website (https://www.oracle.com/java/technologies/downloads/#java11). Download a version for your operating system. After that install the JDK following the instructions 
  provided on the website. You can check your current version by opening your terminal and putting in this ("java -version") command. 


Step 2 (optional) - Install Maven: 
  Go the the Maven Download Page (https://maven.apache.org/download.cgi). DOwnload the ZIP file and extract it (the location does not matter in this case). Follow the instruction on the   
  website on how to add the "bin" directory of Maven to your system's PATH environment variable. You can verify your installation by opening your terminal and putting in this ("mvn -version") command. 


Step 3 (optional) - Install Node.js and npm
  Go to the Node.js website (https://nodejs.org/en) and download the latest version. Aftwards follow the installation instructions provided on the website. You can verify your installation
  by using these ("node -v" and "npm -v")


Step 4 (optional) - Install Angular CLI
  Open your terminal and type this ("npm install -g @angular/cli") in. You can verify your download by using this ("ng version") command. 


Step 5 (optional) - Install a IDE of your choice
  In this example we will be using IntelliJ IDE from Jetbrains. You can download the latest version form their website ("https://www.jetbrains.com/idea/download/?section=windows") and follow the installation instructions provided on the site. 


Step 6 - Set up the backend
  Open the terminal. Navigate to the directory where you want to clone the repository. Run these ("git clone <https://github.com/NicoHaider/PR-SE-Digital-Twin.git>" and "cd PR-SE-Digital-TwinYV") commands. Open the IntelliJ IDEA. Select "Open" and navigate to the backend 
  project directory (PR-SE-Digital-TwinYW). Click "OK". IntelliJ IDEA should automatically recognize and import the Maven project. If not, click on the Maven tool window on the right side and select "Import Changes". Open the terminal in IntelliJ IDEA and run this ("./mvnw 
  clean install") command. Navigate to "src/main/java/com/example/DigitalTwin/DtApplication.java". Right-click on the file and select "Run 'DtApplication.main()'". The backend should now be running.

Step 7 - Set up the frontend 
  Open a new window in IntelliJ IDEA. Select "Open" and navigate to the frontend project directory (Digital-Twin-Frontend). Click "OK". Go to File -> Settings -> Languages & Frameworks -> Node.js and NPM. Ensure that the correct Node.js binary path is set. Open the terminal 
  in IntelliJ IDEA and run this ("npm install") command. Afterwards enter this ("ng serve") command. Your frontend should now be running on the designated port. 



Now both the backend should be running on http://localhost:8080 and the frontend should be running on http://localhost:4200 and be able to communicate with each other. Congratulation! Have fun with our application and have a great day!
