package com.parkinpro.ems.model;

import java.util.Objects;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;


public class Employee {
	private int id;

    @NotBlank(message = "Name cannot be null or empty")
    private String name;

    @NotBlank(message = "Department cannot be null or empty")
    private String department;

    @Min(value = 1, message = "Salary must be greater than 0")
    private double salary;

    /**
     * Default constructor
     */
    public Employee() {
    }

    /**
     * Parameterized constructor
     * @param id Employee ID
     * @param name Employee name
     * @param department Employee department
     * @param salary Employee salary
     */
    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
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

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
