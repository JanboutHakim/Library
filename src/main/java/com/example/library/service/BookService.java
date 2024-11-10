package com.example.library.service;

import com.example.library.DTO.BookDTO;
import com.example.library.exception.BookNotFoundException;
import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {
    @Autowired
    private BookRepository bookRepository;



    private ModelMapper modelMapper;
    public List<BookDTO> getAllBooks(){
        List<BookDTO> bookDTOList =new ArrayList<>();
        for(Book book:bookRepository.findAll())
            bookDTOList.add(modelMapper.map(book,BookDTO.class));
        return bookDTOList;
    }
    @Cacheable(value = "books", key = "#id")
    public BookDTO getBookById(long id){
        Book book =bookRepository.findById(id).orElse(null);
        if(book!=null)
            return modelMapper.map(book,BookDTO.class);
        else
            throw new BookNotFoundException("The book with "+id +" Not Found");

    }

    @Transactional
    public void addBook(BookDTO bookDTO){
        bookRepository.save(convertToEntity(bookDTO));
    }

    @Transactional
    @CachePut(value = "books",key = "#id" , condition = "#result != null")
    public void updateBook(BookDTO bookDTO,long id) {
        // Retrieve the existing book from the repository
        Optional<Book> existingBook = bookRepository.findById(id);

        if(existingBook.isPresent()) {
            Book book = existingBook.get();

            book.setTitle(bookDTO.getTitle());
            book.setAuthor(bookDTO.getAuthor());
            book.setPublicationDate(bookDTO.getPublicationDate());
            book.setISBN(bookDTO.getISBN());
            book.setPublicationDate(bookDTO.getPublicationDate());

            bookRepository.save(book);
        }
        else{
            throw new BookNotFoundException("The Book with"+ id+ "Not found");
        }

    }

    @Transactional
    @CacheEvict(value = "books",key = "#id")
    public void deleteBook(long id){
        // Retrieve the existing book from the repository
        Optional<Book> existingBook =bookRepository.findById(id);
        if(existingBook.isPresent())
            bookRepository.deleteById(id);
        else
            throw new BookNotFoundException("The Book with"+ id+ "Not found");
    }
    //convert the bookDTO to the original Book class
    public Book convertToEntity(BookDTO bookDTO) {
        return modelMapper.map(bookDTO, Book.class);
    }
}
