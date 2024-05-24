package com.example.demo.config;

public class H2Config implements DbConfig {
    @Override
    public String url() {
        return "jdbc:h2:file:./DB";
    }

    @Override
    public String login() {
        return "sa";
    }

    @Override
    public String password() {
        return "sa";
    }

    @Override
    public String port() {
        return "";
    }
}
