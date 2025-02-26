package org.example;

import java.sql.*;
import java.util.*;

public class DataBaseAccess {

    private static final String CREATE_TABLE_USERS = "CREATE TABLE USERS (id bigint auto_increment primary key, firstName varchar(255), lastName varchar(255));";
    private static final String CREATE_TABLE_TASKS = "CREATE TABLE TASKS (id bigint auto_increment primary key, title varchar(255), description varchar(MAX), done boolean, creator_id bigint );";
    private static final String GET_USERS = "SELECT * FROM USERS ;";
    private static final String CREATE_USER = "INSERT INTO USERS (firstName, lastName) VALUES (?, ?);";
    private static final String GET_TASKS = "SELECT * FROM TASKS ;";
    private static final String CREATE_TASKS = "INSERT INTO TASKS (title, description) VALUES (?, ?);";



    private static DataBaseAccess instance;
    private Connection connection;

    private DataBaseAccess() {
        try{
            connection = DriverManager.getConnection("jdbc:h2:mem:db1");
            createTables();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    private void createTables() throws SQLException {
        connection.createStatement().executeUpdate(CREATE_TABLE_USERS);
        connection.createStatement().executeUpdate(CREATE_TABLE_TASKS);
    }

    public static DataBaseAccess getInstance() {
        if (instance == null) {
            instance = new DataBaseAccess();
        }
        return instance;
    }

    public List<User> getUsers() {
            List<User> users = new ArrayList<>();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(GET_USERS);
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                User user = new User(id, firstName, lastName);
                users.add(user);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return users;
    }

    public List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();
        try{
            ResultSet rs = connection.createStatement().executeQuery(GET_TASKS);
            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getLong(1));
                task.setTitle(rs.getString(2));
                task.setDescription(rs.getString(3));
                task.setDone(rs.getBoolean(4));
                tasks.add(task);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return tasks;
    }

    public void addUser(User user) {
        try{
                PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER);
                preparedStatement.setString(1, user.getFirstName());
                preparedStatement.setString(2, user.getLastName());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
    }

    public void addTask(Task task) {
        try{
        PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TASKS);
        preparedStatement.setString(1, task.getTitle());
        preparedStatement.setString(2, task.getDescription());
        preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
    }



}
