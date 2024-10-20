package com.codingshuttle.anujTutorial.tutorial.controllers;

import com.codingshuttle.anujTutorial.tutorial.dto.EmployeeDTO;
import com.codingshuttle.anujTutorial.tutorial.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

/*
    @Autowired
    EmployeeService employeeService;

    instead of doing as above, we do as below:
*/

    final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/health-check")
    public String healthCheck() {
        return "Hello World";
    }

    @GetMapping(path = "/get-all-employees")
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping(path = "/get-employee/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping(path = "/create-employee")
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO employee) {
        return employeeService.createNewEmployee(employee);
    }

    @DeleteMapping(path = "/delete-employee/{id}")
    public boolean deleteEmployeeById(@PathVariable Long id) {
        return employeeService.deleteEmployeeById(id);
    }

}
