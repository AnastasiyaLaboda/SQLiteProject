package com.it_academy.query_executor;

import com.it_academy.models.Account;

import java.sql.*;

public class AccountQueryExecutor {
    public static void printAccountTable(Connection connection) {
        String selectAllAccountsQuery = "SELECT * FROM Accounts;";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectAllAccountsQuery)) {
            while (resultSet.next()) {
                String accountInfo = "AccountId: " + resultSet.getInt("accountId")
                        + "\tUserId: " + resultSet.getInt("userId")
                        + "\tBalance: " + resultSet.getInt("balance")
                        + "\tCurrency: " + resultSet.getString("currency");
                System.out.println(accountInfo);
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while receiving Accounts information: " + e.getMessage());
        }
    }

    public static void addNewAccount(Connection connection, Account account) {
        String addNewAccountQuery = "INSERT INTO Accounts (userId, balance, currency) VALUES(?,?,?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(addNewAccountQuery);) {
            preparedStatement.setInt(1, account.getUserId());
            preparedStatement.setInt(2, account.getBalance());
            preparedStatement.setString(3, account.getCurrency());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Failed to add new account: " + e.getMessage());
            //throw new SQLException("Failed to add new account: " + e.getMessage());
        }
    }

    public static boolean updateAccountBalance(Connection connection, int accountIdForUpdate, int amount) {
        int currentBalance;
        String findCurrentAccountBalanceQuery = "SELECT balance FROM Accounts WHERE accountId=?;";
        String updateAccountBalanceQuery = "UPDATE Accounts SET balance=(balance+?) WHERE accountId=?;";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(findCurrentAccountBalanceQuery);
             PreparedStatement preparedStatement = connection.prepareStatement(updateAccountBalanceQuery)) {
            currentBalance = resultSet.getInt("balance");
            preparedStatement.setInt(1, currentBalance + amount);
            preparedStatement.setInt(2, accountIdForUpdate);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Failed to update the account: " + e.getMessage());
            return false;
        }
    }
}
