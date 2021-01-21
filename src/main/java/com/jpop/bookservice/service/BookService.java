package com.jpop.bookservice.service;

import com.jpop.bookservice.model.BookRequest;
import com.jpop.bookservice.model.BookDTO;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Service to handle book details request
 */
public interface BookService {
    /**
     * Create a book for given below request.
     * @param loggedIn i.e. logged in
     * @param bookRequest i.e. user request
     * @return BookDTO i.e. returns newly created book.
     */
    BookDTO createBook(@NotNull(message = "logged in can not be null") Integer loggedIn,
                       @NotNull(message = "book request can not be null") BookRequest bookRequest);

    /**
     * Update a book for below request.
     * @param loggedIn i.e. logged in user.
     * @param bookId i.e. book id of book details to be updated.
     * @param bookRequest i.e. book request.
     * @return BookDTO i.e. returns newly created book.
     */
    BookDTO updateBook(@NotNull(message = "logged in can not be null")  Integer loggedIn,
                       @NotNull(message = "book id can not be null")  Integer bookId,
                       @NotNull(message = "book request can not be null")  BookRequest bookRequest);

    /**
     * Delete a book with given user id
     * @param bookId i.e. book id.
     */
    void deleteBook(@NotNull(message = "book id can not be null") Integer bookId);

    /**
     * Get Book details
     * @param bookId i.e. book id
     * @return BookDTO i.e. book details
     */
    BookDTO getBookDetails(@NotNull(message = "book id can not be null") Integer bookId);

    /**
     * Get all Book Details.
     * @return List<BookDTO> i.e. list of books with details.
     */
    List<BookDTO> getAllBooks();
}
