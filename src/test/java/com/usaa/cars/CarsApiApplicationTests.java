package com.usaa.cars;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CarsApiApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    CarRepository carRepository;

    @BeforeEach
    void setUp() {
        carRepository.save(new Car("prius","hybrid"));
    }

    @Test
    void getCarDetails_exists_returnsCar() {
        // Arrange
        // Act
        ResponseEntity<Car> response = restTemplate.getForEntity("/cars/prius", Car.class);
        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getName()).isEqualTo("prius");
        assertThat(response.getBody().getType()).isEqualTo("hybrid");
    }
}
