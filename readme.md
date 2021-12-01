# Geo Application

Geo application is a program that allows you to collect geolocation data from mobile devices. The application also allows you to get data for a specific device and for a single record.

## Libraries and tools
- Java 11
- Spring Boot
- Spring Security
- Spring JPA
- Maven
- H2
- Lombok
- JPA
- Mockito
- Sl4j

## Application endpoints
GET `localhost:8080/v1/geo/device/{deviceId}` - get all records based on device id

GET `localhost:8080/v1/geo/{id}` - get specified record by id

POST `localhost:8080/v1/geo` - save new record

## Additional info
The database is prepopulated with sample data at application startup.

