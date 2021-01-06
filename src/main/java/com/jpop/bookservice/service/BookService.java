package com.jpop.bookservice.service;

import com.jpop.bookservice.entity.Book;
import com.jpop.bookservice.model.BookRequest;
import com.jpop.bookservice.model.response.BookResponse;
import com.sun.istack.NotNull;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookResponse<Book> addBook(@NonNull Long userId, @NotNull BookRequest bookRequest);

    BookResponse<Book> updateBook(@NotNull Long userId, @NotNull Integer bookId, @NotNull BookRequest bookRequest);

    BookResponse<Book> deleteBook(Integer bookId);

    BookResponse<Book> getBookDetails(Integer bookId);

    BookResponse<Page<Book>> getAllBooks(Pageable pageable);
}
