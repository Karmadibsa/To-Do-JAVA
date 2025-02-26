package org.example;

import me.xdrop.jrand.JRand;

import java.util.ArrayList;
import java.util.List;

public class DataBaseSeeder {

    public static void seed() {
        DataBaseAccess dba = DataBaseAccess.getInstance();
        List<User> users = generateUsers(10);
        users.forEach(dba :: addUser);
        List<Task> tasks = generateTasks(10);
        tasks.forEach(dba :: addTask);
    }

private static List<User> generateUsers(int number) {
    var firstNames = JRand.firstname();
    var lastNames = JRand.lastname();
    List<User> users = new ArrayList<User>();
    for (int i = 0; i < number; i++) {
        var user = new User(firstNames.gen(), lastNames.gen());
        users.add(user);
    }
    return users;
}

private static List<Task> generateTasks(int number) {
    var titleTask = JRand.word();
    var descriptionTask = JRand.paragraph();
    List<Task> tasks = new ArrayList<Task>();
    for (int i = 0; i < number; i++) {
        var task = new Task(titleTask.gen(), descriptionTask.gen());
        tasks.add(task);
    }
    return tasks;
}
}
