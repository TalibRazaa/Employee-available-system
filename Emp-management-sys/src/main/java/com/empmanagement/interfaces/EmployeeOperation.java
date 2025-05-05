package com.empmanagement.interfaces;

import com.empmanagement.model.Employee;

@FunctionalInterface
public interface EmployeeOperation {
    void perform(Employee employee);

    // Default method to demonstrate default method in interface
    default void logOperation(String operationName) {
        System.out.println("Performing operation: " + operationName);
    }
} 