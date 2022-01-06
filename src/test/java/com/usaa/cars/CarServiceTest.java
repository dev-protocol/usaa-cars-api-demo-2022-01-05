package com.usaa.cars;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    CarService carService;

    @Mock
    CarRepository carRepository;

    @BeforeEach
    void setUp() {
        carService = new CarService(carRepository);
    }

    @Test
    void getCarDetails_exists_returnDetails() {
        when(carRepository.findByName(anyString())).thenReturn(new Car("prius", "hybrid"));

        Car car = carService.getCarDetails("prius");

        assertThat(car).isNotNull();
        assertThat(car.getName()).isEqualTo("prius");
        assertThat(car.getType()).isEqualTo("hybrid");
    }

    @Test
    void getCarDetails_notExists_throwsError() {
        when(carRepository.findByName(anyString())).thenReturn(null);

        assertThatThrownBy(
                () -> carService.getCarDetails("nonExistantCart")
        ).isInstanceOf(CarNotFoundException.class);
    }

    @Test
    void addCar_returnsCar() {
        Car car = new Car("rogue", "xover");
        when(carRepository.save(any(Car.class))).thenReturn(car);
        Car newCar = carService.addCar(car);
        assertThat(newCar).isNotNull();
        assertThat(newCar).isEqualTo(car);
    }
}