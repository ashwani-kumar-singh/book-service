package com.jpop.bookservice.controller;

import com.jpop.bookservice.model.request.BookRequest;
import com.jpop.bookservice.model.response.BookResponse;
import com.jpop.bookservice.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RefreshScope
@RequestMapping("v1/")
@Slf4j
public class BookController {

    @Autowired
    private BookService bookService;

    @Value("${user.role : Default Role General}")
    private String role;

    @GetMapping("books/{book_id}")
    public ResponseEntity<BookResponse> getBookDetails(
            @PathVariable(value = "book_id") Integer bookId) {
        BookResponse getBookServiceResponse = bookService.getBookDetails(bookId);
        return new ResponseEntity<>(getBookServiceResponse, HttpStatus.OK);
    }

    @GetMapping("books/")
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        List<BookResponse> getAllBookServiceResponse = bookService.getAllBooks();
        return new ResponseEntity<>(getAllBookServiceResponse, HttpStatus.OK);
    }

    @PostMapping("books")
    public ResponseEntity<BookResponse> addBook(
            @RequestParam(value = "logged_in") Integer loggedIn, @RequestBody BookRequest bookRequest) {
        BookResponse addBookServiceResponse = bookService.addBook(loggedIn, bookRequest);
        return new ResponseEntity<>(addBookServiceResponse, HttpStatus.OK);
    }

    @PutMapping("books/{book_id}")
    public ResponseEntity<BookResponse> updateBook(
            @PathVariable(value = "book_id") Integer bookId,
            @RequestParam(value = "logged_in") Integer loggedIn,
            @RequestBody BookRequest bookRequest) {
        BookResponse updateBookServiceResponse =
                bookService.updateBook(loggedIn, bookId, bookRequest);
        return new ResponseEntity<>(updateBookServiceResponse, HttpStatus.OK);
    }

    @DeleteMapping("books/{book_id}")
    public ResponseEntity<Boolean> deleteBook(
            @PathVariable(value = "book_id") Integer bookId) {
        Boolean deleteBookServiceResponse = bookService.deleteBook(bookId);
        return new ResponseEntity<>(deleteBookServiceResponse, HttpStatus.OK);
    }

    @GetMapping(value = "whoami/{username}")
    public String whoami(@PathVariable("username") String username) {
        return String.format("Hello! You're %s and you'll become a(n) %s...\n", username, role);
    }

}
