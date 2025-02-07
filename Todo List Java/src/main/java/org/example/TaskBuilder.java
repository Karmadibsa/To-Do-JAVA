package org.example;

public class TaskBuilder {

    protected String title;
    protected String description;
    protected Boolean done;
    protected User userCreated;

    public TaskBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public TaskBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public TaskBuilder setDone(Boolean done) {
        this.done = done;
        return this;
    }

    public TaskBuilder setUserCreated(User userCreated) {
        this.userCreated = userCreated;
        return this;
    }

    public Task createTask() {
        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setDone(done);
        task.setUserCreated(userCreated);
        return task;
    }
}
