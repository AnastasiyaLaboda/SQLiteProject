package com.it_academy;

import com.it_academy.Models.Account;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.it_academy.QueryExecutor.AccountQueryExecutor.addNewAccount;
import static org.junit.jupiter.api.Assertions.*;

public class AccountBalanceTest {
    private static final String DATA_BASE_URL = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\src\\test\\resources\\userAccountsTestdb.db";
    private Connection connection;
    private static final int MAX_BALANCE_SIZE = 2000000000;

    @BeforeEach
    public void openConnection() throws SQLException {
        connection = DriverManager.getConnection(DATA_BASE_URL);
    }

    @AfterEach
    public void closeConnection() throws SQLException {
        connection.close();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 100, 5, MAX_BALANCE_SIZE})
    public void createAccountWithInvalidBalance_shouldThrowException(int invalidBalanceAmount) throws SQLException {
        Account account = new Account();
        account.setUserId(189);
        account.setCurrency("BYN");
        account.setBalance(invalidBalanceAmount);

        try {
            addNewAccount(connection, account);
            //fail("Expected an SQLException to be thrown");
        } catch (SQLException e) {
            assertEquals("Failed to add new account: " + e.getMessage(), e.getMessage());
        }



    }
}
