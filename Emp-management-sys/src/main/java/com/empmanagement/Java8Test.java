package com.empmanagement;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Java8Test {
    public static void main(String[] args) {
        // Test Stream and Lambda
        List<String> names = Arrays.asList("John", "Jane", "Bob", "Alice");
        List<String> filteredNames = names.stream()
                .filter(name -> name.startsWith("J"))
                .collect(Collectors.toList());
        System.out.println("Names starting with 'J': " + filteredNames);

        // Test LocalDate
        LocalDate today = LocalDate.now();
        System.out.println("Today's date: " + today);

        // Test Optional
        Optional<String> optionalName = Optional.ofNullable(null);
        System.out.println("Optional value: " + optionalName.orElse("No name provided"));

        // Test Lambda with custom interface
        Greeting greeting = name -> System.out.println("Hello, " + name + "!");
        greeting.sayHello("World");
    }

    @FunctionalInterface
    interface Greeting {
        void sayHello(String name);
    }
} 