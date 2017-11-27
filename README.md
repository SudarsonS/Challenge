[![CircleCI](https://circleci.com/gh/SudarsonS/Challenge.svg?style=svg)](https://circleci.com/gh/SudarsonS/Challenge)
# CodeChallenge
Simple REST application for “Member” service which:
* Create a new member
* Read an existing member
* Update an existing member
* Delete member(s) which are no longer used
* List existing members

## How to run the project?
To run the project with Maven.
```
https://github.com/SudarsonS/Challenge.git
cd Challenge
mvn clean install
mvn spring-boot:run
```
To run the project with Jar.
```
https://github.com/SudarsonS/Challenge.git
cd Challenge
mvn clean package
java -jar target/member-service-1.0.jar
```
To run the test.

```
mvn test
```

## Project Structure 
Maven Project
* `member-service` package to hold everything.
  * `controller` package contains API Endpoints
  * `entity` package contains Model (Member) 
  * `services` package contains Business logic
  * `repository` package contains in-memory repository
  * `exception` package contains Exception handler
  * `configuration` package contains configuration for web console to interact with h2 database
  * `util` package contains helper functions
  
## Database Interaction

In order to view the database and to interact with it, below steps should be followed after starting the server.

```
http://localhost:8080/flightright/console/

configuration:
JDBC URL:    jdbc:h2:mem:flightright_db
User Name:   sa
Password:

Then select "connect"
```
This open the database console.
