package org.example;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataBaseAccess {

    Scanner InputUser = new Scanner(System.in);

    private static DataBaseAccess instance;
        List<User> userList = new ArrayList<>();
        List<Task> taskList = new ArrayList<>();

    private DataBaseAccess() {
        fillUserList();
        fillTaskList();
    }

    public static DataBaseAccess getInstance() {
        if (instance == null) {
            instance = new DataBaseAccess();
        }
        return instance;
    }

    public void fillTaskList() {
        taskList.add(new Task("Titre", "Description",userList.get(0)));
        taskList.add(new Task("Titre", "Description",userList.get(0)));
        taskList.add(new Task("Titre", "Description",userList.get(0)));
        taskList.add(new Task("Titre", "Description",userList.get(0)));
        taskList.add(new Task("Titre", "Description",userList.get(0)));
        taskList.add(new Task("Titre", "Description",userList.get(0)));
    }

    public void fillUserList() {
        userList.add(new User("Axel"));
        userList.add(new User("Axel"));
        userList.add(new User("Axel"));
        userList.add(new User("Axel"));
        userList.add(new User("Axel"));
        userList.add(new User("Axel"));
    }


    public List<User> getUserList() {
        return userList;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void displayTaskList() {
        if (taskList.isEmpty()) {
            System.out.println("La liste des tâches est vide.");
            return;
        }

        System.out.println("Liste des tâches :");
        for (Task task : taskList) {
            System.out.println("- " + task.title + " " + task.description);
        }
    }

    public void addUser (User user){
        userList.add(user);
    }

    public void addTask (Task task){
        taskList.add(task);
    }
    }
