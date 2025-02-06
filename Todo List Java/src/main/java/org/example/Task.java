package org.example;

import java.util.concurrent.atomic.AtomicInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    private static AtomicInteger counter = new AtomicInteger();

    protected Double id;
    protected String title;
    protected String description;
    protected Boolean done;
    protected User userCreated;


    public Task() {
        this.id = (double) counter.incrementAndGet();
        this.title = "Title unknow";
        this.description = "Description unknow";
        this.done = false;
    }

    public Task(String name, String description) {
        this();
        this.title = name;
        this.description = description;
        this.done = false;
    }

    public Task(String name, String description, User user) {
        this();
        this.title = name;
        this.description = description;
        this.done = false;
        this.userCreated = user;
    }

    public Double getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Boolean isDone() {
        return done;
    }

    public User getUserCreated() {
        return userCreated;
    }

    public void setId(Double id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public void setUserCreated(User userCreated) {
        this.userCreated = userCreated;
    }


    @Override
    public String toString() {
        return "Tasks{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", done=" + done +
                ", userCreated=" + userCreated +
                '}';

    }
}