Toy project for learning microservices with Spring Boot

It consists of:
1. `discovery`, Eureka server
2. `api-gateway` single entry point for all client requests. Routes all requests to appropriate microservice
3. `config-server` is used for handling config files for all applications
4. `school-api` microservice
5. `student-api` microservice