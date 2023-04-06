package Services;

import Models.Account;

import java.util.Scanner;

public class AccountService {

    public static Account inputAccount() {
        Account account = new Account();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the following account details:");
        System.out.println("User Id:");
        account.setUserId(Integer.parseInt(scanner.nextLine()));
        System.out.println("Account balance:");
        account.setBalance(Integer.parseInt(scanner.nextLine()));
        System.out.println("Account currency:");
        String currency = scanner.nextLine().toUpperCase();
        while (!isValidCurrency(currency)) {
            System.out.println("Invalid currency input, try again.");
            currency = scanner.nextLine().toUpperCase();
        }
        account.setCurrency(currency);
        return account;
    }

    enum Currencies {
        BYN,
        USD,
        EUR,
        CHF,
        CNY
    }

    private static boolean isValidCurrency(String currency) {
        try {
            Currencies.valueOf(currency);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

}
