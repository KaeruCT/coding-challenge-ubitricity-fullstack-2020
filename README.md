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

The resulting jar will be at `target/ubi.carpark-0.0.1-SNAPSHOT.jar` and it can be run by doing this:

```
java -jar target/ubi.carpark-0.0.1-SNAPSHOT.jar
```

When the application is up and running, the documentation can be seen at: http://localhost:8080/swagger-ui.html

You can also see a static HTML documentation in the `api-docs.html` file in the root of this project. This was generated using this tool: https://bootprint.knappi.org/

### Tests

There's 3 tests:

 * ChargingPointControllerIntegrationTest - integration test of the rest controller
 * ChargingPointServiceTest - unit test of the business logic service
 * CarparkUtilsTest - simple test of static utility methods

To run them you can also use mvn:

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

The code for the frontend app is inside the `frontend` directory.

I decided to build the frontend using https://nextjs.org/ (a React framework).

The configuration for the frontend is in the `next.config.js` file.
So far this file only contains the base URL for the backend api. 

## How to build & run

Go inside the `frontend` directory and run these commands:
```
npm install
npm run build
```

This will generate a static build in the `out` directory.
The contents of this directory can be copied on any web server to run the frontend app.

You can also run the app in dev mode:
```
npm run dev
```

## Test

There is only one frontend test: `ChargingPoint.test.js` - which
focuses on testing the widget used to represent a charging point.

To run the frontend tests simply run
```
npm test
```

# Ideas & Considerations

* There is no data persistence, but it could be added using Spring Data & the database of your choice.
* Error handling could be improved both in the backend and the frontend. Right now there's simply some log error calls on the server side.
* There's no monitoring of any kind. Logging can also be improved.
* We can add SSR for the frontend, but it will require running a node server, so the frontend deployment becomes slightly more complicated.
