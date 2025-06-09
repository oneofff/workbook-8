package com.pluralsight;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/northwind");
        dataSource.setUser("root");
        dataSource.setPassword("1111");

        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            String getAllProductsQuery = "SELECT * FROM products";
            statement.execute(getAllProductsQuery);
            while (statement.getResultSet().next()) {
                System.out.println("-".repeat(20));
                System.out.println(statement.getResultSet().getString("productName"));
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }
}
