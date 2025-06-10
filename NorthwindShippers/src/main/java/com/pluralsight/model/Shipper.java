package com.pluralsight.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;

@Data
@NoArgsConstructor
public class Shipper {
    private int id;
    private String companyName;
    private String phone;

    public Shipper(ResultSet resultSet) {
        try {
            this.id = resultSet.getInt("ShipperId");
            this.companyName = resultSet.getString("CompanyName");
            this.phone = resultSet.getString("Phone");
        } catch (Exception e) {
            throw new RuntimeException("Error initializing Shipper from ResultSet", e);
        }
    }

    public String toString() {
        return "Shipper Id: " + id + "\n" +
               "Company Name: " + companyName + "\n" +
               "Phone: " + phone;
    }
}
