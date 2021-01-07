package com.jpop.bookservice.controller;

import com.jpop.bookservice.entity.Book;
import com.jpop.bookservice.model.request.BookRequest;
import com.jpop.bookservice.model.response.BookResponse;
import com.jpop.bookservice.model.response.BookServiceResponse;
import com.jpop.bookservice.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/")
@Slf4j
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("books/{book_id}")
    public ResponseEntity<BookServiceResponse<BookResponse>> getBookDetails(
            @PathVariable(value = "book_id") Integer bookId) {
        BookServiceResponse<BookResponse> getBookServiceResponse = bookService.getBookDetails(bookId);
        return new ResponseEntity<>(getBookServiceResponse, HttpStatus.OK);
    }

    @GetMapping("books/")
    public ResponseEntity<BookServiceResponse<Page<BookResponse>>> getAllBooks(Pageable pageable) {
        BookServiceResponse<Page<BookResponse>> getAllBookServiceResponse = bookService.getAllBooks(pageable);
        return new ResponseEntity<>(getAllBookServiceResponse, HttpStatus.OK);
    }

    @PostMapping("books")
    public ResponseEntity<BookServiceResponse<BookResponse>> addBook(
            @RequestParam(value = "logged_in") Integer loggedIn,
            @RequestBody BookRequest bookRequest) {
        BookServiceResponse<BookResponse> addBookServiceResponse =
                bookService.addBook(loggedIn, bookRequest);
        return new ResponseEntity<>(addBookServiceResponse, HttpStatus.OK);
    }

    @PutMapping("books/{book_id}")
    public ResponseEntity<BookServiceResponse<BookResponse>> updateBook(
            @PathVariable(value = "book_id") Integer bookId,
            @RequestParam(value = "logged_in") Integer loggedIn,
            @RequestBody BookRequest bookRequest) {
        BookServiceResponse<BookResponse> updateBookServiceResponse =
                bookService.updateBook(loggedIn, bookId, bookRequest);
        return new ResponseEntity<>(updateBookServiceResponse, HttpStatus.OK);
    }

    @DeleteMapping("books/{book_id}")
    public ResponseEntity<BookServiceResponse<Boolean>> deleteBook(
            @PathVariable(value = "book_id") Integer bookId) {
        BookServiceResponse<Boolean> deleteBookServiceResponse = bookService.deleteBook(bookId);
        return new ResponseEntity<>(deleteBookServiceResponse, HttpStatus.OK);
    }

}
