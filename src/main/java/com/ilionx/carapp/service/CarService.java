package com.ilionx.carapp.service;

import com.ilionx.carapp.model.Car;
import com.ilionx.carapp.persistence.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> findByBrand(String brand) {
        return carRepository.findByBrand(brand);
    }

    @Transactional
    public Car save (Car car) {
        Car savedCar = this.carRepository.save(car);
//        Car savedCar = new Car();
        return savedCar;
    }

    @Transactional
    public Optional<Car> update(Car source, Long id) {
        Optional<Car> optionalCar = this.carRepository.findById(id);
        if (optionalCar.isPresent()) {
            Car target = optionalCar.get();
            target.setBrand(source.getBrand());
            target.setLicensePlate(source.getLicensePlate());
            target.setMileage(source.getMileage());

            return Optional.of(this.save(target));
        }
        else {
            return Optional.empty();
        }
    }

    public List<Car> findByLicensePlate(String licensePlate) {
        return carRepository.findByLicensePlate(licensePlate);
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public Optional<Car> findById(Long aLong) {
        return carRepository.findById(aLong);
    }

    public void deleteById(Long aLong) {
        carRepository.deleteById(aLong);
    }

    public List<Car> findByMileage(int mileage) {
        return this.carRepository.findByMileage(mileage);
    }

    @Transactional
    public void deleteById(long id) {
        this.carRepository.deleteById(id);
    }
}
