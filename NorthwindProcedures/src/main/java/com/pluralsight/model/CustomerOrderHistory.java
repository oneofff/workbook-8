package com.pluralsight.model;

import lombok.Data;

import java.sql.ResultSet;

@Data
public class CustomerOrderHistory {

    private String productName;
    private double total;

    public CustomerOrderHistory(ResultSet resultSet) {
        try {
            this.productName = resultSet.getString("ProductName");
            this.total = resultSet.getDouble("Total");
        } catch (Exception e) {
            throw new RuntimeException("Error initializing CustomerOrderHistory from ResultSet", e);
        }
    }
}
