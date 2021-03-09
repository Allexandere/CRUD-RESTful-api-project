package com.myproject.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "students")
public class Student {

    private static final long serialVersionUID = -1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "first_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String firstName;

    @Column(
            name = "last_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String lastName;

    @Column(
            name = "age",
            nullable = false
    )
    private Integer age;

    @Column(
            name = "average_rating",
            nullable = false,
            columnDefinition = "DECIMAL(10,2)"
    )
    private Double rating;

    @Column(
            name = "email",
            nullable = false,
            columnDefinition = "TEXT",
            unique = true
    )
    private String mail;

    public Student(String firstName,
                   String lastName,
                   Integer age,
                   Double averageRating,
                   String mail){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.rating = averageRating;
        this.mail = mail;
    }

    public Student() {}
}
