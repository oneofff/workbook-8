package com.pluralsight.model;

import lombok.Data;

import java.sql.ResultSet;

@Data
public class SalesByCategory {
    private String productName;
    private double totalPurchase;

    public SalesByCategory(ResultSet resultSet) {
        try {
            this.productName = resultSet.getString("ProductName");
            this.totalPurchase = resultSet.getDouble("TotalPurchase");
        } catch (Exception e) {
            throw new RuntimeException("Error initializing SalesByCategory from ResultSet", e);
        }
    }

    public String toString() {
        return String.format("Product: %s, Total Price: %.2f", productName, totalPurchase);
    }
}
