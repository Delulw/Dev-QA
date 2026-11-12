# Base Student

REST microservice for managing student records with Spring Boot and MongoDB.

## Technology Stack

- Java 21
- Spring Boot 4.1.1
- Spring MVC
- Spring Data MongoDB
- Maven Wrapper
- JUnit 5 and Mockito

## Requirements

- JDK 21 or newer
- MongoDB running locally, or a MongoDB connection URI

The default connection is `mongodb://localhost:27017/student`. To use another
MongoDB instance, set `MONGODB_URI` before starting the application. Do not
commit credentials.

Windows PowerShell example:

```powershell
$env:MONGODB_URI = "mongodb://localhost:27017/student"
```

## Run

Run the tests:

```powershell
.\mvnw.cmd test
```

Start the application:

```powershell
.\mvnw.cmd spring-boot:run
```

The API listens on port `8080`; its base path is `/student`.

## API Endpoints

| Method | Path | Description |
| --- | --- | --- |
| `POST` | `/student` | Create a student |
| `GET` | `/student` | List students |
| `GET` | `/student/find-by-id?id={id}` | Find a student by its MongoDB ID |
| `DELETE` | `/student?id={id}` | Delete a student by its MongoDB ID |

Student IDs are strings. When omitted on creation, MongoDB generates an ID.
Student data is stored in the `student` collection.

## Error Responses

Errors use the JSON shape `{ "message": "...", "httpStatus": "...", "timestamp": 0 }`.
Business validation errors return `400`; missing students return `404`.

## Build

Create a production JAR with:

```powershell
.\mvnw.cmd clean package
```
