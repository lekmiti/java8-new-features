package com.lekmiti.java8.models;

public class Person {
    private String firstName;
    private String lastName;
    private String address;
    private int age;
    private String job;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public String getJob() {
        return job;
    }

    public Person(String firstName, String lastName, String address, int age, String job) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.age = age;
        this.job = job;
    }
}
