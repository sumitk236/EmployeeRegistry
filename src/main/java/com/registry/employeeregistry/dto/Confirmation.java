package com.registry.employeeregistry.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Confirmation {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "status")
    private String status;
}
