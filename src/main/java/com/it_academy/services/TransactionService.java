package com.it_academy.services;

import com.it_academy.models.Transaction;

public class TransactionService {
    public static Transaction createTransaction(int accountId, int amount) {
        Transaction transaction = new Transaction();
        transaction.setAccountId(accountId);
        transaction.setAmount(amount);
        return transaction;
    }
}
