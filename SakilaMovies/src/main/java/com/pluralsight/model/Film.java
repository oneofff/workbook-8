package com.pluralsight.model;

import lombok.Data;

@Data
public class Film {
    private int filmId;
    private String title;
    private String description;
    private int releaseYear;
    private int length;
}
