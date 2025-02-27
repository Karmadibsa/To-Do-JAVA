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

        DataBaseAccess dba = DataBaseAccess.getInstance();
        DataBaseSeeder dbseeder = new DataBaseSeeder();
        dbseeder.seed();
        Server.createWebServer().start();
        var users = dba.getUsers();
        System.out.println(users);
        var tasks = dba.getTasks();
        System.out.println(tasks);

        try (ServerSocket server = new ServerSocket(8080)) {
            while (true) {
                Socket client = server.accept();
                ClientHandler clientHandler = new ClientHandler();
                clientHandler.handle(client);
            }
        }
    }
}
