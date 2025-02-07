package org.example;

import java.util.Date;

public class DatedTask extends Task {

    private Date dueDate;

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "DatedTask{" +
                "userCreated=" + userCreated +
                ", done=" + done +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", id=" + id +
                ", dueDate=" + dueDate +
                '}';
    }
}
