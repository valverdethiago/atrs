# ATRS - Air Ticket Reservation System  
The ATRS system is a initial proof of concept created to be the basic infrastructure for a SPA (Single Page Application) that manages air tickets reservation.  
The initial idea was to develop a application where users from the interne could search for tickets and buy them. The company internall staff also should use the system to manage fligths, canceling, changing scheadule, etc.  
For teaching purposes persistence, deployment and execution of the project was simplified, just with the need of the following software installed:  
- Apache Maven 3.1 (or later)
- Java 1.8 (or later)

## Directory Structure
The project is divided in to some modules to simplify and separate concerns. Here they are:
- atrs-model : Package that contains all the business model classes of the system.
- atrs-service: Holds every repository and business facade of the model.
- atrs-web: Responsible to handle the REST requests from the application clients, being a browser, desktop or mobile application.
- atrs-view: Contains the web view layer of the application.
Every module is configured as a maven project.    

## Database
The software was developed to work with MongoDb. For testing purposes We decided to provide an in-memmory solution to store the databas records. Fongo is a alternative to MongoDb used most commonly for testing purposes. It works exactly the same way of the original solution, but doesn't need to be previously installed. 

## Active Directory Users
The internal staff should be able to log in the application using their respecitves credentials. We adopted a Ldap in memory service to emulate the Active Directory. It runs in the same machine of the application backend and there's one single user configured there, the Administrador (user:**admin**|password:**admin**).

## Installation
The software is packaged in a zip file containing the atrs parent folder. Unzip this file and then follow these instructions to start all the services we need:

1. atrs - run *mvn clean install* in the main folder to build the parent project
2. atrs-web:
    - run _*mvn clean install*_ in this folder to build the application backend
