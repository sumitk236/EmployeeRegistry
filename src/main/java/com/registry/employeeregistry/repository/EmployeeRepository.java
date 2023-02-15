package com.registry.employeeregistry.repository;

import com.registry.employeeregistry.dto.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface EmployeeRepository extends CrudRepository<Employee,Integer> {
}
