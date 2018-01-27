package com.lekmiti.java8.lambda;

import com.lekmiti.java8.models.Employee;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortThenPrintLambdaExpression {

    private static List<Employee> employees;

    public static void main(String[] args) {
        initialization();
        traditionalPrint();
        sortThenPrintInJava7();
        sortThenPrintInJava8();
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

    private static void traditionalPrint(){
        System.out.println("--------------- # traditional for loop -initial order- # ----------------------------------");
        System.out.println("-------------------------------------------------------------------------------------------");

        for (int i = 0; i < employees.size();i++) {
            System.out.println(employees.get(i));
        }
        System.out.println();
    }

    private static void sortThenPrintInJava7(){

        System.out.println("--------------- # Java 7 -order by first name- # ------------------------------------------");
        System.out.println("-------------------------------------------------------------------------------------------");

        // In java7: we can use an anonymous class to override the compare behaviour.
        Collections.sort(employees, new Comparator<Employee>() {
            @Override
            public int compare(Employee e1, Employee e2) {
                return e1.getFirstName().compareTo(e2.getFirstName());
            }
        });

        // This is how to print a list in java7
        for (Employee e : employees) {
            System.out.println(e);
        }

        System.out.println();
    }

    private static void sortThenPrintInJava8(){
        System.out.println("--------------- # Java 8 -order by age- # -------------------------------------------------");
        System.out.println("-------------------------------------------------------------------------------------------");

         /*
         In java8:
            we can use a lambda expression to define the behaviour of the comparator.
            The code is simpler (one line) and human familiar (functional programming).
          */

        Collections.sort(employees, (e1, e2) -> e1.getOld() - e2.getOld());

        // In java8, we can print a list in one line by combining the forEach loop and the lambda expression.
        employees.forEach(e -> System.out.println(e));

        System.out.println();

        System.out.println("--------------- # Java 8 (using method reference) -order by age- # ------------------------");
        System.out.println("-------------------------------------------------------------------------------------------");

        // We can do better by using java8 method reference instead.
        // Method reference is used within a lambda expression.
        // It is used when one method, only, is being called inside a lambda expression.
        // when using method reference, the arguments are passed behind the curtains, which makes the code simpler.
        employees.forEach(System.out::println);
    }

}
