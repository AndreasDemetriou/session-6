package ru.sbt.jschool.session6;

import java.io.Serializable;

public class User implements Serializable {
    int id;
    String name;
    int age;
    int salary;
    public User(int id, String name, int age, int salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }
}
