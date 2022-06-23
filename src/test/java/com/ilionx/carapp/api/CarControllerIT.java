package com.ilionx.carapp.api;

import com.ilionx.carapp.model.Car;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import javax.sound.midi.MetaEventListener;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class )
@ActiveProfiles("intergrationtests")
public class CarControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate; // sort of Postman

    private  static long currentId =-1;

    private final String url = "/api/cars";
    @Test
    public void test1CreateUsingPost() {
        Car car = new Car();
        car.setLicensePlate("AAGGKK");
        car.setMileage(10000);
        car.setBrand("SKODA");

        ResponseEntity<Car> response = this.testRestTemplate.postForEntity(url, car, Car.class);
        assertEquals(200, response.getStatusCodeValue());

        Car returnedCar = response.getBody();
        assertNotEquals(0, returnedCar.getId());
        assertNotNull(returnedCar);
        assertNotNull(returnedCar);
        assertEquals("SKODA", returnedCar.getBrand());
        currentId = returnedCar.getId();
    }

    @Test
    public void test2Fetchit() {
        ResponseEntity<Car> response = this.testRestTemplate.getForEntity(url+"/"+ currentId, Car.class);
        assertNotNull(response.getBody());
        Car responseCar = response.getBody();
        assertEquals(10000, responseCar.getMileage());
    }
}
