package com.example.demo;

public class DBConnector {

    private final com.example.demo.config.H2Config config;

    public DBConnector(com.example.demo.config.H2Config config) {
        this.config = config;
    }

    public java.sql.Connection getConnection() throws java.sql.SQLException, ClassNotFoundException {
//        Class.forName("org.h2.Driver");
        java.sql.Connection connection = java.sql.DriverManager.getConnection(config.url(), config.login(), config.password());
        return connection;
    }
}
