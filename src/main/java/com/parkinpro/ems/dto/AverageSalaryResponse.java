package com.parkinpro.ems.dto;

public class AverageSalaryResponse {
    private String department;
    private double averageSalary;

    public AverageSalaryResponse() {}

    public AverageSalaryResponse(String department, double averageSalary) {
        this.department = department;
        this.averageSalary = averageSalary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getAverageSalary() {
        return averageSalary;
    }

    public void setAverageSalary(double averageSalary) {
        this.averageSalary = averageSalary;
    }
}
