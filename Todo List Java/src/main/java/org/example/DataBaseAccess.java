package org.example;

import java.util.*;

public class DataBaseAccess {

    private static DataBaseAccess instance;
    private List<User> userList = new ArrayList<>();
    private List<Task> taskList = new ArrayList<>();

    public void fillTaskList() {
        taskList.add(new Task("Faire les courses", "Acheter du lait, des œufs et du pain", userList.get(0)));
        taskList.add(new Task("Réviser Java", "Travailler sur les collections et les streams", userList.get(1)));
        taskList.add(new Task("Préparer la réunion", "Rédiger l'ordre du jour et préparer les slides", userList.get(2)));
        taskList.add(new Task("Faire du sport", "Séance de musculation de 45 minutes", userList.get(3)));
        taskList.add(new Task("Envoyer les emails", "Répondre aux demandes clients en attente", userList.get(4)));
        taskList.add(new Task("Regarder un tutoriel", "Apprendre à utiliser GitHub Actions", userList.get(5)));
        taskList.add(new Task("Planifier les vacances", "Réserver l'hôtel et les billets de train", userList.get(6)));
    }

    public void fillUserList() {
        userList.add(new User("Axel"));
        userList.add(new User("Prisca"));
        userList.add(new User("Lucas"));
        userList.add(new User("Victor"));
        userList.add(new User("Laurent"));
        userList.add(new User("Stéphane"));
        userList.add(new User("Steve"));
        userList.add(new User("Tristan"));
        userList.add(new User("Hélène"));

    }

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
