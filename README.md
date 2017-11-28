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
git clone https://github.com/SudarsonS/Challenge.git
cd Challenge
mvn clean install
mvn spring-boot:run
```
To run the project with Jar.
```
git clone https://github.com/SudarsonS/Challenge.git
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

## Continuous Integration

For Continuous Integration, CircleCI is used and #passed tag above re-directs to the build.

## API Documentation

### Create 
To Create a new "Member".

**Request**
```
POST http://localhost:8080/members/

Body:
{ "firstName" : String, "lastName": String , "dateOfBirth" : "yyyy-MM-dd", "postalCode": Integer }

Example:
{ "firstName" : "Johan", "lastName": "Mathew" , "dateOfBirth" : "1991-02-16", "postalCode": 10234 }
```
**Response**

```
201 CREATED     - In case of Success
400 BAD_REQUEST - In case the member is not valid or contains future time
```

### Fetch  
To fetch a member for an given id

**Request**
```
GET http://localhost:8080/members/{id}

Example:
GET http://localhost:8080/members/2
```
**Response**

```
200 OK     - In case of Success
404 NOT_FOUND - In case the member is not found
```

To fetch all the members.

**Request**
```
GET http://localhost:8080/members/
```
**Response**

```
200 OK     - In case of Success
404 NOT_FOUND - In case no member is not found
```
### Update
In order to update the saved member.

**Request**
```
PUT http://localhost:8080/members/

Body: (New Member to update)
{ "memberId":Integer, "firstName" : String, "lastName": String , "dateOfBirth" : "yyyy-MM-dd", "postalCode": Integer }

Example:
{ "memberId":1, "firstName" : "Chris", "lastName": "Mathew" , "dateOfBirth" : "1991-02-16", "postalCode": 10234 }
```
**Response**

```
200 OK     - In case of Success
404 NOT_FOUND - In case the member is not found
```
### Delete  
To Delete a member for an given id

**Request**
```
DELETE http://localhost:8080/members/{id}

Example:
DELETE http://localhost:8080/members/2
```
**Response**

```
200 OK     - In case of Success
404 NOT_FOUND - In case the member is not found
```

To fetch all the members.

**Request**
```
DELETE http://localhost:8080/members/
```
**Response**

```
200 OK     - In case of Success
404 NOT_FOUND - In case no member is not found
```
