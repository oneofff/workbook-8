package com.pluralsight.dao;

import javax.sql.DataSource;

public class MySqlConfig {
    public static final String URL = "jdbc:mysql://localhost:3306/northwind";
    public static final String USER = "root";
    public static final String PASSWORD = "1111";
    public static DataSource DATA_SOURCE;

    private MySqlConfig() {
        // Private constructor to prevent instantiation
    }

    public static DataSource getDataSource() {
        if (DATA_SOURCE == null) {
            DATA_SOURCE = createDataSource();
        }
        return DATA_SOURCE;
    }

    private static DataSource createDataSource() {
        com.mysql.cj.jdbc.MysqlDataSource dataSource = new com.mysql.cj.jdbc.MysqlDataSource();
        dataSource.setUrl(URL);
        dataSource.setUser(USER);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }
}
