package com.cars;

import com.cars.dto.CarDTO;
import com.cars.entities.Car;
import org.apache.coyote.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;


import java.util.List;

@SpringBootTest(classes = CarsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CarAPITest {
    @Autowired
    private TestRestTemplate rest;

    private static final String URL = "/api/v1/cars";

    private ResponseEntity<CarDTO> getCar(String url) {
        return rest.getForEntity(url, CarDTO.class);
    }

    private ResponseEntity<List<CarDTO>> getCars(String url) {
        return rest.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<CarDTO>>() {});
    }

    @Test
    void testGetCarList() {
        Car car = new Car();
        car.setName("porshe");
        car.setType("luxo");

        rest.postForEntity(URL, car, null);

        List<CarDTO> carDTOS = getCars(URL).getBody();
        assertNotNull(carDTOS);
        assertEquals(2, carDTOS.size());
        assertEquals("luxo", carDTOS.get(0).getType());
    }

    @Test
    void testSave() {
        Car car = new Car();
        car.setName("porshe");
        car.setType("luxo");

        ResponseEntity<CarDTO> response = rest.postForEntity(URL, car, null);
        System.out.println(response);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void testNoContent() {
        assertEquals(HttpStatus.NO_CONTENT, getCars(URL + "/type/xyz").getStatusCode());

    }
}
