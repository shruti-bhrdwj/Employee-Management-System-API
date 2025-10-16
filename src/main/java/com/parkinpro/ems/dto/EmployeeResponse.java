package com.parkinpro.ems.dto;

import com.parkinpro.ems.model.Employee;

/**
 * Data Transfer Object for employee response
 */
public class EmployeeResponse {
    private int id;
    private String name;
    private String department;
    private double salary;

    public EmployeeResponse() {}

    public EmployeeResponse(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.department = employee.getDepartment();
        this.salary = employee.getSalary();
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
