# Microservices Demo Application

## Components
- `discovery-server` - This microservice has implemented Eureks Discovery Server. It doesn't register with itself.
- `movie-catalog-service` - Spring Boot module.
	To access hystrix dashboard - http://localhost:8081/hystrix and pass http://localhost:8081/actuator/hystrix.stream in text field.
- `movie-info-service` - Spring Boot module.
- `ratings-data-service` - Spring Boot module.
- `spring-cloud-config-server` - This microservice has implemented Spring Cloud Config Server.
- `spring-kafka-demo` - This is a Spring Boot module with Kafka implementation. [goto Kafka Module](/spring-boot-microservices-workshop/spring-kafka-demo)
