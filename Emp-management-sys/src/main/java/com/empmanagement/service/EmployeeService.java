package com.empmanagement.service;

import com.empmanagement.interfaces.EmployeeOperation;
import com.empmanagement.model.Employee;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class EmployeeService {
    private List<Employee> employees;

    public EmployeeService() {
        this.employees = new ArrayList<>();
        initializeSampleData();
    }

    private void initializeSampleData() {
        employees.add(new Employee(1L, "John Doe", "IT", 75000.0, LocalDate.of(2020, 1, 15)));
        employees.add(new Employee(2L, "Jane Smith", "HR", 65000.0, LocalDate.of(2019, 6, 20)));
        employees.add(new Employee(3L, "Bob Johnson", "Finance", 85000.0, LocalDate.of(2021, 3, 10)));
        employees.add(new Employee(4L, "Alice Brown", "IT", 80000.0, LocalDate.of(2020, 8, 5)));
    }

    // Filter employees by department using Stream
    public List<Employee> getEmployeesByDepartment(String department) {
        return employees.stream()
                .filter(emp -> emp.getDepartment().equals(department))
                .collect(Collectors.toList());
    }

    // Sort employees by salary using Stream
    public List<Employee> getEmployeesSortedBySalary() {
        return employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary))
                .collect(Collectors.toList());
    }

    // Get employees with salary above threshold using Stream
    public List<Employee> getEmployeesWithSalaryAbove(double threshold) {
        return employees.stream()
                .filter(emp -> emp.getSalary() > threshold)
                .collect(Collectors.toList());
    }

    // Get average salary by department using Stream
    public Map<String, Double> getAverageSalaryByDepartment() {
        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)
                ));
    }

    // Demonstrate Optional usage
    public void printEmployeeContactInfo(Long employeeId) {
        employees.stream()
                .filter(emp -> emp.getId().equals(employeeId))
                .findFirst()
                .ifPresent(emp -> {
                    System.out.println("Employee: " + emp.getName());
                    emp.getEmailOptional().ifPresent(email -> System.out.println("Email: " + email));
                    emp.getPhoneNumberOptional().ifPresent(phone -> System.out.println("Phone: " + phone));
                });
    }

    // Demonstrate custom operation using functional interface
    public void applyCustomOperation(EmployeeOperation operation) {
        operation.logOperation("Custom Operation");
        employees.forEach(operation::perform);
    }

    // Get employees who joined after a specific date
    public List<Employee> getEmployeesJoinedAfter(LocalDate date) {
        return employees.stream()
                .filter(emp -> emp.getJoiningDate().isAfter(date))
                .collect(Collectors.toList());
    }
} 