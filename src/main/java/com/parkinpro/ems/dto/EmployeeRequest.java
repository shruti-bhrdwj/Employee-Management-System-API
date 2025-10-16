package com.parkinpro.ems.dto;

import jakarta.validation.constraints.*;

/**
 * Data Transfer Object for creating/updating employee
 * Includes validation constraints (Jakarta Validation API)
 */
public class EmployeeRequest {

    @NotNull(message = "Employee ID cannot be null")
    @Positive(message = "Employee ID must be positive")
    private Integer id;

    @NotBlank(message = "Employee name cannot be blank")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Department cannot be blank")
    private String department;

    @NotNull(message = "Salary cannot be null")
    @Positive(message = "Salary must be greater than 0")
    private Double salary;

    // Constructors
    public EmployeeRequest() {}

    public EmployeeRequest(Integer id, String name, String department, Double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
