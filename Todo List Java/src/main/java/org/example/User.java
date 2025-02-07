package org.example;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class User {
    private static AtomicInteger counter = new AtomicInteger();

    private Double id;
    private String firstName;

    public User() {
        this.id = (double) counter.incrementAndGet();
        this.firstName = "Inconnu";
    }

    public User(String name) {
        this();
        this.firstName = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                '}';
    }

    public Double getId() {
        return id;
    }

    public String getName() {
        return firstName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(firstName, user.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName);
    }
}