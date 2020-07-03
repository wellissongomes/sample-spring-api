package com.cars.dto;

import com.cars.entities.Car;

public class CarDTO {
    private Long id;
    private String name;
    private String type;

    public CarDTO() {}

    public CarDTO(Car car) {
        this.id = car.getId();
        this.name = car.getName();
        this.type = car.getType();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
