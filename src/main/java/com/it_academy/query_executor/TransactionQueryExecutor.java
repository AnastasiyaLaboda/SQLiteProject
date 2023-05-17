package com.it_academy.query_executor;

import com.it_academy.models.Transaction;

import java.sql.*;

public class TransactionQueryExecutor {
    public static void printTransactionTable(Connection connection) {
        String selectAllTransactionsQuery = "SELECT * FROM Transactions;";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectAllTransactionsQuery)) {
            while (resultSet.next()) {
                String transactionInfo = "Transaction_Id: " + resultSet.getInt("transactionId") +
                        "\tAccount_Id: " + resultSet.getInt("accountId") +
                        "\tAmount: " + resultSet.getString("amount");
                System.out.println(transactionInfo);
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while receiving Transactions information: " + e.getMessage());
        }
    }

    public static void insertNewTransaction(Connection connection, int amount, Transaction transaction) {
        String insertTransactionQuery = "INSERT INTO Transactions(accountId, amount) VALUES(?,?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertTransactionQuery);) {
            preparedStatement.setInt(1, transaction.getAccountId());
            preparedStatement.setInt(2, amount);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Failed to insert new Transaction: " + e.getMessage());
            //throw new SQLException("Failed to insert new Transaction: " + e.getMessage());
        }
    }
}
