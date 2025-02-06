package org.example;

import java.util.Scanner;

public class Main {

    public static void main (String[] args) {
        Scanner InputUser = new Scanner(System.in);
        DataBaseAccess db = DataBaseAccess.getInstance();

        System.out.println("Enter your name : ");
        User user = new User(InputUser.nextLine());
        db.addUser (user);
        System.out.println(user);


        int choiceMenu;
        do {
            System.out.println("Que voulez vous faire ? 1.Créez une tache 2.Supprimez une tache 3.Modifiez une tache 4.Afficher les taches 5.Quittez ");
            choiceMenu = Integer.parseInt(InputUser.nextLine());
            switch (choiceMenu) {
                case 1:
                    System.out.println("Enter your name tasks : ");
                    String title = InputUser.nextLine();
                    System.out.println("Enter your description tasks : ");
                    String description = InputUser.nextLine();
                    Task task = new Task(title, description,user);
                    db.addTask(task);
                    break;
                case 2:
                    System.out.println("Tache supprimé");
                    break;

                case 3:
                    System.out.println("Tache Modifié");
                    break;
                case 4:
                    db.displayTaskList();
                    System.out.println("Tache Affiché");
                    
                    break;
                case 5:
                    System.out.println("Au revoir");
                    break;
                default:
                    System.out.println("Choix incorrect");
                    break;
            }
        }
        while (choiceMenu != 5);
    }
}
