package com.cars.servicies;

import com.cars.entities.Car;
import com.cars.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public List<Car> getAll() {
        return carRepository.findAll();
    }

    public Car getById(Long id) {
        return carRepository.findById(id).get();
    }

    public Car getByType(String type) {
        return carRepository.findByType(type);
    }

    public Car save(Car car) {
        return carRepository.save(car);
    }

    public Car update(Long id, Car car) {
        Car oldCar = getById(id);

        oldCar.setName(car.getName());
        oldCar.setType(car.getType());

        return carRepository.save(oldCar);
    }

    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }
}
