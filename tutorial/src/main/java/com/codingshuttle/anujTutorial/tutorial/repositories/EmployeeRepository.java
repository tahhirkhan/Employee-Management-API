package com.codingshuttle.anujTutorial.tutorial.repositories;

import com.codingshuttle.anujTutorial.tutorial.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
}
