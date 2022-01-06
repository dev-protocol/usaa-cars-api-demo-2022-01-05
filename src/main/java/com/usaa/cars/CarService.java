package com.usaa.cars;

import org.springframework.stereotype.Service;

@Service
public class CarService {

    CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car getCarDetails(String name) {
        Car car =  carRepository.findByName(name);
        if (car == null){
            throw new CarNotFoundException();
        }else{
            return car;
        }
    }

    public Car addCar(Car car) {
        return null;
    }
}
