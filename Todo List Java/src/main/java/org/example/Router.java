package org.example;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Router {

    public void route(Socket client, Request request) throws IOException {
        switch (request.getPath()) {
            case "/users":
                handleUsersPath(client);
                break;
            case "/user":
                handleUserPath(client, request);
                break;
            case "/tasks":
                handleTasksPath(client);
                break;
            default:
                handle404(client);
                break;
        }
    }

    private void handleUsersPath(Socket client) throws IOException {
        final DataBaseAccess dba = DataBaseAccess.getInstance();
        var users = dba.getUsers();

        OutputStream outputStream = client.getOutputStream();
        sendFirstLines(outputStream, 200L);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<html><body>");
        stringBuilder.append("<p>");
        stringBuilder.append("<h1>Liste utilisateur :</h1>");
        stringBuilder.append("<ul>");
        for (var user : users) {
            stringBuilder.append("<li>");
            stringBuilder.append((user.getFirstName() + " " + user.getLastName()));
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
    }

    private void handleUserPath(Socket client, Request request) throws IOException {

        final DataBaseAccess dba = DataBaseAccess.getInstance();
        var userId = request.getParams().get("id");
        var user = dba.getUserById(Long.parseLong(userId));
        if (user == null) {
            handle404(client);
            return;
        }
        OutputStream outputStream = client.getOutputStream();
        sendFirstLines(outputStream, 200L);


        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<html><body>");
        stringBuilder.append("<h1>User</h1>");
        stringBuilder.append("<p>").append(user.getFirstName()).append("</p>");
        stringBuilder.append("<p>").append(user.getLastName()).append("</p>");
        stringBuilder.append("</body></html>");

        var content = stringBuilder.toString();
        outputStream.write(content.getBytes());

        outputStream.write("\r\n\r\n".getBytes());
        outputStream.flush();
        outputStream.close();
    }

    private void handleTasksPath(Socket client) throws IOException {
        final DataBaseAccess dba = DataBaseAccess.getInstance();
        var tasks = dba.getTasks();

        OutputStream outputStream = client.getOutputStream();
        sendFirstLines(outputStream, 200L);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<html><body>");
        stringBuilder.append("<h1>Liste taches :</h1>");
        stringBuilder.append("<ul>");
        for (var task : tasks) {
            stringBuilder.append("<li>");
            stringBuilder.append("<h2>Title :</h2>");
            stringBuilder.append(task.getTitle());
            stringBuilder.append("<h2>Description :</h2>");
            stringBuilder.append(task.getDescription());
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
    }

    private void handle404(Socket client) throws IOException {

        OutputStream outputStream = client.getOutputStream();
        sendFirstLines(outputStream, 404L);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<html><body>");
        stringBuilder.append("<h1>Error 404</h1>");
        stringBuilder.append("<h2>Pas le bon url nullos !</h2>");

        stringBuilder.append("</body></html>");

        var content = stringBuilder.toString();
        outputStream.write(content.getBytes());

        outputStream.write("\r\n\r\n".getBytes());
        outputStream.flush();
        outputStream.close();
    }

    private static void sendFirstLines(OutputStream outputStream, Long statusCode) throws IOException {
        outputStream.write(String.format("HTTP/1.1 %d\r\n", statusCode).getBytes());
        outputStream.write("Content-Type: text/html\r\n".getBytes());
        outputStream.write("\r\n".getBytes());
    }
}
