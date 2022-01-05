# Cars API

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
