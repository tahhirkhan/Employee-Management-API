package com.codingshuttle.anujTutorial.tutorial.services;

import com.codingshuttle.anujTutorial.tutorial.dto.EmployeeDTO;
import com.codingshuttle.anujTutorial.tutorial.entities.EmployeeEntity;
import com.codingshuttle.anujTutorial.tutorial.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

/*
    @Autowired
    EmployeeRepository employeeRepository;

    instead of auto wiring the repository object, we do as below i.e., constructor injection.
*/

    final EmployeeRepository employeeRepository;
    final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeDTO> listDTO = new ArrayList<>();
        for (EmployeeEntity empEntity : employeeRepository.findAll()) {
            EmployeeDTO map = modelMapper.map(empEntity, EmployeeDTO.class);
            listDTO.add(map);
        }
/*
        foreach loop can be replaced with stream():
        List<EmployeeDTO> listDTO = employeeRepository
                .findAll()
                .stream().map(empEntity -> modelMapper.map(empEntity, EmployeeDTO.class))
                .collect(Collectors.toList());
        return listDTO;
*/
        return listDTO;
    }

    public EmployeeDTO getEmployeeById(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.getById(id);
        return modelMapper.map(employeeEntity, EmployeeDTO.class);
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        employeeRepository.save(employeeEntity);
        return employeeDTO;
    }

    public boolean deleteEmployeeById(Long id) {
        boolean isPresent = employeeRepository.existsById(id);
        if (!isPresent) {
            return false;
        }
        employeeRepository.deleteById(id);
        return true;
    }
}

// controller ----> service ----> repository
// Presentation <---[DTO]---> Service <---[Entity]--->Persistence
