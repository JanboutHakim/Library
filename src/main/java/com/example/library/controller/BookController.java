package com.example.library.controller;

import com.example.library.DTO.BookDTO;
import com.example.library.exception.BookNotFoundException;
import com.example.library.model.Book;
import com.example.library.service.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("")
    private ResponseEntity<List<BookDTO>> getAllBooks(){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks());
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable("id") Long id) {
      return ResponseEntity.status(HttpStatus.OK).body(bookService.getBookById(id));

    }

    @PostMapping("")
    public ResponseEntity<String> handleAddBook(@Valid @RequestBody BookDTO bookDTO) {
        bookService.addBook(bookDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Book added successfully");
    }

    @PutMapping("/{id}")
    private ResponseEntity<String> handleUpdatingBook(@PathVariable("id") long id,@Valid @RequestBody BookDTO bookDTO){
        bookService.updateBook(bookDTO,id);
        return ResponseEntity.status((HttpStatus.OK)).body("Book updated successfully");
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<String> handleDeletingBook(@PathVariable("id") long id){
        bookService.deleteBook(id);
        return ResponseEntity.status((HttpStatus.OK)).body("Book Deleted successfully");
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<String> handleBookNotFound(BookNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
