package com.pluralsight.model;

import lombok.Data;

import java.sql.ResultSet;


@Data
public class Customer {
    private String contactName;
    private String companyName;
    private String city;
    private String country;
    private String phoneNumber;

    public Customer(ResultSet resultSet){
        try {
            this.contactName = resultSet.getString("ContactName");
            this.companyName = resultSet.getString("CompanyName");
            this.city = resultSet.getString("City");
            this.country = resultSet.getString("Country");
            this.phoneNumber = resultSet.getString("Phone");
        } catch (Exception e) {
            throw new RuntimeException("Error initializing Customer from ResultSet", e);
        }
    }

    public String toString() {
        return "Contact Name: " + contactName + "\n" +
               "Company Name: " + companyName + "\n" +
               "City: " + city + "\n" +
               "Country: " + country + "\n" +
               "Phone Number: " + phoneNumber;
    }
}
