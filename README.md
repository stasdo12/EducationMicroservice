
## EducationMicroservice

Microservice Discovery and Exam Generator

## Description

This project demonstrates a microservice architecture for generating exams by retrieving questions from different microservices. It includes three microservices:

1. DiscoveryMicr: This microservice acts as the Eureka Server for service discovery.

2. EXAMINATOR: This microservice is responsible for generating exams by retrieving questions from other microservices.

3. HistoricMicrApplication: This microservice provides history-related questions.

4. MathMicrApplication: This microservice provides math-related questions.
5. PhysicsMicrApplication: This microservice provides Physic-related questions.

## Technologies Used

- Java
- Spring Boot
- Spring Cloud Netflix Eureka Server
- Spring Cloud Netflix Eureka Client
- Spring Cloud OpenFeign
- RestTemplate

## Setup and Installation

1. Clone the repository.

2. Build and run the DiscoveryMicr application.

3. Build and run the HistoricMicrApplication and MathMicrApplication microservices.

4. Access the EXAMINATOR microservice and make a POST request to the `/exam` endpoint with the desired specifications.

## Usage

1. Start the DiscoveryMicr application.

2. Start the HistoricMicrApplication and MathMicrApplication microservices.

3. Make a POST request to the EXAMINATOR microservice at `/exam` endpoint with the desired specifications. The specifications should be passed as a JSON object containing the microservice names as keys and the number of questions required as values.

4. The EXAMINATOR microservice will retrieve questions from the specified microservices and generate an exam with the requested number of questions.

5. The generated exam will be returned as a response.
