package com.project.LMS.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;

    private boolean borrowed; // Track if the book is borrowed

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User borrowedBy; // User who borrowed the book

    // Lombok @Getter and @Setter will generate the getters and setters for the fields.
    // So you don't need to explicitly define isBorrowed() and setBorrowed() here.
}
