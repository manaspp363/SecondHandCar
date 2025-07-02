package com.app.app.service;

import com.app.app.entity.*;
import com.app.app.payload.CarDto;
import com.app.app.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarService {

    private CarRepository carRepository;
    private BrandRepository brandRepository;
    private YearRepository yearRepository;
    private ModelRepository modelRepository;
    private TransmissionRepository transmissionRepository;
    private FuelRepository fuelRepository;
    private ImageService imageService;

    public CarService(ImageService imageService, FuelRepository fuelRepository, BrandRepository brandRepository, YearRepository yearRepository, ModelRepository modelRepository, TransmissionRepository transmissionRepository, CarRepository carRepository) {
        this.brandRepository = brandRepository;
        this.yearRepository = yearRepository;
        this.modelRepository = modelRepository;
        this.transmissionRepository = transmissionRepository;
        this.carRepository = carRepository;
        this.fuelRepository = fuelRepository;
        this.imageService = imageService;
    }

    public Car saveCar(CarDto carDto) {
        Car car = new Car();

        // Fetch related entities
        Brand brand = brandRepository.findById(carDto.getBrandId())
                .orElseThrow(() -> new EntityNotFoundException("Brand not found"));
        Fuel fuel = fuelRepository.findById(carDto.getFuelId())
                .orElseThrow(() -> new EntityNotFoundException("Fuel not found"));
        Model model = modelRepository.findById(carDto.getModelId())
                .orElseThrow(() -> new EntityNotFoundException("Model not found"));
        Transmission transmission = transmissionRepository.findById(carDto.getTransmissionId())
                .orElseThrow(() -> new EntityNotFoundException("Transmission not found"));
        Year year = yearRepository.findById(carDto.getYearId())
                .orElseThrow(() -> new EntityNotFoundException("Year not found"));


        // Set values
        car.setBrand(brand);
        car.setFuel(fuel);
        car.setModel(model);
        car.setTransmission(transmission);
        car.setYear(year);
        car.setPrice(carDto.getPriceUk());

        return carRepository.save(car);
    }

    public Car updateCar(Long id, CarDto carDto) {
        // Retrieve the car entity or throw an exception if not found
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Car not found with id: " + id));

        // Retrieve associated entities
        Brand brand = brandRepository.findById(carDto.getBrandId())
                .orElseThrow(() -> new EntityNotFoundException("Brand not found with id: " + carDto.getBrandId()));
        Fuel fuel = fuelRepository.findById(carDto.getFuelId())
                .orElseThrow(() -> new EntityNotFoundException("Fuel not found with id: " + carDto.getFuelId()));
        Model model = modelRepository.findById(carDto.getModelId())
                .orElseThrow(() -> new EntityNotFoundException("Model not found with id: " + carDto.getModelId()));
        Transmission transmission = transmissionRepository.findById(carDto.getTransmissionId())
                .orElseThrow(() -> new EntityNotFoundException("Transmission not found with id: " + carDto.getTransmissionId()));
        Year year = yearRepository.findById(carDto.getYearId())
                .orElseThrow(() -> new EntityNotFoundException("Year not found with id: " + carDto.getYearId()));

        // Update car properties
        car.setBrand(brand);
        car.setFuel(fuel);
        car.setModel(model);
        car.setTransmission(transmission);
        car.setYear(year);
        car.setPrice(carDto.getPriceUk());

        // Save updated car and return it
        return carRepository.save(car);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }



@Transactional
    public Car deleteCar(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Car not found with id: " + id));

        boolean imageDeleted = imageService.deleteImage(car);

        if (!imageDeleted) {
            throw new RuntimeException("Failed to delete car image for id: " + id);
        }

        carRepository.delete(car);
        return car;
    }


    public List<Car> getAllByParam(String param) {
        return carRepository.findByParam(param);
    }


}
