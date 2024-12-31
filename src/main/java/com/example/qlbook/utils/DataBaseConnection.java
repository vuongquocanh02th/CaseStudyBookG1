package com.example.qlbook.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static String jdbcURL = "jdbc:mysql://localhost:3306/quanlysach?useSSL=false";
    private static String jdbcUsername = "root";
    private static String jdbcPassword = "Phua11@pxa";
    private static Connection connection;

    private DataBaseConnection (){};

        public static Connection getConnection() {
            if (connection == null) {
                try {
                    connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException("Error connecting to the database", e);
                }
            }
            return connection;
        }
}
