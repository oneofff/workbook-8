package com.pluralsight.model;

import lombok.Data;

import java.sql.ResultSet;

@Data
public class Film {
    private int filmId;
    private String title;
    private String description;
    private int releaseYear;
    private int length;

    public Film(ResultSet resultSet) {
        try {
            this.filmId = resultSet.getInt("film_id");
            this.title = resultSet.getString("title");
            this.description = resultSet.getString("description");
            this.releaseYear = resultSet.getInt("release_year");
            this.length = resultSet.getInt("length");
        } catch (Exception e) {
            System.out.println("Error creating Film from ResultSet: " + e.getMessage());
        }
    }

    public String toString() {
        return filmId + ": " + title + " (" + releaseYear + ") - " + length + " minutes\n" +
               "Description: " + description;
    }
}
