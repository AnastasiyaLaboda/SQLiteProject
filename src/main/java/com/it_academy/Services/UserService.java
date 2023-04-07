package com.it_academy.Services;

import com.it_academy.Models.User;

import java.util.Scanner;

public class UserService {
    public static User inputUser() {
        User user = new User();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter user name:");
        String userName = scanner.nextLine();
        while (userName.matches("null|\\s*")) {
            System.out.println("User name can't be null");
            userName = scanner.nextLine();
        }
        user.setName(userName);
        System.out.println("Enter user address:");
        user.setAddress(scanner.nextLine());
        return user;
    }
}
