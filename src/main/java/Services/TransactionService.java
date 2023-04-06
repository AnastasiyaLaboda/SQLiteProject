package Services;

import Models.Transaction;

import java.util.Scanner;

public class TransactionService {
    public static Transaction createTransaction(int accountId, int amount) {
        Transaction transaction = new Transaction();
        transaction.setAccountId(accountId);
        transaction.setAmount(amount);
        return transaction;
    }
}
