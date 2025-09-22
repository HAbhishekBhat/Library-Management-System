package com.library.version3.entity;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String genre;
    private int publishedYear;

    @Enumerated(EnumType.STRING)
    private BookStatus status = BookStatus.AVAILABLE;

    public enum BookStatus {
        AVAILABLE,
        BORROWED
    }
}
