package com.cars.servicies;

import com.cars.dto.CarDTO;
import com.cars.entities.Car;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CarServiceTest {
    @Autowired
    private CarService carService;

    @Test
    void insertCar() {
        Car car = new Car();
        car.setName("FUSQUINHA DA MASSA");
        car.setType("esportivo");

        CarDTO carDTO = carService.save(car);
        assertNotNull(carDTO);

        Long id = carDTO.getId();
        assertNotNull(id);

        CarDTO c = carService.getById(id);
        assertNotNull(c);

        assertEquals(c.getName(), "FUSQUINHA DA MASSA");
        assertEquals(c.getType(), "esportivo");
    }

    @Test
    void getCars() {
        for(int i = 0; i < 9; i++) {
            Car car = new Car();
            car.setName("FUSQUINHA DA MASSA");
            car.setType("esportivo " + (i + 1));
            CarDTO carDTO = carService.save(car);
        }

        List<CarDTO> carDTOList = carService.getAll();
        assertFalse(carDTOList.isEmpty());

        assertEquals(10, carDTOList.size());
    }
}

