# Capitole tech challenge
### Welcome to the README.md file for our Capitole tech challenge

## ADR (architecture decision record)
### Introduction
Java 17 is the latest LTS release of the Java programming language. It comes with a lot of new features and improvements, including enhanced language features, improved garbage collection, and better performance. Project Reactor is a reactive programming library for building non-blocking, event-driven applications. Hexagonal Architecture is an architectural pattern that provides a clean separation between the core logic of an application and its external dependencies.

### Why use Java 17?
Java 17 comes with several language enhancements that make it easier to write cleaner, more concise code. Some of these enhancements include:

- Records: A new way to define classes that are meant to store data.
- Text blocks: A new way to write multi-line strings in Java.
- Additionally, Java 17 provides better performance and more efficient garbage collection, making it a great choice for building high-performance, scalable applications.

### Why use Project Reactor?
Project Reactor is a reactive programming library that provides a simple, composable way to build event-driven, non-blocking applications. It is based on the Reactive Streams specification and provides a range of operators that make it easy to handle data streams asynchronously.

Using Project Reactor can help simplify your code, improve performance, and make it easier to handle complex asynchronous workflows.

### Why use Hexagonal Architecture?
Hexagonal Architecture, also known as Ports and Adapters Architecture, is an architectural pattern that provides a clean separation between the core logic of an application and its external dependencies. This separation helps make it easier to test and maintain the core logic of your application, as well as swap out external dependencies without affecting the core logic.

Using Hexagonal Architecture can help you build more modular, testable, and maintainable applications.

### Conclusion
In conclusion, using Java 17 with Project Reactor and Hexagonal Architecture is a great approach for building high-performance, scalable, and maintainable applications. Java 17 provides several language enhancements and performance improvements that make it a great choice for building modern applications. Project Reactor provides a powerful way to handle asynchronous workflows, and Hexagonal Architecture helps you build more modular, testable, and maintainable applications.

## Layers Description:

Using the Hexagonal Architecture pattern allows us to write highly decoupled, testable, and maintainable code.

1. The Domain Layer: This layer contains the application's core business logic and is where the domain objects, entities, and services reside.

2. The Application Layer: This layer contains the application's use cases and orchestrates the interactions between the domain layer and the external dependencies.

3. The Infra Layer: This layer contains the application's external dependencies and their implementation details.

### Unit Testing in the Domain Layer
In this project, we've implemented unit tests for the domain layer. Unit tests are designed to test individual units of code in isolation, and in this case, we're testing our domain objects, entities, vo and aggregates.

### Sociable Testing in the Application Layer
Sociable tests, also known as integration tests, are designed to test the interactions between the application layer and its external dependencies. In a Hexagonal Architecture application, these tests may involve setting up test data, simulating external services, or even spinning up a test database. Sociable testing helps ensure that the application is functioning correctly in a realistic environment, while still allowing us to test individual units of code in isolation.
