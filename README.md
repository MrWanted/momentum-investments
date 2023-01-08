# Momentum-Investments Assessment
## Problem statemet
Momentum Investments is seeking to automate the withdrawal process for its investors.
The current process is manual, tedious and time-consuming.The investors must first download a form, complete, sign, 
and then send to the servicing ara via email.

## Proposed Solution
To have an automated process that will result in an improved investor and time saved to the
servicing staff.

The automated process should allow investors to select a product they are withdrawing from,
capture the withdrawal amount and banking details the money must be paid into. Once
the withdrawal is completed, investors should receive a notification that shows them the balance before
the withdrawal was made , the amount withdrawn and the closing balance.

### NB: The complete problem statement available via this link: URL

## Application technology stack
The application uses MVC based controllers to expose REST APIs. Uses Spring data to access the database
- [Java 17](https://www.oracle.com/java/technologies/javase/17-relnote-issues.html)
- [Springboot 3](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.0-Release-Notes)
- [PostgresSQL](https://www.postgresql.org/)
- [H2 Database Engine](https://www.h2database.com/html/main.html) 
- [Spring for Apache Kafka](https://docs.spring.io/spring-kafka/reference/html/)
- [JUnit 4](https://junit.org/junit4/)
- [Docker](https://www.docker.com/)// TODO
- [Flyway Database Migration](https://flywaydb.org/)
- [OpenAPI](https://github.com/springdoc/springdoc-openapi)
- and other java related libraries

## How to access the system
- The code can be downloaded FROM [GitHub](https://github.com/MrWanted/momentum-investments.git)
## How to run the system loacally(on development mode)
- mvn spring-boot:run
- [access the system](http://localhost:8080/)
- [access H2 console](http://localhost:8080/h2-console/)
- [access REST API documentation](http://localhost:8080/swagger-ui/index.html)
## How to run using Docker:
- ./mvnw package && java -jar target/momentum-ivestments-0.0.1-SNAPSHOT.jar
- docker build -t momentum/momentum-app . 
- docker run -p 8080:8080 momentum/momentum-app

## How to log in to the system
- Since the API is secured, the user would have to provide username and password in order to access the system
- username: admin, password:password


