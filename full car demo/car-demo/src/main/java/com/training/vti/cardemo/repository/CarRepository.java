package com.training.vti.cardemo.repository;

import com.training.vti.cardemo.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car,Integer> {
}
