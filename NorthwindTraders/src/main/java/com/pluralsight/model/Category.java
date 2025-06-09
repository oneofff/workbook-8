package com.pluralsight.model;

import lombok.Data;

import java.sql.ResultSet;

@Data
public class Category {
    private int categoryId;
    private String categoryName;

    public Category(ResultSet resultSet) {
        try {
            this.categoryId = resultSet.getInt("CategoryId");
            this.categoryName = resultSet.getString("CategoryName");
        } catch (Exception e) {
            throw new RuntimeException("Error initializing Category from ResultSet", e);
        }
    }

    public String toString() {
        return "Category Id: " + categoryId + "\n" +
               "Category Name: " + categoryName;
    }
}
