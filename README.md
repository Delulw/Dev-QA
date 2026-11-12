# Base Student

Base Student is a Spring Boot application scaffold for managing student records. The project currently contains the application entry point and a JPA entity; API, persistence, and service layers are not implemented yet.

## Technology Stack

- Java 21
- Spring Boot 4.1.1
- Maven Wrapper
- Spring Data JPA
- Spring MVC
- PostgreSQL JDBC driver
- Lombok
- JUnit 5 and Spring Boot test support

## Requirements

- JDK 21 or newer
- No separate Maven installation is required; use the included Maven Wrapper.
- PostgreSQL is required once the JPA persistence layer is connected to a database.

## Getting Started

Clone the repository and move into the project directory:

```bash
git clone <repository-url>
cd base_student
```

Run the test suite:

```bash
./mvnw test
```

On Windows, use:

```powershell
.\mvnw.cmd test
```

Start the application:

```bash
./mvnw spring-boot:run
```

On Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

The application is configured with the name `base_student`. Spring Boot uses port `8080` by default.

## Configuration

The default configuration is in `src/main/resources/application.properties`; the development profile is in `src/main/resources/application-dev.properties`. Both files currently only define the application name.

Although the PostgreSQL driver and Spring Data JPA are included, database connection properties have not been added yet. Before using the entity with PostgreSQL, configure a datasource, for example:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/base_student
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

Do not commit real credentials. Prefer environment variables or an ignored local configuration file for local development.

To activate the development profile:

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

## Current Domain Model

`StudentModel` is mapped to the `student` table:

| Property | Database column | Type | Notes |
| --- | --- | --- | --- |
| `id` | `id` | `Integer` | Auto-generated identity primary key |
| `name` | `name` | `String` | Maximum length 80 |
| `lastName` | `last_name` | `String` | Maximum length 50 |
| `phone` | `phone` | `String` | Maximum length 20 |
| `eMail` | `email` | `String` | Maximum length 80 |

Lombok provides getters, setters, constructors, `equals`, `hashCode`, `toString`, and a builder for the entity.

## Project Structure

```text
src/
├── main/
│   ├── java/com/base_student/
│   │   ├── BaseStudentApplication.java
│   │   ├── controller/      # REST or MVC controllers (planned)
│   │   ├── dto/             # Request and response DTOs (planned)
│   │   ├── model/           # JPA entities
│   │   ├── respository/     # Repository interfaces (planned)
│   │   └── service/         # Application services (planned)
│   └── resources/
│       ├── application.properties
│       └── application-dev.properties
└── test/java/com/base_student/
    └── BaseStudentApplicationTests.java
```

## Build and Packaging

Create a production JAR with:

```bash
./mvnw clean package
```

The packaged artifact is written to `target/`. Run it with:

```bash
java -jar target/base_student-0.0.1-SNAPSHOT.jar
```

## Testing

The current test suite contains a Spring application context smoke test. Add controller, service, repository, and integration tests as those layers are implemented.

## API Status

No HTTP endpoints are currently exposed. Controllers, repositories, services, DTOs, validation, and database migrations remain to be implemented.

## License

No license has been specified for this project yet.