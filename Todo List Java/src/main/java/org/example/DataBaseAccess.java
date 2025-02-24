package org.example;

import me.xdrop.jrand.JRand;
import me.xdrop.jrand.generators.person.FirstnameGenerator;

import java.util.*;

public class DataBaseAccess {

    private static DataBaseAccess instance;
    FirstnameGenerator firstname = JRand.firstname();

    private DataBaseAccess() {
    }

    public static DataBaseAccess getInstance() {
        if (instance == null) {
            instance = new DataBaseAccess();
        }
        return instance;
    }

    public List<User> getUserList() {
        return userList;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void displayTaskList() {
        System.out.println("Liste des tâches :");
        int i = 0;
        for (Task task : taskList) {
            i++; // Incrémente i pour chaque tâche
            System.out.println(i + "- " + task.getTitle() + " --- " + task.getDescription());
        }
    }

    public void displayUserList() {
        System.out.println("Liste des User :");
        int i = 0;
        for (User user : userList) {
            i++; // Incrémente i pour chaque tâche
            System.out.println(i + "- " + user.getName());
        }
    }


    public Task selectTaskByNumber(int taskNumber) throws ElementNotFoundException {
        // Vérifie si le numéro est valide
        if (taskNumber > 0 && taskNumber <= taskList.size()) {
            Task selectedTask = taskList.get(taskNumber - 1);
            System.out.println("Tâche sélectionnée : " + selectedTask);
            return selectedTask;
        } else {
            throw new ElementNotFoundException("Numéro de tâche invalide.");
        }
    }

    public void addUser(User user) {
        userList.add(user);
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void deleteTask (int task) throws ElementNotFoundException {
        if (task > 0 && task <= taskList.size()) {
            taskList.remove(task);
        } else {
            throw new ElementNotFoundException("Numéro de tâche invalide.");
        }
    }

}
