package com.vbank.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    public static Connection getConnection() {
        String url = "jdbc:postgresql://localhost:5432/fortnite_bank";
        try {
            return DriverManager.getConnection(url, System.getenv("VBANK_ROLE"), System.getenv("VBANK_PASS"));
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Unable to connect to database. Sad :(");
            return null;
        }
    }
}