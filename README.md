# Weather service

This application provides a REST endpoint for retrieving the temperature info for a given city name.

The application is implemented in Java using Spring Boot.
Minimum required JDK version is 11.

## Building the and running application

The application can be built by executing the following command

```shell
./mvnw clean package
```

The process builds the application and runs the unit tests.

To run the application on the local machine, execute the following command:

```shell
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

This will start the application on localhost, on port 8080.

When the application starts, it creates its own (H2 in memory) database.

## Interacting with the application

After running the application, Swagger UI can be found [**here**](http://localhost:8080/swagger-ui/index.html?configUrl=/admin/v3/api-docs/swagger-config#/).

Spring actuator is available [**here**](http://localhost:8080/actuator), and a basic health check can be performed 
[**here**](http://localhost:8080/actuator/health).

## Additional info

* Exception handling that treats all exceptions like an internal server error is added to WeatherController as an 
  example. There can be multiple handlers for different types of exceptions/http statuses. 
* There are no separate application-*.yml files for environments such as stg/prd because it is expected that those 
  properties are provisioned through e.g. Helm charts in cloud deployments.
* Separate logback configurations can be created for environments other than local.
* In modular applications, `api` classes can be packaged in a separate module, in order to make it easier for other 
  services to use the provided api. We have a separate `api` package just to show this intent.
* Different security strategies can be implemented, depending on the deployment type (e.g. endpoint is public, 
  or behind a gateway in a Kubernetes cluster, etc.).
