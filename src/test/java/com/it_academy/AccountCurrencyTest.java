package com.it_academy;

import com.it_academy.Models.Account;
import com.it_academy.Services.AccountService.Currencies;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.it_academy.QueryExecutor.AccountQueryExecutor.addNewAccount;
import static org.junit.jupiter.api.Assertions.*;

public class AccountCurrencyTest {
    private static final String DATA_BASE_URL = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\src\\test\\resources\\userAccountsTestdb.db";
    private static Connection connection;

    @BeforeAll
    public static void openConnection() throws SQLException {
        connection = DriverManager.getConnection(DATA_BASE_URL);
    }

    @AfterAll
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @ParameterizedTest
    @EnumSource(Currencies.class)
    public void createAccountsInDifferentCurrenciesForOneUser(Currencies currency) {
        Account account = new Account();
        account.setUserId(1);
        account.setCurrency(currency.name());
    }

    @ParameterizedTest
    @EnumSource(Currencies.class)
    public void createAccountsInSameCurrencyForOneUserThrowException(Currencies currency) throws SQLException {
        Account account1 = new Account();
        account1.setUserId(1);
        account1.setBalance(100);
        account1.setCurrency(currency.name());
        addNewAccount(connection, account1);

        Account account2 = new Account();
        account2.setUserId(1);
        account2.setBalance(100);
        account2.setCurrency(currency.name());

        try {
            addNewAccount(connection, account2);
        } catch (SQLException e) {
            assertEquals("Failed to add new account: " + e.getMessage(), e.getMessage());
        }
    }
}


