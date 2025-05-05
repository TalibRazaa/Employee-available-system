package com.empmanagement;

import com.empmanagement.interfaces.EmployeeOperation;
import com.empmanagement.model.Employee;
import com.empmanagement.service.EmployeeService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        EmployeeService service = new EmployeeService();

        System.out.println("=== Employee Management System Demo ===\n");

        // 1. Filter employees by department
        System.out.println("1. IT Department Employees:");
        List<Employee> itEmployees = service.getEmployeesByDepartment("IT");
        itEmployees.forEach(emp -> System.out.println(emp.getName() + " - " + emp.getSalary()));

        // 2. Sort employees by salary
        System.out.println("\n2. Employees Sorted by Salary:");
        List<Employee> sortedEmployees = service.getEmployeesSortedBySalary();
        sortedEmployees.forEach(emp -> System.out.println(emp.getName() + " - $" + emp.getSalary()));

        // 3. Get employees with salary above threshold
        System.out.println("\n3. Employees with Salary > $75000:");
        List<Employee> highSalaryEmployees = service.getEmployeesWithSalaryAbove(75000.0);
        highSalaryEmployees.forEach(emp -> System.out.println(emp.getName() + " - $" + emp.getSalary()));

        // 4. Get average salary by department
        System.out.println("\n4. Average Salary by Department:");
        Map<String, Double> avgSalaryByDept = service.getAverageSalaryByDepartment();
        avgSalaryByDept.forEach((dept, avg) -> 
            System.out.println(dept + ": $" + String.format("%.2f", avg)));

        // 5. Demonstrate Optional usage
        System.out.println("\n5. Employee Contact Information (with Optional):");
        service.printEmployeeContactInfo(1L); // Employee with contact info
        service.printEmployeeContactInfo(3L); // Employee without contact info

        // 6. Demonstrate custom operation using functional interface
        System.out.println("\n6. Custom Operation (Salary Increase):");
        EmployeeOperation salaryIncrease = employee -> {
            double newSalary = employee.getSalary() * 1.1;
            System.out.println(employee.getName() + ": $" + employee.getSalary() + 
                             " -> $" + String.format("%.2f", newSalary));
        };
        service.applyCustomOperation(salaryIncrease);

        // 7. Get employees who joined after a specific date
        System.out.println("\n7. Employees who joined after Jan 1, 2020:");
        List<Employee> recentEmployees = service.getEmployeesJoinedAfter(LocalDate.of(2020, 1, 1));
        recentEmployees.forEach(emp -> 
            System.out.println(emp.getName() + " - Joined: " + emp.getJoiningDate()));
    }
} 