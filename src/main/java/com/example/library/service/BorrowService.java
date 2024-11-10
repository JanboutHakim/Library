package com.example.library.service;

import com.example.library.DTO.BookDTO;
import com.example.library.DTO.PatronDTO;
import com.example.library.exception.BookNotFoundException;
import com.example.library.exception.BorrowedBookNotFoundException;
import com.example.library.exception.PatronNotFoundException;
import com.example.library.model.Book;
import com.example.library.model.BorrowingRecords;
import com.example.library.model.Patron;
import com.example.library.repository.BorrowRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BorrowService {

    @Autowired
    private BorrowRepository  borrowRepository;
    private PatronService patronService;
    private BookService bookService;

    private ModelMapper modelMapper;
    public void borrowBook(long patronID, long bookID) {
        BorrowingRecords borrowingRecords = new BorrowingRecords();

        // Check if the book exists
        BookDTO bookDTO = bookService.getBookById(bookID);
        if (bookDTO == null) {
            throw new BookNotFoundException("Book not found with ID: " + bookID);
        }

        // Check if the patron exists
        PatronDTO patronDTO = patronService.getPatronById(patronID);
        if (patronDTO == null) {
            throw new PatronNotFoundException("Patron not found with ID: " + patronID);
        }
        List<BorrowingRecords> existingBorrowRecords = borrowRepository.findByIdPatronIDAndBookID(bookID,patronID);
        if(existingBorrowRecords.isEmpty()) {
            borrowingRecords.setBook(modelMapper.map(bookDTO, Book.class));
            borrowingRecords.setPatron(modelMapper.map(patronDTO, Patron.class));
            borrowingRecords.setBorrowingDate(LocalDate.now());

            borrowRepository.save(borrowingRecords);
        }else {
            throw new BookNotFoundException("The Book Already Borrowed");
        }


    }

    @Transactional
    public void returnBook(long patronID,long bookID){
        List<BorrowingRecords> existingBorrowRecords = borrowRepository.findByIdPatronIDAndBookID(bookID,patronID);

      if(!existingBorrowRecords.isEmpty())
          borrowRepository.returnBook(LocalDate.now(),bookID,patronID);
      else{
          throw new BorrowedBookNotFoundException("the book is not borrowed");
      }
    }
}
