package com.pluralsight.dao;

import com.pluralsight.model.Category;
import com.pluralsight.model.Customer;
import com.pluralsight.model.Product;

import javax.sql.DataSource;
import java.util.LinkedList;
import java.util.List;

public class ObjectsDao {
    private final DataSource dataSource = MySqlConfig.getDataSource();

    public List<Product> getAllProducts() {
        try (var connection = dataSource.getConnection();
             var preparedStatement = connection.prepareStatement("SELECT * FROM products");
             var resultSet = preparedStatement.executeQuery()) {

            List<Product> products = new LinkedList<>();
            while (resultSet.next()) {
                Product product = new Product(resultSet);
                products.add(product);
            }
            return products;
        } catch (Exception e) {
            throw new RuntimeException("Error fetching products from database", e);
        }
    }

    public List<Customer> getAllCustomers() {
        try (var connection = dataSource.getConnection();
             var preparedStatement = connection.prepareStatement("SELECT * FROM customers ORDER BY Country");
             var resultSet = preparedStatement.executeQuery()) {

            List<Customer> customers = new LinkedList<>();
            while (resultSet.next()) {
                Customer customer = new Customer(resultSet);
                customers.add(customer);
            }
            return customers;
        } catch (Exception e) {
            throw new RuntimeException("Error fetching customers from database", e);
        }
    }

    public List<Category> getAllCategories() {
        try (var connection = dataSource.getConnection();
             var preparedStatement = connection.prepareStatement("SELECT * FROM categories ORDER BY CategoryId");
             var resultSet = preparedStatement.executeQuery()) {

            List<Category> categories = new LinkedList<>();
            while (resultSet.next()) {
                Category category = new Category(resultSet);
                categories.add(category);
            }
            return categories;
        } catch (Exception e) {
            throw new RuntimeException("Error fetching categories from database", e);
        }
    }

    public List<Product> getProductsByCategory(int categoryOption) {
        try (var connection = dataSource.getConnection();
             var preparedStatement = connection.prepareStatement("SELECT * FROM products WHERE CategoryId = ?")) {
            preparedStatement.setInt(1, categoryOption);
            try (var resultSet = preparedStatement.executeQuery()) {
                List<Product> products = new LinkedList<>();
                while (resultSet.next()) {
                    Product product = new Product(resultSet);
                    products.add(product);
                }
                return products;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error fetching products by category from database", e);
        }
    }
}
