package com.example.library.controller;

import com.example.library.exception.BookNotFoundException;
import com.example.library.exception.BorrowedBookNotFoundException;
import com.example.library.exception.PatronNotFoundException;
import com.example.library.service.BorrowService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class BorrowController {
    private BorrowService borrowService;

    @PostMapping("/api/borrow/{bookId}/patron/{patronId}")
    private ResponseEntity<String> handleBorrowingBook(@PathVariable("bookId") long bookId,@PathVariable("patronId") long patronId){
        borrowService.borrowBook(patronId,bookId);
        return ResponseEntity.status(HttpStatus.OK).body("The book borrowed successfully");
    }

    @PutMapping("/api/return/{bookId}/patron/{patronId}")
    private ResponseEntity<String> handleReturnBook(@PathVariable("bookId") long bookId,@PathVariable("patronId") long patronId){
        borrowService.returnBook(patronId,bookId);
        System.out.println("ok"+patronId+"  "+ bookId);
        return ResponseEntity.status(HttpStatus.OK).body("The book returned successfully");
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<String> handleBookNotFound(BookNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @ExceptionHandler(PatronNotFoundException.class)
    public ResponseEntity<String> handleBookNotFound(PatronNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @ExceptionHandler(BorrowedBookNotFoundException.class)
    public ResponseEntity<String> handleBorrowedBookNotFound(BorrowedBookNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
