package com.heaven.apisecurity.service;

import com.heaven.apisecurity.exception.NotFoundException;
import com.heaven.apisecurity.model.Employee;
import com.heaven.apisecurity.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(Integer id) {

        return employeeRepository.findById(id).orElseThrow(()->{
            throw new NotFoundException("Not found with employee with id: " + id);
        });
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return null;
    }

    @Override
    public Boolean deleteById(Integer id) {
        return null;
    }
}
