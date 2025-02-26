package org.example;
import org.h2.tools.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException, IOException {
        Scanner InputUser = new Scanner(System.in);
        DataBaseAccess dba = DataBaseAccess.getInstance();
        DataBaseSeeder dbseeder = new DataBaseSeeder();
        dbseeder.seed();
        Server.createWebServer().start();
        dba.addUser(new User("Axel", "Momper"));
        dba.addTask(new Task("Ranger" , "Ranger la chambre et en dessous de la chambre"));
        var users = dba.getUsers();
        System.out.println(users);
        var tasks = dba.getTasks();
        System.out.println(tasks);

        try (ServerSocket server = new ServerSocket(8080)){
            while (true) {
                Socket client = server.accept();
                BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                Request request = new Request();

                String firstLine = reader.readLine();
                String[] splitFirstLine = firstLine.split(" ");

                request.setMethod(splitFirstLine[0]);
                request.setPath(splitFirstLine[1]);
                request.setProtocol(splitFirstLine[2]);

                String line;
                while (!(line = reader.readLine()).isBlank()) {
                    var h = line.split(": ");
                    request.getHeaders().put(h[0], h[1]);
                }

                System.out.println(request);

                OutputStream outputStream = client.getOutputStream();
                outputStream.write("HTTP/1.1 200\r\n".getBytes());
                outputStream.write("Content-type: text/html\r\n".getBytes());
                outputStream.write("\r\n".getBytes());

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("<html><body>");
                stringBuilder.append("<p>");
                stringBuilder.append("<h1>Liste utilisateur :</h1>");
                stringBuilder.append("<ul>");
                for (var user : users) {
                    stringBuilder.append("<li>");
                    stringBuilder.append((user.getFirstName() +" " + user.getLastName()) );
                    stringBuilder.append("</li>");
                }
                stringBuilder.append("</ul>");
                stringBuilder.append("<h1>Liste taches :</h1>");
                stringBuilder.append("<ul>");
                for (var task : tasks) {
                    stringBuilder.append("<li>");
                    stringBuilder.append("<h2>Title :</h2>");
                    stringBuilder.append(task.getTitle());
                    stringBuilder.append("<h2>Description :</h2>");
                    stringBuilder.append(task.getDescription() );
                    stringBuilder.append("</li>");
                }
                stringBuilder.append("</ul>");
                stringBuilder.append("</p>");
                stringBuilder.append("</body></html>");

                var content = stringBuilder.toString();
                outputStream.write(content.getBytes());

                outputStream.write("\r\n\r\n".getBytes());
                outputStream.flush();
                outputStream.close();
                reader.close();
                client.close();
            }

        }

//        int choiceMenu;
//
//        do {
//            System.out.println("Que voulez vous faire ? 1.Créez une tache 2.Supprimez une tache 3.Modifiez une tache 4.Afficher les taches 5.Afficher les User 6.Quittez ");
//            choiceMenu = Integer.parseInt(InputUser.nextLine());
//            switch (choiceMenu) {
//                case 1:
//                    System.out.println("Enter your name tasks : ");
//                    String title = InputUser.nextLine();
//                    System.out.println("Enter your description tasks : ");
//                    String description = InputUser.nextLine();
//                    Task task = new Task(title, description);
//                    dba.addTask(task);
//                    break;
//                case 2:
//                    dba.displayTaskList();
//                    System.out.println("Enter number of tasks to delete : ");
//                    int choiceDelete = Integer.parseInt(InputUser.nextLine());
//                    dba.selectTaskByNumber(choiceDelete);
//                    dba.deleteTask(choiceDelete - 1);
//                    System.out.println("Tache supprimé");
//                    dba.displayTaskList();
//                    break;
//
//                case 3:
//                    dba.displayTaskList();
//                    System.out.println("Enter number of tasks to edit : ");
//                    int choiceUpdate = Integer.parseInt(InputUser.nextLine());
//                    Task taskUpdate = dba.selectTaskByNumber(choiceUpdate);
//                    System.out.println("Enter your name tasks : ");
//                    String titleUpdate = InputUser.nextLine();
//                    System.out.println("Enter your description tasks : ");
//                    String descriptionUpdate = InputUser.nextLine();
//
//                    taskUpdate.setTitle(titleUpdate);
//                    taskUpdate.setDescription(descriptionUpdate);
//
//                    System.out.println("Tache Modifié");
//
//                    dba.displayTaskList();
//                    break;
//                case 4:
//                    dba.displayTaskList();
//                    System.out.println("Tache Affiché");
//
//                    break;
//                case 5:
//                    dba.displayUserList();
//                    System.out.println("Tache Affiché");
//
//                    break;
//                case 6:
//                    System.out.println("Au revoir");
//                    break;
//                default:
//                    System.out.println("Choix incorrect");
//                    break;
//            }
//        }
//        while (choiceMenu != 6);
    }
}
