package com.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository repository;

    public boolean saveEmployee(Employee employee) {
        return repository.save(employee);
    }

    public boolean updateEmployee(Employee employee) {
        return repository.update(employee);
    }

    public List<Employee> getAllEmp() {
        return repository.findAll();
    }

    public Employee getEmpById(String id) {
        return repository.findById(Integer.parseInt(id));
    }

    public boolean delete(Employee employee) {
        return repository.delete(employee);
    }
}