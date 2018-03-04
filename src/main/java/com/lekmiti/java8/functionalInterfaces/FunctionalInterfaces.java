package com.lekmiti.java8.functionalInterfaces;

import com.lekmiti.java8.models.Employee;
import com.lekmiti.java8.models.Person;
import org.junit.Test;

import java.util.Optional;
import java.util.function.*;

import static org.junit.Assert.assertTrue;

public class FunctionalInterfaces {

    @Test
    // Accept a single input argument and return a boolean.
    public void predicate() {
        // given
        Employee employee = new Employee(null, null, 24, null, null);
        Optional<Employee> maybeEmployee = Optional.of(new Employee(null, null, 24, null, null));

        // when
        Predicate<Employee> isJunior = e -> e.getOld() <= 25;
        maybeEmployee.filter(isJunior);

        // then
        assertTrue(isJunior.test(employee));
        assertTrue(maybeEmployee.isPresent());

    }

    @Test
    // Accept two input arguments and return a boolean.
    public void biPredicate() {

        // given
        Employee employee1 = new Employee(null, null, 24, null, "software developer");
        Employee employee2 = new Employee(null, null, 24, null, "software developer");

        // when
        BiPredicate<Employee, Employee> doTheSameJob = (e1, e2) -> employee1.getJob().equalsIgnoreCase(employee2.getJob());

        // then
        assertTrue(doTheSameJob.test(employee1, employee2));


    }

    @Test
    // Accept a single input argument and return void. It is used to apply side-affects
    public void consumer() {
        // example 01:s
        Optional<Employee> maybeEmployee = Optional.of(new Employee(null, null, 24, null, null));
        Consumer<Employee> printer = System.out::println;
        maybeEmployee.ifPresent(printer);

        // example 2:
        // given
        Employee employee = maybeEmployee.get();
        // when
        Consumer<Employee> ageRaiser = Employee::incrementAge;
        ageRaiser.accept(employee);
        // then
        assertTrue(employee.getOld() == 25);

        // example 03:
        // the andThen() method attaches a consumer to an other one,  allowing by cascading calls of many consumers.
        // in this example , the age raiser increment the age of the  given employee first, then passes it to the printer in order to be printed
        ageRaiser.andThen(printer).accept(employee);

    }

    @Test
    // Accept two input arguments and return void. It is used to apply side-affects

    public void biConsumer() {

        // given
        Employee employee1 = new Employee(null, null, 24, null, null);
        Employee employee2 = new Employee(null, null, 25, null, null);

        // when
        BiConsumer<Employee, Employee> ageRaiser = (e1, e2) -> {
            e1.incrementAge();
            e2.incrementAge();
        };

        ageRaiser.accept(employee1, employee2);

        // then
        assertTrue(employee1.getOld() == 25);
        assertTrue(employee2.getOld() == 26);


    }

    @Test
    // supply a result in a given type from nothing(null)
    public void supplier() {
        // given
        Supplier<Employee> surgeanSupplier = () -> new Employee("Atef", "Lekmiti", 29, "Algiers", "Medical Surgeon");
        // when
        Optional<Employee> maybeEmployee = Optional.of(surgeanSupplier.get());
        // then
        assertTrue(maybeEmployee.get().getJob().equals("Medical Surgeon"));
    }


    @Test
    public void function() {

        // example 01 (apply):
        // given
        Person person = new Person("Hana", "Lekmiti", "Jijel", 22, "Nurse");

        //when
        Function<Person, Employee> hiring = p ->
                new Employee(p.getFirstName(), p.getLastName(), p.getAge(), p.getAddress(), p.getJob());
        Employee hiredasANurse = hiring.apply(person);

        // then
        assertTrue(hiredasANurse.getJob().equals("Nurse"));


        // example 02 (andThen, compose):

        // given
        Function<Integer, Integer> operation1 = x -> x * 2;
        Function<Integer, Integer> operation2 = x -> x + 5;

        // when
        Integer result1 = operation1.andThen(operation2).apply(2);
        Integer result2 = operation1.compose(operation2).apply(2);

        // then
        assertTrue(result1 == 9);
        assertTrue(result2 == 14);

    }


    @Test
    public void biFunction() {

        // example 01 (apply):
        // given
        Employee employee = new Employee(null, null, 24, null, null);

        //when
        BiFunction<Employee, String, Employee> changeAddress = (e, newAddress) -> {
            e.setAddress(newAddress);
            return e;
        };

        employee = changeAddress.apply(employee, "Paris");

        // then
        assertTrue(employee.getAddress().equals("Paris"));


    }


}
