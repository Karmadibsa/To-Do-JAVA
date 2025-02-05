package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public class User {
    private static AtomicInteger counter = new AtomicInteger();

    private Double id;
    private String firstName;

    public User(String Name) {
        this.id = (double) counter.incrementAndGet();
        this.firstName = Name ;
    }

    public Double getId() {
        return id;
    }

    public String getName() {
        return firstName;
    }
}