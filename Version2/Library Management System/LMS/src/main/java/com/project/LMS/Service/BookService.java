package com.project.LMS.Service;

import com.project.LMS.Entity.Book;
import com.project.LMS.Entity.User;
import com.project.LMS.Repository.BookRepository;
import com.project.LMS.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Autowired
    public BookService(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    // Get all books
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    // Get a book by ID
    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    // Save a new or existing book
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    // Delete a book by ID
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    // Update an existing book
    public Book updateBook(Long id, Book updatedBook) {
        Book existingBook = findById(id);
        if (existingBook != null) {
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setBorrowed(updatedBook.isBorrowed());
            existingBook.setBorrowedBy(updatedBook.getBorrowedBy());
            return save(existingBook);
        }
        return null;
    }

    // Borrow a book by a user
    public Book borrowBook(Long bookId, Long userId) {
        Book book = findById(bookId);
        User user = userRepository.findById(userId).orElse(null);

        if (book != null && !book.isBorrowed() && user != null) {
            book.setBorrowed(true);
            book.setBorrowedBy(user);
            return save(book);
        }
        return null;
    }

    // Return a borrowed book
    public Book returnBook(Long bookId) {
        Book book = findById(bookId);
        if (book != null && book.isBorrowed()) {
            book.setBorrowed(false);
            book.setBorrowedBy(null);
            return save(book);
        }
        return null;
    }
}
