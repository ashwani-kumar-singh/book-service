package com.jpop.bookservice.service;

import com.jpop.bookservice.model.request.BookRequest;
import com.jpop.bookservice.model.response.BookResponse;
import com.sun.istack.NotNull;
import lombok.NonNull;

import java.util.List;

public interface BookService {
    BookResponse addBook(@NonNull Integer loggedIn, @NotNull BookRequest bookRequest);

    BookResponse updateBook(@NotNull Integer loggedIn, @NotNull Integer bookId, @NotNull BookRequest bookRequest);

    boolean deleteBook(@NotNull Integer bookId);

    BookResponse getBookDetails(@NotNull Integer bookId);

    List<BookResponse> getAllBooks();
}
