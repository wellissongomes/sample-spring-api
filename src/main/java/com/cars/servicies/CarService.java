package com.cars.servicies;

import com.cars.entities.Car;
import com.cars.exceptions.NoContentException;
import com.cars.exceptions.ObjectNotFoundException;
import com.cars.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public List<Car> getAll() {
        return carRepository.findAll();
    }

    public Car getById(Long id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if(!optionalCar.isPresent()) {
            throw new ObjectNotFoundException("Carro não encontrado.");
        }

        Car car = optionalCar.get();
        return car;
    }

    public List<Car> getAllByType(String type) {
        List<Car> cars = carRepository.findByType(type);
        if(cars.isEmpty()) {
            throw new NoContentException();
        }

        return cars;
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
