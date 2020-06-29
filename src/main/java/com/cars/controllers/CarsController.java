package com.cars.controllers;

import com.cars.entities.Car;
import com.cars.servicies.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cars")
public class CarsController {

    @Autowired
    private CarService carService;

    @GetMapping()
    public List<Car> getAllCars() {
        return carService.getAll();
    }

    @GetMapping("/{id}")
    public Car getCarById(@PathVariable("id") Long id){
        return carService.getById(id);
    }

    @GetMapping("/type/{type}")
    public Car getCarByType(@PathVariable("type") String type) {
        return carService.getByType(type);
    }

    @PostMapping()
    public Car saveCar(@RequestBody Car car) {
        return carService.save(car);
    }

    @PutMapping("/{id}")
    public Car updateCar(@PathVariable("id") Long id, @RequestBody Car car) {
        return carService.update(id, car);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable("id") Long id) {
        carService.deleteById(id);
    }
}
