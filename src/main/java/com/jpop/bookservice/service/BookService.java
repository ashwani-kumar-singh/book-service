package com.jpop.bookservice.service;

import com.jpop.bookservice.entity.Book;
import com.jpop.bookservice.model.request.BookRequest;
import com.jpop.bookservice.model.response.BookResponse;
import com.jpop.bookservice.model.response.BookServiceResponse;
import com.sun.istack.NotNull;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookServiceResponse<BookResponse> addBook(@NonNull Integer loggedIn, @NotNull BookRequest bookRequest);

    BookServiceResponse<BookResponse> updateBook(@NotNull Integer loggedIn, @NotNull Integer bookId, @NotNull BookRequest bookRequest);

    BookServiceResponse<Boolean> deleteBook(@NotNull Integer bookId);

    BookServiceResponse<BookResponse> getBookDetails(@NotNull Integer bookId);

    BookServiceResponse<Page<BookResponse>> getAllBooks(Pageable pageable);
}
