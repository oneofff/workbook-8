package com.pluralsight.model;

import lombok.Data;

import java.sql.ResultSet;
import java.time.LocalDate;

@Data
public class SalesByYear {
    private LocalDate shippedDate;
    private int orderId;
    private double subtotal;
    private LocalDate year;

    public SalesByYear(ResultSet resultSet) {
        try {
            this.shippedDate = resultSet.getDate("ShippedDate").toLocalDate();
            this.orderId = resultSet.getInt("OrderID");
            this.subtotal = resultSet.getDouble("Subtotal");
            this.year = resultSet.getDate("Year").toLocalDate();
        } catch (Exception e) {
            throw new RuntimeException("Error initializing SalesByYear from ResultSet", e);
        }
    }

    public String toString() {
        return String.format("shippedDate: %s, orderId: %d, subtotal: %.2f, year: %s",
                shippedDate, orderId, subtotal, year);
    }
}
