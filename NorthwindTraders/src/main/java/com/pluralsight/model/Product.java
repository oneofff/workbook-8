package com.pluralsight.model;

import lombok.Data;

import java.sql.ResultSet;

@Data
public class Product {
    private int productId;
    private String productName;
    private Double unitPrice;
    private int unitsInStock;

    public Product(ResultSet resultSet) {
        try {
            this.productId = resultSet.getInt("productId");
            this.productName = resultSet.getString("productName");
            this.unitPrice = resultSet.getDouble("unitPrice");
            this.unitsInStock = resultSet.getInt("unitsInStock");
        } catch (Exception e) {
            throw new RuntimeException("Error initializing Product from ResultSet", e);
        }
    }

    public String toString() {
        return "Product Id: " + productId + "\n" +
                "Name: " + productName + "\n" +
                "Price: " + unitPrice + "\n" +
                "Stock: " + unitsInStock;
    }
}
