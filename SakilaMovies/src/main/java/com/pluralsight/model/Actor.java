package com.pluralsight.model;

import lombok.Data;

import java.sql.ResultSet;

@Data
public class Actor {
    private int actorId;
    private String firstName;
    private String lastName;

    public Actor(ResultSet resultSet) {
        try {
            this.actorId = resultSet.getInt("actor_id");
            this.firstName = resultSet.getString("first_name");
            this.lastName = resultSet.getString("last_name");
        } catch (Exception e) {
            System.out.println("Error creating Actor from ResultSet: " + e.getMessage());
        }
    }

    public String toString() {
        return actorId + ": " + firstName + " " + lastName;
    }
}
