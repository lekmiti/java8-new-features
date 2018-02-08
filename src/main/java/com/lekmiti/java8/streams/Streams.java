package com.lekmiti.java8.streams;

import com.lekmiti.java8.models.Employee;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


public class Streams {

    private  List<Employee> employees;

    @Test
    public void filterByAgeGreaterthan() {

        List<Employee> employeesGreaterThan24 = employees.stream()
                .filter(e -> e.getOld() > 24)
                .collect(Collectors.toList());

        assertTrue(employeesGreaterThan24.size() == 3);

    }

    @Test
    public void findEmployeeOrCreateOne() {

        Employee nurse = employees.stream()
                .filter(e -> e.getJob().equals("Nurse"))
                .findAny()
                .orElse(new Employee("newNurse", "newNurse", 25, "Nurse", "newAddress"));

        Employee mechanic = employees.stream()
                .filter(e -> e.getJob().equals("Mechanic"))
                .findAny()
                .orElse(new Employee("newMechanic", "newMechanic", 30, "Mechanic", "newAddress"));

        assertTrue(nurse.getFirstName().equals("Hana"));
        assertTrue(mechanic.getFirstName().equals("newMechanic"));
    }

    @Test
    public void getFirstNamesOnly() {
        List<String> firstNames = employees.stream().map(e -> e.getFirstName())
                .peek(System.out::println)
                .collect(Collectors.toList());
        List<String> names = Arrays.asList("Mohammed", "Wafa", "Atef", "Soumia", "Hana");
        assertTrue(names.toString().equals(firstNames.toString()));
    }

    @Test
    public void sortEmployeesByAge() {
        List<String> sortedByYounger = employees.stream().sorted((e1, e2) -> e1.getOld() - e2.getOld())
                .peek(System.out::println)
                .map(Employee::getFirstName)
                .collect(Collectors.toList());
        List<String> expectedOrder = Arrays.asList("Wafa", "Hana", "Mohammed", "Soumia", "Atef");
        assertTrue(sortedByYounger.toString().equals(expectedOrder.toString()));


    }

    @Test
    public void getTheFirstEmployee() {
        Employee firstEmployee = employees.stream().findFirst().get();
        assertTrue(firstEmployee.equals(employees.get(0)));

    }

    @Test
    public void getAllsSportsPracticedByEmployees() {

        List<String> sports = employees.stream()
                .map(e -> e.getSports())
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());
        List<String> expected = Arrays.asList("swimming", "football", "running", "handball", "basketball", "skying");

