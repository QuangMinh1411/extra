package com.heaven.apisecurity.repository;

import com.heaven.apisecurity.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
