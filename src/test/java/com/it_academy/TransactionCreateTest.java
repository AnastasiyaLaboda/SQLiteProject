package com.it_academy;

import com.it_academy.Models.Transaction;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionCreateTest {


    @ParameterizedTest
    @CsvSource({"1, 100", "2, 200", "1000, 1000"})
    void createTransactionValidInput(int accountId, int amount) {
        Transaction transaction = new Transaction();
        transaction.setAccountId(accountId);
        transaction.setAmount(amount);
        assertEquals(accountId, transaction.getAccountId());
        assertEquals(amount, transaction.getAmount());
    }
}
