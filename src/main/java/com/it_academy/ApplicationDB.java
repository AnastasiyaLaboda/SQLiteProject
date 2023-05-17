package com.it_academy;

import com.it_academy.services.AccountService;
import com.it_academy.services.TransactionService;
import com.it_academy.services.UserService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import static com.it_academy.DBDriverManager.isDriverExists;
import static com.it_academy.query_executor.AccountQueryExecutor.*;
import static com.it_academy.query_executor.TransactionQueryExecutor.*;
import static com.it_academy.query_executor.UserQueryExecutor.*;

public class ApplicationDB {
    private static final String DATA_BASE_URL = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\src\\main\\resources\\userAccountsdb.db";

    public static void main(String[] args) {

        if (isDriverExists()) {
            try (Connection connection = DriverManager.getConnection(DATA_BASE_URL)) {
                String action;
                do {
                    printMenuToChooseOption();
                    action = new Scanner(System.in).nextLine();
                    switch (action) {
                        case "1":
                            printUserTable(connection);
                            break;
                        case "2":
                            registerNewUser(connection, UserService.inputUser());
                            break;
                        case "3":
                            int idToDelete = getIdToDelete();
                            deleteUser(connection, idToDelete);
                            break;
                        case "4":
                            printAccountTable(connection);
                            break;
                        case "5":
                            addNewAccount(connection, AccountService.inputAccount());
                            break;
                        case "6":
                            printTransactionTable(connection);
                            break;
                        case "7":
                            int accountIdForUpdate = getAccountIdToChangeBalance();
                            int amount = getAmountToChangeBalance();
                            if (updateAccountBalance(connection, accountIdForUpdate, amount)) {
                                insertNewTransaction(connection, amount, TransactionService.createTransaction(accountIdForUpdate, amount));
                            } else {
                                System.out.println("You can't do this transaction");
                            }
                            break;
                    }
                }
                while (!action.trim().equalsIgnoreCase("exit"));
            } catch (SQLException e) {
                System.out.println("You can not establish the database connection");
            }
        }
    }

    private static int getIdToDelete() {
        System.out.println("Enter id to delete");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

    private static int getAccountIdToChangeBalance() {
        System.out.println("Enter Account ID to Increase(+) or to Reduce(-) Account balance");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

    private static int getAmountToChangeBalance() {
        System.out.println("Enter amount to change Account balance");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

    public static void printMenuToChooseOption() {
        System.out.println("\nEnter number to select an option:");
        System.out.println("""
                1 - View all Users
                2 - Register a new User
                3 - Delete a User
                4 - See all Users Accounts
                5 - Add a new User Account
                6 - See all Users Transactions
                7 - Increase or Reduce Account balance
                Type 'EXIT' to quit.
                """);
    }
}
