package com.jpop.bookservice.controller;

import com.jpop.bookservice.model.BookRequest;
import com.jpop.bookservice.model.BookDTO;
import com.jpop.bookservice.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.validation.Valid;
import java.util.List;

/**
 * REST controller for handling Book request
 */

@RestController
@RefreshScope
@EnableSwagger2
@RequestMapping("v1/")
@Slf4j
public class BookController {

    @Autowired
    private BookService bookService;

    /*@Value("${user.role : Default Role General}")
    private String role;*/

    /**
     * {@code GET  books/{book_id}} : get the book details for the requested book id.
     *
     * @param bookId i.e. book id
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the BookDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("books/{book_id}")
    public ResponseEntity<BookDTO> getBookDetails(
            @PathVariable(value = "book_id") Integer bookId) {
        log.debug("REST request to get book details with id:{}", bookId);
        BookDTO getBookServiceResponse = bookService.getBookDetails(bookId);
        return new ResponseEntity<>(getBookServiceResponse, HttpStatus.OK);
    }

    /**
     * {@code GET  books/} : get the all the books details.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the list of BookDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("books/")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        log.debug("REST request to get all book details");
        List<BookDTO> getAllBookServiceResponse = bookService.getAllBooks();
        return new ResponseEntity<>(getAllBookServiceResponse, HttpStatus.OK);
    }

    /**
     * {@code POST  books} : Create a new Book.
     *
     * @param bookRequest the book request to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
     *         body the new BookDTO, or with status {@code 500 (Internal Server Error)} if the
     *         book has already an ID.
     */
    @PostMapping("books")
    public ResponseEntity<BookDTO> createBook( @RequestParam(value = "logged_in") Integer loggedIn,
                                               @Valid @RequestBody BookRequest bookRequest) {
        log.debug("REST request by user :{} to create book for request:{}", loggedIn, bookRequest);
        BookDTO addBookServiceResponse = bookService.createBook(loggedIn, bookRequest);
        return new ResponseEntity<>(addBookServiceResponse, HttpStatus.OK);
    }

    /**
     * {@code PUT  books/{book_id}} : Update existing Book Details.
     *
     * @param bookRequest the book request to update.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
     *         body the new BookDTO, or with status {@code 500 (Internal Server Error)} if the
     *         book does not exist with given id.
     */
    @PutMapping("books/{book_id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable(value = "book_id") Integer bookId,
            @RequestParam(value = "logged_in") Integer loggedIn, @Valid @RequestBody BookRequest
                                                          bookRequest) {
        log.debug("REST request by user: {} to update book: {} for request: {}", loggedIn, bookId,
                bookRequest);
        BookDTO updateBookServiceResponse =
                bookService.updateBook(loggedIn, bookId, bookRequest);
        return new ResponseEntity<>(updateBookServiceResponse, HttpStatus.OK);
    }

    /**
     * {@code DELETE  books/{book_id}} : delete the "id" book.
     *
     * @param bookId the id of the book to delete.
     * @return the {@link ResponseEntity} with status {@code 200}.
     */
    @DeleteMapping("books/{book_id}")
    public ResponseEntity<Void> deleteBook(@PathVariable(value = "book_id") Integer bookId) {
        log.debug("REST request to delete book details with id:{}", bookId);
        bookService.deleteBook(bookId);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    /*@GetMapping(value = "whoami/{username}")
    public String whoami(@PathVariable("username") String username) {
        return String.format("Hello! You're %s and you'll become a(n) %s...\n", username, role);
    }*/
}
