package com.usaa.cars;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class CarController {

    CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars/{name}")
    public Car getCarDetails(@PathVariable String name){
        return carService.getCarDetails(name);
    }

    @PostMapping("/cars")
    public Car addNewCar(@RequestBody Car car){
        return carService.addCar(car);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void carNotFoundHandler(CarNotFoundException e){ }
}
