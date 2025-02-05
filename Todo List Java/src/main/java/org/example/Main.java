package org.example;

import java.util.Scanner;

public class Main {

    public static void main (String[] args) {
        Scanner InputUser = new Scanner(System.in);

        System.out.println("Enter your name : ");
        User user = new User(InputUser.nextLine());
        System.out.println("Nom" + user.getName());
        System.out.println("Id : " + user.getId());

        System.out.println("Enter your tasks : ");
        Tasks task = new Tasks(InputUser.nextLine());
        System.out.println("Nom" + task.getName());
        System.out.println("Id : " + task.getId());
}
}
