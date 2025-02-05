package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public class Tasks {
    private static AtomicInteger counter = new AtomicInteger();

    protected Double id;
    protected String Name;

    public Tasks(String name) {
        this.id = (double) counter.incrementAndGet();
        this.Name = name;
    }

    public Double getId() {
        return id;
    }

    public String getName() {
        return Name;
    }
}