# Coding challenge
**Carpark Ubi**

This is Andres Villarreal's implementation of the Carpark Ubi coding challenge.

# Backend

## How to build & run

Spring Boot allows us to do this with a very simple command:
```
mvn clean spring-boot:run
```

If necessary, you can get a jar for distribution by running:

```
mvn package
```

The resulting jar will be at `target/recruiting-1.0.jar` and it can be run by doing this:

```
java -jar target/recruiting-1.0.jar
```

### Tests

To run only the tests:

```
mvn test
```

## Structure

I used the basic structure I usually use for Spring Boot projects:

  * `config`: Spring config
  * `controller`: REST controllers
  * `exceptions`: Common exceptions used across the project
  * `model`: Data model classes
  * `request`: Classes used by the controllers to receive input
  * `response`: Classes used by the controllers to return output
  * `service`: Service classes containing most of the business logic
  * `util`: Static utility methods

# Frontend

## How to build & run

Go inside the `frontend` directory and run these commands:
```
npm install
npm run build
```

## Test

To run the frontend tests simply run
```
npm test
```

# Ideas & Considerations

* There is no data persistence, but it could be added using Spring Data & the database of your choice.
* Error handling could be improved both in the backend and the frontend.
* There's no monitoring of any kind. Logging can also be improved.

Original README continues below:

## Description:
The task is to implement a simple (frontend & backend) application to manage the charging points installed at Carpark Ubi.

Carpark Ubi has 10 charging points installed. When a car is connected it consumes either 20 Amperes (fast charging) or 10 Amperes (slow charging). Carpark Ubi installation has an overall current input of 100 Amperes so it can support fast charging for a maximum of 5 cars or slow charging for a maximum of 10 cars at one time. A charge point notifies the application when a car is plugged or unplugged. The application must distribute the available current of 100 Amperes among the charging points so that when possible all cars use fast charging and when the current is not sufficient some cars are switched to slow charging. Cars which were connected earlier have lower priority than those which were connected later.

The application must also provide a report with a current state of each charging point returning a list of charging point, status (free or occupied) and - if occupied – the consumed current.

## Requirements:
### Backend:
1. The solution must be implemented as a Spring Boot application with Java.
2. We need to be able to start it and run tests.
3. BIZ logic needs to be implemented correctly.
4. Interaction with the APP needs to happen through well-defined REST APIs and frontend application.
5. Include at least one unit test and one integration test.
6. Solution needs to be thread safe.

### Frontend:
1. The solution must be implemented in modern frontend framework or library (e.g. Angular, React, Vue).
2. The solution should display charging points and allow to plug / unplug a car to charging point at any time. After every action status of charge points should be updated.
3. Include at least one unit test.
​
## Examples:
```
Car is plugged into CP1

Report (frontend): 
CP1 OCCUPIED 20A
CP2 AVAILABLE
...
CP10 AVAILABLE
```

```
Cars have been plugged into CP1, CP2, CP3, CP4, CP5 and CP6

Report (frontend):
CP1 OCCUPIED 10A
CP2 OCCUPIED 10A
CP3 OCCUPIED 20A
CP4 OCCUPIED 20A
CP5 OCCUPIED 20A
CP6 OCCUPIED 20A
CP7 AVAILABLE
...
CP10 AVAILABLE
```
​
## Deliverables:
Link to the git repository with the implementation and the documentation on how to call the API (Swagger/Postman collection/text description).
Please add any details about your ideas and considerations to this README and add it to the repository.