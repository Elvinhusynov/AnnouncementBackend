package com.huseynov.announcementbackend.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    public static Connection getConnection () throws SQLException {
        String url = "jdbc:mysql://localhost:3306/announcement_backend";
        String username = "root";
        String password = "5301";

        return DriverManager.getConnection(url, username , password);
    }
}

