package com.heaven.apisecurity.service;

import com.heaven.apisecurity.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee addEmployee(Employee employee);
    List<Employee> getAllEmployees();

    Employee findById(Integer id);

    Employee updateEmployee(Employee employee);

    Boolean deleteById(Integer id);

}
