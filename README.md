# GetSetKeyVal
An app to store and retrieve keys based on prefix and suffix.

KeyVal is simple web app written in java using springBoot v2.6.0 and java 1.8 and uses gradle as the build tool.

To run this project locally:
1. Using Docker - 
    Run " docker run -p 127.0.0.1:8080:8080 sandesh23/keyval:latest" on your cmd/bash
    This will basically pull the docker image from docker hub and run on the your machine. -p maps the container port to the host port.
    You can go to the browser and type http://localhost:8080/hello in the address bar. This sholud return "Hello from KeyVal App!!'.
2. Using IDE:
    Clone this repo to a new directory on your computer and import the project to the IDE of your choice. 
    Run the ShortnerApplication.java
    The app should startup.
    Follow the same procedure as in Docker step 3.
3. Java command:
   If the above 2 options dont work, you can build the project using gradle.
   Run ``` gradle build ``` to compile and build the project. This creates a .jar file in the <rootProject>/build/libs directory.
   Open a cmd in the <rootProject>build/libs directory and run ``` java -jar getSetKeyVal-0.0.1-SNAPSHOT.jar ``` this directly runs the jar file.
   Follow step3 in docker to check if its running.	

 To set a key in the DB open postman and create a post resquest with the below json in request body:
 
 ```
{
	"key" : "example"
	"value" : "examplevalue"
}
```
Send the request to http://localhost:8080/set.
You will get back a response "Key Value set successfully!"

To search keys do a get request on http://localhost:8080/search?suffix=ex or http://localhost:8080/search?prefix=ex