        assertTrue(sports.size() == expected.size() &&
                sports.containsAll(expected) && expected.containsAll(sports));
    }

    @Test
    public void sportsPracticedByMoahmmed() {
        List<String> sportsPracticedByMoahmmed = employees.stream()
                .filter(e -> e.getFirstName().equals("Mohammed"))
                .flatMap(e -> e.getSports().stream())
                .distinct()
                .collect(Collectors.toList());
        List<String> expected = Arrays.asList("swimming", "football");

        assertTrue(sportsPracticedByMoahmmed.size() == expected.size() &&
                sportsPracticedByMoahmmed.containsAll(expected) && expected.containsAll(sportsPracticedByMoahmmed));
    }

    @Test
    public void ArithmeticSequence() {
        List<Integer> sequence = Stream.iterate(0, n -> n + 2).limit(10).collect(Collectors.toList());
        List<Integer> expected = Arrays.asList(0, 2, 4, 6, 8, 10, 12, 14, 16, 18);
        System.out.println(sequence.toString());
        System.out.println(expected.toString());
        assertTrue(sequence.toString().equals(expected.toString()));
    }

    @Test
    public void streamOfInt() {
        IntStream.range(1, 11).forEach(System.out::println);
        IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

     }

     @Test
    public void streamOfLong() {
        LongStream.rangeClosed(1, 6).forEach(System.out::println);
    }

    @Test
    public void streamFromString() {
        "abcdef".chars().forEach(System.out::println);
    }

    @Test
    public  void initUsingStreamOf() {
        List<String> employees = Stream.of(
                new Employee("Mohammed", "Lekmiti", 26, "Adress01", "Software Developer"),
                new Employee("Wafa", "Lekmiti", 22, "Adress02", "Translator"),
                new Employee("Atef", "Lekmiti", 30, "Adress03", "Surgeon"),
                new Employee("Soumia", "Lekmiti", 28, "Adress04", "Teacher"),
                new Employee("Hana", "Lekmiti", 23, "Adress05", "Nurse")
        ).map(Employee::getFirstName).collect(Collectors.toList());
        List<String> names = Arrays.asList("Mohammed", "Wafa", "Atef", "Soumia", "Hana");
        assertTrue(names.toString().equals(employees.toString()));
    }

    @Test
    public void initUsingStreamBuilder() {
        List<String> employeesList = Stream.<Employee>builder()
                .add(new Employee("Mohammed", "Lekmiti", 26, "Adress01", "Software Developer"))
                .add(new Employee("Wafa", "Lekmiti", 22, "Adress02", "Translator"))
                .add(new Employee("Atef", "Lekmiti", 30, "Adress03", "Surgeon"))
                .add(new Employee("Soumia", "Lekmiti", 28, "Adress04", "Teacher"))
                .add(new Employee("Hana", "Lekmiti", 23, "Adress05", "Nurse"))
                .build().map(Employee::getFirstName).collect(Collectors.toList());

        System.out.println();
        List<String> names = Arrays.asList("Mohammed", "Wafa", "Atef", "Soumia", "Hana");
        assertTrue(names.toString().equals(employeesList.toString()));
    }

    @Test
    public void collecting() {

        List<String> nameslist = employees.stream().map(Employee::getFirstName).collect(Collectors.toList());

        Set<String> namesSet = employees.stream().map(Employee::getFirstName).collect(Collectors.toSet());

        Map<String, List<Employee>> employeesByLastname = employees.stream().collect(Collectors.groupingBy(Employee::getLastName));

        Map<String, List<String>> employeeNamesByLastname = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getLastName,
                        Collectors.mapping(
                                Employee::getFirstName,
                                Collectors.toList())
                ));
        List<String> epexted = Arrays.asList("Mohammed", "Wafa", "Atef", "Soumia", "Hana");
        assertTrue(employeeNamesByLastname.keySet().contains("Lekmiti"));
        assertTrue(employeeNamesByLastname.get("Lekmiti").toString().equals(epexted.toString()));

        String names = employees.stream().map(Employee::getFirstName)
                .collect(Collectors.joining(", ", "[", "]"));
        String expectedNames = "[Mohammed, Wafa, Atef, Soumia, Hana]";
        assertTrue(names.equals(expectedNames));

        double averageOld = employees.stream().collect(Collectors.averagingInt(Employee::getOld));
        assertTrue(averageOld == 25.8);

    }

    @Test
    public void reductionOperations() {
        double averageOld = employees.stream()
                .mapToInt(Employee::getOld)
                .average()
                .getAsDouble();

        double olderAge = employees.stream()
                .mapToDouble(Employee::getOld)
                .max()
                .getAsDouble();

        double youngerAge = employees.stream()
                .mapToDouble(Employee::getOld)
                .min()
                .getAsDouble();

        int totalAge = employees
                .stream()
                .mapToInt(Employee::getOld)
                .sum();

        Integer totalAgeUsingReduce = employees
                .stream()
                .map(Employee::getOld)
                .reduce(0, (a, b) -> a + b);

        long emplyeesNbr = employees.stream().count();
        assertTrue(olderAge == 30);
        assertTrue(youngerAge ==  22);
        assertTrue(totalAge == 129);
        assertTrue(totalAgeUsingReduce == 129);
        assertTrue(emplyeesNbr == 5 );
    }

    @Test
    public void parallelStream() {
        List<String> sports = employees.parallelStream().flatMap(e -> e.getSports().stream()).distinct().collect(Collectors.toList());
        List<String> expected = Arrays.asList("swimming", "football", "running", "handball", "basketball", "skying");

        assertTrue(sports.size() == expected.size() &&
                sports.containsAll(expected) && expected.containsAll(sports));
    }

    @Before
    public void initialization() {
        Employee mohammed = new Employee("Mohammed", "Lekmiti", 26, "Adress01", "Software Developer");
        mohammed.addSport(Arrays.asList("swimming", "football"));

        Employee wafa = new Employee("Wafa", "Lekmiti", 22, "Adress02", "Translator");
        wafa.addSport(Arrays.asList("running"));

        Employee atef = new Employee("Atef", "Lekmiti", 30, "Adress03", "Surgeon");
        atef.addSport(Arrays.asList("football", "handball"));

        Employee soumia = new Employee("Soumia", "Lekmiti", 28, "Adress04", "Teacher");
        soumia.addSport(Arrays.asList("basketball"));

        Employee hana = new Employee("Hana", "Lekmiti", 23, "Adress05", "Nurse");
        hana.addSport(Arrays.asList("skying"));

        employees = Arrays.asList(mohammed, wafa, atef, soumia, hana);
    }

    @Test
    public void statistics() {
        IntSummaryStatistics stats = employees.stream().mapToInt(Employee::getOld).summaryStatistics();
        double olderAge = stats.getMax();
        double averageAge = stats.getAverage();
        double youngerAge = stats.getMin();
        double emploeesNbr = stats.getCount();
        double sumofAges = stats.getSum();

        assertTrue(olderAge == 30 && averageAge == 25.8 && youngerAge == 22 && emploeesNbr == 5 && sumofAges == 129);


    }


}
