package com.lekmiti.java8.optional;

import com.lekmiti.java8.models.Employee;
import org.junit.Test;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OptionalTests {


    @Test
    public void value_should_not_be_present_when_creating_empty_optional() {
        Optional<Employee> noEmployee = Optional.empty();
        assertFalse(noEmployee.isPresent());
    }

    @Test
    public void value_should_be_present_when_it_is_not_null() {
        Employee employee = new Employee();
        Optional<Employee> maybeEmployee = Optional.of(employee);
        assertTrue(maybeEmployee.isPresent());

    }

    @Test(expected = NullPointerException.class)
    public void value_should_not_be_null_when_using_optional_of() {
        Employee employee = null;
        Optional<Employee> optional = Optional.of(employee); // throw a NullPointerException

    }

    @Test
    public void should_use_optional_ofnullable_when_we_expect_the_value_to_be_null_or_not() {
        Employee employee = null;
        Optional<Employee> maybeEmployee = Optional.ofNullable(employee); // Safe from NullPointerException
        assertFalse(maybeEmployee.isPresent());
        employee = new Employee();
        assertTrue(Optional.ofNullable(employee).isPresent());

    }

    @Test
    public void enhance_code_readability_using_if_present() {

        // given
        Employee firstEmployee = new Employee("Mohammed", "LEKMITI", 25, "Paris", "software developer");
        // when
        Optional<Employee> maybeEmployee = Optional.of(firstEmployee);
        // then
        maybeEmployee.ifPresent(System.out::println);

       /*

        traditionally, we do something like:
        if(firstEmployee != null){
            System.out.println(firstEmployee);
        }*/

    }

    @Test
    public void should_get_default_value_when_the_first_value_is_absent() {
        // given
        Employee firstEmployee = null;
        Employee secondEmployee = new Employee("Wafâa", "LEKMITI", 21, "Paris", "translator");
        Employee thirdEmployee = new Employee("Atef", "LEKMITI", 29, "Algiers", "Surgeon");
        // when
        Employee firstOrSecondEmployee = Optional.ofNullable(firstEmployee).orElse(secondEmployee);
        Employee firstOrThirdEmployee = Optional.ofNullable(firstEmployee).orElseGet(() -> thirdEmployee);
        // then
        assertTrue(firstOrSecondEmployee.equals(secondEmployee));
        assertTrue(firstOrThirdEmployee.equals(thirdEmployee));

    }

    @Test
    public void performance_orElse_against_orElseGet()  {
        // given
        Employee employee = null;

        // when
        LocalTime localTime1 = LocalTime.now();
        Employee usingOrElse = Optional.ofNullable(employee).orElse(aCallThatTakesSomeTime());
        LocalTime localTime2 = LocalTime.now();
        Employee usingOrElseGet = Optional.ofNullable(employee).orElse(aCallThatTakesSomeTime());
        LocalTime localTime3 = LocalTime.now();
        long executionTimeUsingOrElse = ChronoUnit.MILLIS.between(localTime1, localTime2);
        long executionTimeUsingOrElseGet = ChronoUnit.MILLIS.between(localTime2, localTime3);

        // then
        assertTrue(executionTimeUsingOrElse >= executionTimeUsingOrElseGet);
    }

    private Employee aCallThatTakesSomeTime()   {
        // a call for a web service or a database
        int sum = 0;
        for (int i = 0; i < 10000; i++)
            sum = sum + i;
        return new Employee();
    }


    @Test(expected = IllegalArgumentException.class)
    public void should_throw_an_exception_when_the_value_is_absence() {
        // given
        Employee firstEmployee = null;
        // when
        Employee optional = Optional.ofNullable(firstEmployee).orElseThrow(IllegalArgumentException::new);
    }

    @Test
    public void should_get_the_value(){
        // given
        Employee firstEmployee = new Employee("Mohammed", "LEKMITI", 25, "Paris", "software developer");
        Optional<Employee> maybeEmployee = Optional.of(firstEmployee);
        // when
        Employee value = maybeEmployee.get();
        // then
        assertEquals(value,firstEmployee);

    }

    @Test(expected = NoSuchElementException.class)
    public void should_throw_an_eception_when_getting_a_null_value() {
        Optional<Employee> opt = Optional.ofNullable(null);
        Employee employee = opt.get();
    }

    @Test
    public void filter_and_map(){
       Employee employee = new Employee("Mohammed", "LEKMITI", 25, "Paris", "software developer");
       Optional<Integer> maybeAge = Optional.of(employee)
                .map(e -> e.getOld())
                .filter(age -> age > 24);
        maybeAge.ifPresent(age -> assertTrue(true));

    }

}
