package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner InputUser = new Scanner(System.in);
        DataBaseSeeder dbSeeder = new DataBaseSeeder();
        DataBaseSeeder.seed();
        DataBaseAccess db = DataBaseAccess.getInstance();


        int choiceMenu;
        do {
            System.out.println("Que voulez vous faire ? 1.Créez une tache 2.Supprimez une tache 3.Modifiez une tache 4.Afficher les taches 5.Afficher les User 6.Quittez ");
            choiceMenu = Integer.parseInt(InputUser.nextLine());
            switch (choiceMenu) {
                case 1:
                    System.out.println("Enter your name tasks : ");
                    String title = InputUser.nextLine();
                    System.out.println("Enter your description tasks : ");
                    String description = InputUser.nextLine();
                    Task task = new Task(title, description);
                    db.addTask(task);
                    break;
                case 2:
                    db.displayTaskList();
                    System.out.println("Enter number of tasks to delete : ");
                    int choiceDelete = Integer.parseInt(InputUser.nextLine());
                    try {
                        db.selectTaskByNumber(choiceDelete);
                        db.deleteTask(choiceDelete - 1);
                        System.out.println("Tache supprimé");
                    } catch (ElementNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    db.displayTaskList();
                    break;

                case 3:
                    db.displayTaskList();
                    System.out.println("Enter number of tasks to edit : ");
                    int choiceUpdate = Integer.parseInt(InputUser.nextLine());
                    try {
                        Task taskUpdate = db.selectTaskByNumber(choiceUpdate);
                        System.out.println("Enter your name tasks : ");
                        String titleUpdate = InputUser.nextLine();
                        System.out.println("Enter your description tasks : ");
                        String descriptionUpdate = InputUser.nextLine();

                        taskUpdate.setTitle(titleUpdate);
                        taskUpdate.setDescription(descriptionUpdate);

                        System.out.println("Tache Modifié");

                    } catch (ElementNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    db.displayTaskList();
                    break;
                case 4:
                    db.displayTaskList();
                    System.out.println("Tache Affiché");

                    break;
                case 5:
                    db.displayUserList();
                    System.out.println("Tache Affiché");

                    break;
                case 6:
                    System.out.println("Au revoir");
                    break;
                default:
                    System.out.println("Choix incorrect");
                    break;
            }
        }
        while (choiceMenu != 6);
    }
}
