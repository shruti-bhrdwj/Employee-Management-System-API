package com.parkinpro.ems.service;

import com.parkinpro.ems.model.Employee;
import com.parkinpro.ems.exception.*;
import java.util.*;
import java.util.stream.Collectors;

public class EmployeeService {
    private final Map<Integer, Employee> employees;

    public EmployeeService() {
        this.employees = new HashMap<>();
    }

    /**
     * Adds a new employee to the system
     * @param emp Employee to be added
     * @throws DuplicateEmployeeException if employee with same ID already exists
     * @throws InvalidEmployeeDataException if employee data is invalid
     */
    public void addEmployee(Employee emp) throws DuplicateEmployeeException, InvalidEmployeeDataException {
        if (emp == null) {
            throw new InvalidEmployeeDataException("Employee cannot be null");
        }

        validateEmployee(emp);

        if (employees.containsKey(emp.getId())) {
            throw new DuplicateEmployeeException("Employee with ID " + emp.getId() + " already exists");
        }

        employees.put(emp.getId(), emp);
    }

    /**
     * Removes an employee by ID
     * @param empId Employee ID to remove
     * @throws EmployeeNotFoundException if employee is not found
     */
    public void removeEmployee(int empId) throws EmployeeNotFoundException {
        if (!employees.containsKey(empId)) {
            throw new EmployeeNotFoundException("Employee with ID " + empId + " not found");
        }
        employees.remove(empId);
    }

    /**
     * Gets all employees in a specific department using Streams API
     * @param department Department name
     * @return List of employees in the department
     * @throws EmployeeNotFoundException if no employees found in department
     */
    public List<Employee> getEmployeeByDepartment(String department) throws EmployeeNotFoundException {
        if (department == null || department.trim().isEmpty()) {
            throw new IllegalArgumentException("Department cannot be null or empty");
        }

        List<Employee> result = employees.values().stream()
                .filter(emp -> emp.getDepartment().equalsIgnoreCase(department))
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            throw new EmployeeNotFoundException("No employees found in department: " + department);
        }

        return result;
    }

    /**
     * Gets the employee with the highest salary using Streams API
     * @return Employee with highest salary
     * @throws EmployeeNotFoundException if no employees exist
     */
    public Employee getHighestPaidEmployee() throws EmployeeNotFoundException {
        return employees.values().stream()
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("No employees found in the system"));
    }

    /**
     * Calculates average salary for a department using Streams API
     * @param department Department name
     * @return Average salary
     * @throws EmployeeNotFoundException if department has no employees
     */
    public double getAverageSalaryByDepartment(String department) throws EmployeeNotFoundException {
        if (department == null || department.trim().isEmpty()) {
            throw new IllegalArgumentException("Department cannot be null or empty");
        }

        OptionalDouble average = employees.values().stream()
                .filter(emp -> emp.getDepartment().equalsIgnoreCase(department))
                .mapToDouble(Employee::getSalary)
                .average();

        return average.orElseThrow(() ->
                new EmployeeNotFoundException("No employees found in department: " + department));
    }

    /**
     * Gets an employee by ID
     * @param empId Employee ID
     * @return Employee object
     * @throws EmployeeNotFoundException if employee not found
     */
    public Employee getEmployeeById(int empId) throws EmployeeNotFoundException {
        Employee emp = employees.get(empId);
        if (emp == null) {
            throw new EmployeeNotFoundException("Employee with ID " + empId + " not found");
        }
        return emp;
    }

    /**
     * Gets all employees
     * @return List of all employees
     */
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }

    /**
     * Gets all unique departments
     * @return Set of department names
     */
    public Set<String> getAllDepartments() {
        return employees.values().stream()
                .map(Employee::getDepartment)
                .collect(Collectors.toSet());
    }

    /**
     * Validates employee data
     * @param emp Employee to validate
     * @throws InvalidEmployeeDataException if data is invalid
     */
    private void validateEmployee(Employee emp) throws InvalidEmployeeDataException {
        if (emp.getName() == null || emp.getName().trim().isEmpty()) {
            throw new InvalidEmployeeDataException("Employee name cannot be null or empty");
        }
        if (emp.getDepartment() == null || emp.getDepartment().trim().isEmpty()) {
            throw new InvalidEmployeeDataException("Employee department cannot be null or empty");
        }
        if (emp.getSalary() <= 0) {
            throw new InvalidEmployeeDataException("Employee salary must be greater than 0");
        }
    }
}
