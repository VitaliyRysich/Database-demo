package com.rysich.vitalii.databasedemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Review {
    @Id
    @GeneratedValue
    private Long id;

    private String rating;

    private String description;

    protected Review() {
    }

    public Review(String name) {
        this.description = name;
    }

    public Long getId() {
        return id;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String name) {
        this.description = name;
    }

    @Override
    public String toString() {
        return String.format("Review[%s]", description);
    }
}
