package com.library.version3.service;



import com.library.version3.entity.Book;
import com.library.version3.entity.BorrowRecord;
import com.library.version3.entity.Member;
import com.library.version3.repository.BookRepository;
import com.library.version3.repository.BorrowRecordRepository;
import com.library.version3.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BorrowRecordService {

    @Autowired
    private BorrowRecordRepository borrowRecordRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MemberRepository memberRepository;

    public BorrowRecord borrowBook(BorrowRecord record) {
        Book book = bookRepository.findById(record.getBook().getId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.getStatus() == Book.BookStatus.BORROWED) {
            throw new RuntimeException("Book is currently not available");
        }

        Member member = memberRepository.findById(record.getMember().getId())
                .orElseThrow(() -> new RuntimeException("Member not found"));

        book.setStatus(Book.BookStatus.BORROWED);
        bookRepository.save(book);

        record.setBorrowDate(LocalDate.now());
        record.setDueDate(LocalDate.now().plusDays(14)); // 2 weeks loan period
        record.setFineAmount(0.0);

        return borrowRecordRepository.save(record);
    }

    public BorrowRecord returnBook(Long recordId) {
        BorrowRecord record = borrowRecordRepository.findById(recordId)
                .orElseThrow(() -> new RuntimeException("Borrow record not found"));

        if (record.getReturnDate() != null) {
            throw new RuntimeException("Book already returned");
        }

        LocalDate today = LocalDate.now();
        record.setReturnDate(today);

        long overdueDays = ChronoUnit.DAYS.between(record.getDueDate(), today);
        if (overdueDays > 0) {
            record.setFineAmount(overdueDays * 5.0); // Rs. 5 fine per day
        } else {
            record.setFineAmount(0.0);
        }

        // Make book available again
        Book book = record.getBook();
        book.setStatus(Book.BookStatus.AVAILABLE);
        bookRepository.save(book);

        return borrowRecordRepository.save(record);
    }

    public List<BorrowRecord> getAllBorrowRecords() {
        return borrowRecordRepository.findAll();
    }

    public BorrowRecord getBorrowRecordById(Long id) {
        return borrowRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Borrow record not found"));
    }

    public void deleteBorrowRecord(Long id) {
        borrowRecordRepository.deleteById(id);
    }
}
