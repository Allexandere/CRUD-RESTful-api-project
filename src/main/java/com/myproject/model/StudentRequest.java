package com.myproject.model;

import lombok.Data;

@Data
public class StudentRequest {
    String name;
    String lastName;
    Integer minAge;
    Integer maxAge;
    Double minRating;
    Double maxRating;
    String mail;

    public StudentRequest(String name,
                          String lastName,
                          Integer minAge,
                          Integer maxAge,
                          Double minRating,
                          Double maxRating,
                          String mail)
    {
        this.name = name;
        this.lastName = lastName;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.minRating = minRating;
        this.maxRating = maxRating;
        this.mail = mail;
    }

    public StudentRequest(){}
}
