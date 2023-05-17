package com.it_academy.query_executor;

import com.it_academy.models.User;

import java.sql.*;

public class UserQueryExecutor {
    public static void printUserTable(Connection connection) {
        String selectAllUsersQuery = "SELECT * FROM Users;";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectAllUsersQuery)) {
            while (resultSet.next()) {
                String userInfo = "UserId: " + resultSet.getInt("UserId") +
                        "\tName: " + resultSet.getString("name") +
                        "\t\tAddress: " + resultSet.getString("address");
                System.out.println(userInfo);
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while receiving Users information: " + e.getMessage());
        }
    }

    public static void registerNewUser(Connection connection, User user) {
        String registerUserQuery = "INSERT INTO Users (name, address) VALUES(?,?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(registerUserQuery);) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getAddress());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Failed to register new user: " + e.getMessage());
        }
    }

    public static void deleteUser(Connection connection, int idToDelete) {
        String deleteUserQuery = "DELETE FROM Users WHERE userId=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteUserQuery)) {
            preparedStatement.setInt(1, idToDelete);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to delete the user: " + e.getMessage());
        }
    }
}


