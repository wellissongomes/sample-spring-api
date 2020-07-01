package com.cars.controllers;

import com.cars.dto.CarDTO;
import com.cars.entities.Car;
import com.cars.servicies.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cars")
public class CarsController {

    @Autowired
    private CarService carService;

    @GetMapping()
    public ResponseEntity<List<CarDTO>> getAllCars() {
        return new ResponseEntity<>(carService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDTO> getCarById(@PathVariable("id") Long id){
        return new ResponseEntity<>(carService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<CarDTO>> getCarsByType(@PathVariable("type") String type) {
        return new ResponseEntity<>(carService.getAllByType(type), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<CarDTO> saveCar(@RequestBody Car car) {
        return new ResponseEntity(carService.save(car), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarDTO> updateCar(@PathVariable("id") Long id, @RequestBody Car car) {
        return new ResponseEntity<>(carService.update(id, car), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable("id") Long id) {
        carService.deleteById(id);
    }
}
