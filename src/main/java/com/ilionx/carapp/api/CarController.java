package com.ilionx.carapp.api;

import com.ilionx.carapp.model.Car;
import com.ilionx.carapp.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> findAllCars() {
        return ResponseEntity.ok(this.carService.findAll());
    }

    @PostMapping
    public ResponseEntity<Car> create(@RequestBody Car car) {
       return ResponseEntity.ok(this.carService.save(car));
    }

    @GetMapping("{id}")
    public ResponseEntity<Car> findCarById(@PathVariable Long id) {
        Optional<Car> optionalCar = this.carService.findById(id) ;
        if (optionalCar.isPresent()) {
            return ResponseEntity.ok(optionalCar.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/mileage/{mileage}")
    public ResponseEntity<List<Car>> findCarByMileage(@PathVariable int mileage) {
        return ResponseEntity.ok(this.carService.findByMileage(mileage));
    }

    @PutMapping("{id}")
    public ResponseEntity<Car> updateCarById(@PathVariable Long id, @RequestBody Car origin) {
        Optional<Car> optionalCar = this.carService.update(origin, id) ;
        if (optionalCar.isPresent()) {
//            return new ResponseEntity<>(this.carService.save(target), HttpStatus.OK);
            return ResponseEntity.ok(optionalCar.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById (@PathVariable Long id) {
        this.carService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
