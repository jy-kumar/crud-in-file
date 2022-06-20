package com.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeService service;

    @GetMapping("/")
    String hello(){
        return "Welcome JK";
    }

    @PostMapping("/emp")
    ResponseEntity<String> saveEmployee(@RequestBody Employee employee){
        if(service.saveEmployee(employee))
            return new ResponseEntity<>("Success",HttpStatus.OK);
        else
            return new ResponseEntity<>("Failed",HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping("/emp")
    ResponseEntity<String> deleteEmployee(@RequestBody Employee employee){
        System.out.println(employee);
        if(service.delete(employee))
            return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
        else
            return new ResponseEntity<>("Failed",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/emp")
    ResponseEntity<String> updateEmployee(@RequestBody Employee employee){
        if(service.updateEmployee(employee))
            return new ResponseEntity<>("Success",HttpStatus.OK);
        else
            return new ResponseEntity<>("Failed",HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("/emp")
    List<Employee> getAllEmp(){
        return service.getAllEmp();
    }

    @GetMapping("/emp/{id}")
    Employee getEmpById(@PathVariable String id){
        return service.getEmpById(id);
    }
}