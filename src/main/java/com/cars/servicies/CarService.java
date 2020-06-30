package com.cars.servicies;

import com.cars.dto.CarDTO;
import com.cars.entities.Car;
import com.cars.exceptions.NoContentException;
import com.cars.exceptions.ObjectNotFoundException;
import com.cars.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public List<CarDTO> getAll() {
        List<Car> cars = carRepository.findAll();
        List<CarDTO> carsDTO = listCarToListCarDTO(cars);
        return carsDTO;
    }

    public CarDTO getById(Long id) {
        Optional<CarDTO> optionalCar = carRepository.findById(id)
                                        .map(car -> new CarDTO(car));
        if(!optionalCar.isPresent()) {
            throw new ObjectNotFoundException("Carro não encontrado.");
        }

        CarDTO car = optionalCar.get();
        return car;
    }

    public List<CarDTO> getAllByType(String type) {
        List<Car> cars = carRepository.findByType(type);
        if(cars.isEmpty()) {
            throw new NoContentException();
        }

        List<CarDTO> carsDTO = listCarToListCarDTO(cars);
        return carsDTO;
    }

    public CarDTO save(Car car) {
        return new CarDTO(carRepository.save(car));
    }

    public CarDTO update(Long id, Car car) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if(!optionalCar.isPresent()) {
            throw new ObjectNotFoundException("Carro não encontrado.");
        }
        Car oldCar = optionalCar.get();
        oldCar.setName(car.getName());
        oldCar.setType(car.getType());
        carRepository.save(oldCar);

        return new CarDTO(oldCar);
    }

    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }

    private List<CarDTO> listCarToListCarDTO(List<Car> cars){
        return cars.stream()
                .map(car -> new CarDTO(car))
                .collect(Collectors.toList());
    }
}
