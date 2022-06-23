package com.ilionx.carapp.model;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarTest {

    // class under test
    private Car car = new Car();

    @Test
    public void testSetGetBrand() {

        car.setBrand("Ferrari");
        assertEquals("Ferrari", car.getBrand());
    }

    @Test
    public void testSetGetMileage() {
        car.setMileage(11000);
        assertEquals(11000, car.getMileage());
    }
}
