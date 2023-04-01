package com.training.vti.cardemo.service;

import com.training.vti.cardemo.entity.Car;
import com.training.vti.cardemo.exception.NotFoundException;
import com.training.vti.cardemo.repository.CarRepository;
import com.training.vti.cardemo.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final FileUtils fileUtils;

    public List<Car> getAllCars(){
        return carRepository.findAll();
    }



    public Car saveCar(MultipartFile file,String model,String maker){
        fileUtils.validateFile(file);
        try {
            String type = file.getContentType();
            Car car = new Car(type,model,maker,file.getBytes());
            return carRepository.save(car);
        } catch (Exception e) {
            throw new RuntimeException("Upload image error");
        }

    }

    public Car getCar(Integer id){
        return carRepository.findById(id).orElseThrow(()->{
            throw new NotFoundException("Not found car with id:" + id);
        });

    }

    public void deleteCar(Integer id){
        Car car = carRepository.findById(id).orElseThrow(()->{
            throw new NotFoundException("Not found car with id:" + id);
        });
        carRepository.delete(car);
    }

    public Car updateCar(Integer id,Car car){
        Car exist = getCar(id);
        exist.setMaker(car.getMaker());
        exist.setModel(car.getModel());
        carRepository.save(exist);
        return exist;
    }
}
