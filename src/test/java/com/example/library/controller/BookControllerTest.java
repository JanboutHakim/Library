package com.example.library.controller;

import com.example.library.DTO.BookDTO;
import com.example.library.exception.BookNotFoundException;
import com.example.library.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    private BookDTO bookDTO;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
        objectMapper = new ObjectMapper();

        bookDTO = new BookDTO(1L, "Test Author", LocalDate.now(), "123456789", "Test Book");
    }

    @Test
    void testGetAllBooks() throws Exception {
        List<BookDTO> books = Collections.singletonList(bookDTO);

        when(bookService.getAllBooks()).thenReturn(books);

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].title").value("Test Book"));

        verify(bookService, times(1)).getAllBooks();
    }

    @Test
    void testGetBookById_Success() throws Exception {
        when(bookService.getBookById(1L)).thenReturn(bookDTO);

        mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Test Book"));

        verify(bookService, times(1)).getBookById(1L);
    }

    @Test
    void testGetBookById_NotFound() throws Exception {
        when(bookService.getBookById(1L)).thenThrow(new BookNotFoundException("Book not found"));

        mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Book not found"));

        verify(bookService, times(1)).getBookById(1L);
    }

    @Test
    void testAddBook() throws Exception {
        String jsonRequest = objectMapper.writeValueAsString(bookDTO);

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk());

        verify(bookService, times(1)).addBook(bookDTO);
    }

    @Test
    void testUpdateBook() throws Exception {
        String jsonRequest = objectMapper.writeValueAsString(bookDTO);

        mockMvc.perform(put("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk());

        verify(bookService, times(1)).updateBook(bookDTO, 1L);
    }

    @Test
    void testDeleteBook_Success() throws Exception {
        mockMvc.perform(delete("/api/books/1"))
                .andExpect(status().isOk());

        verify(bookService, times(1)).deleteBook(1L);
    }

    @Test
    void testDeleteBook_NotFound() throws Exception {
        doThrow(new BookNotFoundException("Book not found")).when(bookService).deleteBook(1L);

        mockMvc.perform(delete("/api/books/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Book not found"));

        verify(bookService, times(1)).deleteBook(1L);
    }
}
