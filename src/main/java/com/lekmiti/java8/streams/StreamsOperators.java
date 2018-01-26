package com.lekmiti.java8.streams;

import com.lekmiti.java8.models.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamsOperators {
    private static List<Employee> employees;


    public static void main(String[] args){
        initialization();

        System.out.println("------------------------------------example 01--------------------------------------------");
        //Operators: filter, peek.
        filterByAgeGreaterthan(24);
        System.out.println();

        System.out.println("------------------------------------example 02--------------------------------------------");
        // Operators: map, peek.
        getFirstNamesOnly();
        System.out.println();


        System.out.println("------------------------------------example 03--------------------------------------------");
        // Operators: sorted, peek
        sortEmployeesByAge();
        System.out.println();


        System.out.println("------------------------------------example 04--------------------------------------------");
        // Operators: findFirst
        getTheFirstEmployee();
        System.out.println();






    }

    public static List<Employee> filterByAgeGreaterthan(int age){
        return employees.stream().filter(e -> e.getOld() > age)
                .peek(System.out::println)
                .collect(Collectors.toList());
     }


     public static List<String> getFirstNamesOnly(){
        return employees.stream().map(e -> e.getFirstName())
                .peek(System.out::println)
                .collect(Collectors.toList());
    }

    public static List<Employee> sortEmployeesByAge(){
        return employees.stream().sorted((e1, e2) -> e1.getOld() - e2.getOld())
                .peek(System.out::println)
                .collect(Collectors.toList());

    }


    public static void getTheFirstEmployee (){
         employees.stream().findFirst().ifPresent(System.out::println);

    }


    private static void initialization(){
        employees = Arrays.asList(
                new Employee("Mohammed", "Lekmiti", 26, "Adress01", "Software Developer"),
                new Employee("wafa", "Lekmiti", 22, "Adress02", "Translator"),
                new Employee("Atef", "Lekmiti", 30, "Adress03", "Surgeon"),
                new Employee("Soumia", "Lekmiti", 28, "Adress04", "Teacher"),
                new Employee("Hana", "Lekmiti", 23, "Adress05", "Nurse")
        );
    }
}
