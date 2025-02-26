package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.example.Main.DESC;
import static org.example.Main.ID;
import static org.example.Main.NAME;
import static org.example.Main.STATUS;

public class Storage {

    static final String TASKS = "tasks";

    Connection connection;

    Storage() throws Exception {
        String connectionUrl = "jdbc:h2:./data/todoDB";
        String userName = "";
        String password = "";
        connection = DriverManager.getConnection(connectionUrl, userName, password);
        connection.createStatement().execute(
                "CREATE TABLE IF NOT EXISTS " + TASKS + " (" +
                        ID + " INT AUTO_INCREMENT PRIMARY KEY, " +
                        NAME + " VARCHAR(60) NOT NULL DEFAULT '', " +
                        DESC + " VARCHAR(255) NOT NULL DEFAULT '', " +
                        STATUS + " VARCHAR(32) NOT NULL DEFAULT '')"
        );
    }

    List<Map<String, String>> getAll() throws Exception {
        List<Map<String, String>> allTasks = new ArrayList<>();
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM " + TASKS);
        while (resultSet.next()) {
            Map<String, String> task = new HashMap<>();
            task.put(ID, resultSet.getString(ID));
            task.put(NAME, resultSet.getString(NAME));
            task.put(DESC, resultSet.getString(DESC));
            task.put(STATUS, resultSet.getString(STATUS));
            allTasks.add(task);
        }
        return allTasks;
    }

    void addNew(Map<String, String> task) throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO " + TASKS + " (" +
                        NAME + ", " +
                        DESC + ", " +
                        STATUS + ")" +
                        "VALUES (?, ?, ?)"
        );
        preparedStatement.setString(1, task.get(NAME));
        preparedStatement.setString(2, task.get(DESC));
        preparedStatement.setString(3, task.get(STATUS));
        preparedStatement.execute();
    }

    void changeStatus(int id, String newStatus) throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE " + TASKS + " SET " + STATUS + " = ? WHERE " + ID + " = ?"
        );

        preparedStatement.setString(1, newStatus);
        preparedStatement.setInt(2, id);
        preparedStatement.execute();
    }
}
