package com.pluralsight;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.pluralsight.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/northwind");
        dataSource.setUser("root");
        dataSource.setPassword("1111");

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM products ");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("-".repeat(20));
                Product product = new Product(resultSet);
                System.out.println(product);
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }
}
