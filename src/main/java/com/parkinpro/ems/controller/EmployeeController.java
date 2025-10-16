package com.parkinpro.ems.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parkinpro.ems.dto.AverageSalaryResponse;
import com.parkinpro.ems.dto.EmployeeRequest;
import com.parkinpro.ems.dto.EmployeeResponse;
import com.parkinpro.ems.dto.ErrorResponse;
import com.parkinpro.ems.dto.MessageResponse;
import com.parkinpro.ems.exception.DuplicateEmployeeException;
import com.parkinpro.ems.exception.EmployeeNotFoundException;
import com.parkinpro.ems.exception.InvalidEmployeeDataException;
import com.parkinpro.ems.model.Employee;
import com.parkinpro.ems.service.EmployeeService;

import jakarta.validation.Valid;

/**
 * REST Controller for Employee Management
 * Provides endpoints for CRUD operations and analytics
 *
 * Base URL: /api/v1/employees
 */
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController() {
        this.employeeService = new EmployeeService();
    }

    /**
     * Add a new employee
     * POST /api/v1/employees
     *
     * @param request EmployeeRequest with employee details
     * @return ResponseEntity with created employee or error response
     */
    @PostMapping
    public ResponseEntity<?> addEmployee(@Valid @RequestBody EmployeeRequest request) {
        try {
            Employee employee = new Employee(
                request.getId(),
                request.getName(),
                request.getDepartment(),
                request.getSalary()
            );

            employeeService.addEmployee(employee);

            EmployeeResponse response = new EmployeeResponse(employee);
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (DuplicateEmployeeException e) {
            ErrorResponse error = new ErrorResponse(409, "Duplicate Employee", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.CONFLICT);

        } catch (InvalidEmployeeDataException e) {
            ErrorResponse error = new ErrorResponse(400, "Invalid Data", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Get employee by ID
     * GET /api/v1/employees/{id}
     *
     * @param id Employee ID
     * @return ResponseEntity with employee or error response
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable int id) {
        try {
            Employee employee = employeeService.getEmployeeById(id);
            EmployeeResponse response = new EmployeeResponse(employee);
            return ResponseEntity.ok(response);

        } catch (EmployeeNotFoundException e) {
            ErrorResponse error = new ErrorResponse(404, "Not Found", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get all employees in a department
     * GET /api/v1/employees/department/{department}
     *
     * @param department Department name
     * @return ResponseEntity with list of employees or error response
     */
    @GetMapping("/department/{department}")
    public ResponseEntity<?> getEmployeesByDepartment(@PathVariable String department) {
        try {
            List<Employee> employees = employeeService.getEmployeeByDepartment(department);
            List<EmployeeResponse> responses = employees.stream()
                    .map(EmployeeResponse::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(responses);

        } catch (EmployeeNotFoundException e) {
            ErrorResponse error = new ErrorResponse(404, "Not Found", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get the highest paid employee
     * GET /api/v1/employees/highest-paid
     *
     * @return ResponseEntity with employee or error response
     */
    @GetMapping("/highest-paid")
    public ResponseEntity<?> getHighestPaidEmployee() {
        try {
            Employee employee = employeeService.getHighestPaidEmployee();
            EmployeeResponse response = new EmployeeResponse(employee);
            return ResponseEntity.ok(response);

        } catch (EmployeeNotFoundException e) {
            ErrorResponse error = new ErrorResponse(404, "Not Found", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get average salary by department
     * GET /api/v1/employees/department/{department}/average-salary
     *
     * @param department Department name
     * @return ResponseEntity with average salary or error response
     */
    @GetMapping("/department/{department}/average-salary")
    public ResponseEntity<?> getAverageSalaryByDepartment(@PathVariable String department) {
        try {
            double average = employeeService.getAverageSalaryByDepartment(department);
            return ResponseEntity.ok(new AverageSalaryResponse(department, average));

        } catch (EmployeeNotFoundException e) {
            ErrorResponse error = new ErrorResponse(404, "Not Found", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete employee by ID
     * DELETE /api/v1/employees/{id}
     *
     * @param id Employee ID
     * @return ResponseEntity with success message or error response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable int id) {
        try {
            employeeService.removeEmployee(id);
            return ResponseEntity.ok(new MessageResponse("Employee deleted successfully"));

        } catch (EmployeeNotFoundException e) {
            ErrorResponse error = new ErrorResponse(404, "Not Found", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }
}
