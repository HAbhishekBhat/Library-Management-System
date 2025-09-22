package com.library.version3.controller;

import com.library.version3.entity.BorrowRecord;
import com.library.version3.service.BorrowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrowRecords")
public class BorrowRecordController {

    @Autowired
    private BorrowRecordService borrowRecordService;

    // Borrow a book
    @PostMapping
    public ResponseEntity<BorrowRecord> borrowBook(@RequestBody BorrowRecord borrowRecord) {
        BorrowRecord savedRecord = borrowRecordService.borrowBook(borrowRecord);
        return ResponseEntity.ok(savedRecord);
    }

    // Get all borrow records
    @GetMapping
    public ResponseEntity<List<BorrowRecord>> getAllBorrowRecords() {
        List<BorrowRecord> borrowRecords = borrowRecordService.getAllBorrowRecords();
        return ResponseEntity.ok(borrowRecords);
    }

    // Get a borrow record by ID
    @GetMapping("/{id}")
    public ResponseEntity<BorrowRecord> getBorrowRecordById(@PathVariable Long id) {
        BorrowRecord borrowRecord = borrowRecordService.getBorrowRecordById(id);
        return ResponseEntity.ok(borrowRecord);
    }

    // Return a book and calculate fine if applicable
    @PutMapping("/{id}/return")
    public ResponseEntity<BorrowRecord> returnBook(@PathVariable Long id) {
        BorrowRecord returnedRecord = borrowRecordService.returnBook(id);
        return ResponseEntity.ok(returnedRecord);
    }

    // Delete a borrow record
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBorrowRecord(@PathVariable Long id) {
        borrowRecordService.deleteBorrowRecord(id);
        return ResponseEntity.noContent().build();
    }
}
