package com.library.version3.repository;



import com.library.version3.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByStatus(Book.BookStatus status);

    List<Book> findByTitleContainingIgnoreCase(String title);
}
