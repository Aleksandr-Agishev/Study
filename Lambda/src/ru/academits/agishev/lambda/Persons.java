package ru.academits.agishev.lambda;

public class Persons {
    private String name;
    private int age;

    public Persons(String name, int age) {
        if (age < 0) {
            throw new IllegalArgumentException("age = "+ age+ " don't correct. age must be >=0");
        }
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("age must be >=0");
        }
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
