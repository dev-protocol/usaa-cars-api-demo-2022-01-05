# Cars API
A Simple API to demonstrate TDD and Spring Boot

## Objectives
1. Review & Discuss TDD
2. Introduction to TDD for Spring Boot
3. Survey Mocking 

## Why do TDD?
- Code coverage ?
- Fulfill only the requirements, know when done
- Good implementations! Simple!
- Drives our design

## Assumptions
1. Overly simple implementations
1. Focus on the TDD process
1. This is not the only way to do it.  Look for YOUR rythm!

## User Story
As a Cars api user, I need to query for a car by its model name.
- The api must respond to the URI "/cars/name" where “name” is the name of the car I want to retrieve.
- The api should return a json object like {“name”: “car’s name”, “type”: “type of car”}
- If the car that I query for does not exist, then the api should return a HttpStatus.NO_CONTENT (204)

## Recepies

### Integration / End-To-End Test
Tests the applications work with all internal dependencies working in an end-to-end nature.  
Integration tests are written to drive out the features that are necessary to meet the requirements
for the application.  Don't spend a lot of time in the details here, as none of this code will
compile until the whole application is functional.

1. Use the annotation `@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)`
    - Note that `@SpringBootTest` will make use of the entire spring boot framework, and should be
    used sparingly, as it will cause a greater load on your tests.
1. `@Autowire TestRestTemplate` and use this to make calls to your proposed endpoint(s)

### Controller Tests
The controller tests, test only the endpoint URIs, and the input/outputs of the endpoints.
Don't be concerned about the details, like actual data.  If the endpoint has to perform
a transformation, that should be tested.

1. Use `@WebMvcTest(Controller.class)` to include special features from Spring for the MVC testing
1. `@Autowire MockMvc` to access the controller directly.
1. Use `@MockBean YourService` To provide a "mock" implementation of your service bean
    - Use `when` / `then` to designate "test" implementation that you require.  Don't underestimate
    the possibilities (`thenThrow()`, `thenReturn(null)`, etc)
      
### Service Tests
The service is just a Plain Old Java Object (POJO), which doesn't require anything from spring.
Create your repository interface, and stub out any required methods.  You will finish this up
in the last step.

1. Use `@ExtendWith(MockitoExtension.class)` to get some Mockito functionality with annotations.
1. Use `@Mock YourRepository` to provide a "mock" implementation of the repository for testing
   (note: we trust the database)
1. Instantiate `YourService` in the `@BeforeEach` setup method, so you get a fresh copy for each 
test.  Don't forget to add the repository mock to the constructor.  Spring will use the proper one
   at production time.
1. Note how we used the `assertThrownBy` to ensure that the error was thrown by the method.

## Other Tips
Look into the APIs for [Mockito](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html)
to dig into all of the cool features of this library, like "[verify](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#6)".

