package com.store.dao;

import com.store.database.Database;
import com.store.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {

    public User authenticate(String username, String password) {

        String sql = """
                SELECT id, username, password, role
                FROM users
                WHERE username = ? AND password = ?
                """;

        try (Connection connection = Database.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                return  new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("role")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
