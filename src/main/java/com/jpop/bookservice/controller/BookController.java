package com.jpop.bookservice.controller;

import com.jpop.bookservice.entity.Book;
import com.jpop.bookservice.model.BookRequest;
import com.jpop.bookservice.model.response.BookResponse;
import com.jpop.bookservice.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/")
@Slf4j
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("books/{book_id}")
    public ResponseEntity<BookResponse<Book>> getBookDetails(
            @PathVariable(value = "book_id") Integer bookId) {
        BookResponse<Book> getBookResponse = bookService.getBookDetails(bookId);
        return new ResponseEntity<>(getBookResponse, HttpStatus.OK);
    }

    @GetMapping("books/")
    public ResponseEntity<BookResponse<Page<Book>>> getAllBooks(Pageable pageable) {
        BookResponse<Page<Book>> getAllBookResponse = bookService.getAllBooks(pageable);
        return new ResponseEntity<>(getAllBookResponse, HttpStatus.OK);
    }

    @PostMapping("books")
    public ResponseEntity<BookResponse<Book>> addBook(
            @RequestParam(value = "user_id") Long userId,
            @RequestBody BookRequest bookRequest) {
        BookResponse<Book> addBookResponse =
                bookService.addBook(userId, bookRequest);
        return new ResponseEntity<>(addBookResponse, HttpStatus.OK);
    }

    @PutMapping("books/{book_id}")
    public ResponseEntity<BookResponse<Book>> updateBook(
            @PathVariable(value = "book_id") Integer bookId,
            @RequestParam(value = "user_id") Long userId,
            @RequestBody BookRequest bookRequest) {
        BookResponse<Book> updateBookResponse =
                bookService.updateBook(userId, bookId, bookRequest);
        return new ResponseEntity<>(updateBookResponse, HttpStatus.OK);
    }

    @DeleteMapping("books/{book_id}")
    public ResponseEntity<BookResponse<Book>> deleteBook(
            @PathVariable(value = "book_id") Integer bookId) {
        BookResponse<Book> deleteBookResponse = bookService.deleteBook(bookId);
        return new ResponseEntity<>(deleteBookResponse, HttpStatus.OK);
    }

}
