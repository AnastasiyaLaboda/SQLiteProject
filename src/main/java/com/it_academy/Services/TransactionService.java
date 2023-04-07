package com.it_academy.Services;

import com.it_academy.Models.Transaction;

public class TransactionService {
    public static Transaction createTransaction(int accountId, int amount) {
        Transaction transaction = new Transaction();
        transaction.setAccountId(accountId);
        transaction.setAmount(amount);
        return transaction;
    }
}
