package com.registry.employeeregistry.validation;

import com.registry.employeeregistry.dto.Employee;
import com.registry.employeeregistry.exception.EmployeeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
@Slf4j
public class EmployeeValidation {

    public boolean validateEmployeeDetails(List<Employee> employeeList){
        AtomicBoolean isValid= new AtomicBoolean(true);
        employeeList.stream().forEach(employee -> {
                                 if (!Optional.ofNullable(employee).isPresent()) {
                                    throw new EmployeeException("Employee Object cannot be null or empty");
                                 }
                                 if (Optional.ofNullable(employee).isPresent()) {
                                            if (Optional.ofNullable(employee.getEmployeeId()).isPresent()) {
                                                log.error("Employee Id cannot be blank");
                                                isValid.set(false);
                                                throw new EmployeeException("Employee Id cannot be blank");
                                            }
                                        }
                            });
        return isValid.get();
    }
}
