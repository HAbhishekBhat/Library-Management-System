


Create Database
---------------
CREATE DATABASE lms;
USE lms;

Create Tables
-------------

-- Book Table
CREATE TABLE book (
    id BIGINT PRIMARY KEY,
    title VARCHAR(255),
    author VARCHAR(255),
    genre VARCHAR(100),
    published_year INT
);

-- Member Table
CREATE TABLE member (
    id BIGINT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100),
    membership_date DATE
);

-- BorrowRecord Table
CREATE TABLE borrow_record (
    id BIGINT PRIMARY KEY,
    book_id BIGINT,
    member_id BIGINT,
    borrow_date DATE,
    due_date DATE,
    return_date DATE,
    fine_amount DOUBLE,
    FOREIGN KEY (book_id) REFERENCES book(id),
    FOREIGN KEY (member_id) REFERENCES member(id)
);

Insert Records
--------------

-- Books (Programming Books)
INSERT INTO book (id, title, author, genre, published_year) VALUES
(1, 'Effective Java', 'Joshua Bloch', 'Programming', 2018),
(2, 'Clean Code', 'Robert C. Martin', 'Programming', 2008),
(3, 'Java: The Complete Reference', 'Herbert Schildt', 'Programming', 2021);

-- Members
INSERT INTO member (id, name, email, membership_date) VALUES
(1, 'Alice Johnson', 'alice.johnson@example.com', '2024-01-10'),
(2, 'Bob Smith', 'bob.smith@example.com', '2024-02-14'),
(3, 'Catherine Lee', 'catherine.lee@example.com', '2024-03-01'),
(4, 'David Kim', 'david.kim@example.com', '2024-04-12');

-- Borrow Records
INSERT INTO borrow_record (id, book_id, member_id, borrow_date, due_date, return_date, fine_amount) VALUES
(1, 1, 1, '2024-03-01', '2024-03-15', '2024-03-16', 10.0),
(2, 2, 2, '2024-03-05', '2024-03-19', '2024-03-18', 0.0),
(3, 3, 3, '2024-04-01', '2024-04-15', NULL, 0.0),
(4, 1, 4, '2024-04-20', '2024-05-04', NULL, 0.0);
