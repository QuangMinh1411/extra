package com.training.vti.cardemo.controller;

import com.training.vti.cardemo.entity.Car;
import com.training.vti.cardemo.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/cars")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping
    public ResponseEntity<?> getAllCars(){
        return ResponseEntity.ok(carService.getAllCars());
    }

    @PostMapping
    public ResponseEntity<?> saveCar(@RequestParam("file")MultipartFile file,@RequestParam("model") String model,@RequestParam("maker") String maker){

        return new ResponseEntity<>( carService.saveCar(file,model,maker), HttpStatus.CREATED);
    }

    @GetMapping("/images/{id}")
    public ResponseEntity<?> getCarImage(@PathVariable("id") Integer id){
        Car car = carService.getCar(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(car.getType()))
                .body(car.getImage());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCar(@PathVariable Integer id){
        return new ResponseEntity<>(carService.getCar(id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable Integer id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build(); // 204
    }

    @PatchMapping  ("/update/{id}")
    public ResponseEntity<?> updateCar(@PathVariable Integer id,@RequestBody Car car){
        return new ResponseEntity<>(carService.updateCar(id,car),HttpStatus.OK);
    }
}
