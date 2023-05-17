package com.it_academy;

public class DBDriverManager {
    private static final String JDBC_DRIVER = "org.sqlite.JDBC";

    public static boolean isDriverExists() {
        try {
            Class.forName(JDBC_DRIVER);
            return true;
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver was not found");
            return false;
        }
    }
}
