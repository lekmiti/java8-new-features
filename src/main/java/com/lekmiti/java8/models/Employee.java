package com.lekmiti.java8.models;

import java.util.Comparator;

public class Employee  {

    private String firstName;
    private String lastName;
    public int old;
    private String address;
    private String job;

    public Employee(String firstName, String lastName, int old, String address, String job) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.old = old;
        this.address = address;
        this.job = job;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getOld() {
        return old;
    }

    public String getAddress() {
        return address;
    }

    public String getJob() {
        return job;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (old != employee.old) return false;
        if (firstName != null ? ! firstName.equals(employee.firstName) : employee.firstName != null) return false;
        if (lastName != null ? ! lastName.equals(employee.lastName) : employee.lastName != null) return false;
        if (address != null ? ! address.equals(employee.address) : employee.address != null) return false;
        return job != null ? job.equals(employee.job) : employee.job == null;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + old;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (job != null ? job.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", old=" + old +
                ", address='" + address + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}
