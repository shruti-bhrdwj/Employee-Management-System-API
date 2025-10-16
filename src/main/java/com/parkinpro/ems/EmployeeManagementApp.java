package com.parkinpro.ems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.parkinpro.ems.exception.EmployeeNotFoundException;
import com.parkinpro.ems.model.Employee;
import com.parkinpro.ems.service.EmployeeService;

@SpringBootApplication
public class EmployeeManagementApp {
	public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementApp.class, args);
        EmployeeService service = new EmployeeService();
        
        try {
            // Add employees
            System.out.println("=== Adding Employees ===");
            service.addEmployee(new Employee(1, "John Doe", "IT", 75000));
            service.addEmployee(new Employee(2, "Jane Smith", "HR", 65000));
            service.addEmployee(new Employee(3, "Bob Johnson", "IT", 80000));
            service.addEmployee(new Employee(4, "Alice Williams", "Finance", 90000));
            service.addEmployee(new Employee(5, "Charlie Brown", "IT", 70000));
            System.out.println("Employees added successfully\n");
            
            // Get employees by department
            System.out.println("=== IT Department Employees ===");
            service.getEmployeeByDepartment("IT").forEach(System.out::println);
            System.out.println();
            
            // Get highest paid employee
            System.out.println("=== Highest Paid Employee ===");
            System.out.println(service.getHighestPaidEmployee());
            System.out.println();
            
            // Get average salary by department
            System.out.println("=== Average Salary - IT Department ===");
            double avgSalary = service.getAverageSalaryByDepartment("IT");
            System.out.printf("Average Salary: $%.2f%n", avgSalary);
            System.out.println();
            
            // Remove employee
            System.out.println("=== Removing Employee ===");
            service.removeEmployee(2);
            System.out.println("Employee removed successfully\n");
            
            // Try to get removed employee (should throw exception)
            System.out.println("=== Testing Exception Handling ===");
            try {
                service.getEmployeeById(2);
            } catch (EmployeeNotFoundException e) {
                System.out.println("Expected exception: " + e.getMessage());
            }
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
}
