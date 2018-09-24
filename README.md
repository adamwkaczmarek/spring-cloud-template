# SPRING CLOUD TEMPLATE


## PROJECT DESCRIPTION:

This project should be treated as a template for other  projects based on spring cloud . It contains base configurations for  :
- eureka server
- configuration server
- zull proxy (gateway)
- authentication service (JWT token)

## OTHER FEATURES:
 - docker configuration
 
 # Software needed
1.	Apache Maven (http://maven.apache.org).
2.	Docker (http://docker.com).
3.	Git Client (http://git-scm.com).

# Building the Docker Images
To build the project as a docker image, open a command-line window change to the directory where you have downloaded the source code.

Run the following maven command.  This command will execute the [Spotify docker plugin](https://github.com/spotify/docker-maven-plugin) defined in the pom.xml file.  

   **mvn clean package docker:build**

If everything builds successfully you should see a message indicating that the build was successful.


# Running the services 

Now we are going to use docker-compose to start the actual image.  To start the docker image, go  to the directory 

**spring cloud template dir/docker/common** 

and then run folowing command :

**docker-compose up**

If everything starts correctly you should see a bunch of Spring Boot information fly by on standard out.  At this point all of the services available in project will be running.
