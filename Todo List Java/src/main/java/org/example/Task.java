package org.example;

public class Task {

    protected Long id;
    protected String title;
    protected String description;
    protected Boolean done;
    protected User creator;

    public Task() {
        this.title = "Titre";
        this.description = "description";
        this.done = false;
    }

    public Task(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.done = false;
    }

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.done = false;
    }


    public Task(Long id, String title, String description, User creator) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.done = false;
        this.creator = creator;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", done=" + done +
                ", creator =" + creator +
                '}';
    }
}