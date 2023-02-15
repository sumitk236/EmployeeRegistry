package com.registry.employeeregistry.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Employee {
    @Id
    @Column(name = "employeeId")
    private int employeeId;
    @Column(name = "employeeName")
    private String employeeName;
    @Column(name = "dept")
    private String dept;
    @Column(name = "salary")
    private double salary;
}
