package com.jpop.bookservice.service.impl;

import com.jpop.bookservice.constant.BookStatusCode;
import com.jpop.bookservice.entity.Book;
import com.jpop.bookservice.exception.BookServiceBaseException;
import com.jpop.bookservice.model.BookRequest;
import com.jpop.bookservice.model.response.BookResponse;
import com.jpop.bookservice.repository.BookRepository;
import com.jpop.bookservice.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Slf4j
@Service
@Validated
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public BookResponse<Book> getBookDetails(Integer bookId) {
        BookResponse bookResponse = new BookResponse(BookStatusCode.FAILED);
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if(bookOptional.isPresent()){
            bookResponse.setResponseObject(bookOptional.get());
            bookResponse.setStatus(BookStatusCode.SUCCESS);
        } else {
            throw new BookServiceBaseException(BookStatusCode.DATABASE_ERROR.getDesc());
        }
        return bookResponse;
    }

    @Override
    public BookResponse<Page<Book>> getAllBooks(Pageable pageable) {
        BookResponse bookResponse = new BookResponse(BookStatusCode.FAILED);
        bookResponse.setResponseObject(bookRepository.findAll(pageable));
        bookResponse.setStatus(BookStatusCode.SUCCESS);
        return bookResponse;
    }

    @Override
    public BookResponse<Book> addBook(Long userId, BookRequest bookRequest) {
        BookResponse bookResponse = new BookResponse(BookStatusCode.FAILED);
        Book book = new Book();
        BeanUtils.copyProperties(bookRequest, book);
        book.setCreatedBy(userId);
        bookResponse.setResponseObject(bookRepository.save(book));
        bookResponse.setStatus(BookStatusCode.SUCCESS);
        return bookResponse;
    }

    @Override
    public BookResponse updateBook(Long userId, Integer bookId, BookRequest bookRequest) {
        BookResponse bookResponse = new BookResponse(BookStatusCode.FAILED);
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if(bookOptional.isPresent()){
            Book book = bookOptional.get();
            BeanUtils.copyProperties(bookRequest, book);
            book.setUpdatedBy(userId);
            bookResponse.setResponseObject(bookRepository.save(book));
            bookResponse.setStatus(BookStatusCode.SUCCESS);
        } else {
            throw new BookServiceBaseException(BookStatusCode.DATABASE_ERROR.getDesc());
        }
        return bookResponse;
    }

    @Override
    public BookResponse<Book> deleteBook(Integer bookId) {
        BookResponse bookResponse = new BookResponse(Boolean.FALSE, BookStatusCode.FAILED);
        bookRepository.deleteById(bookId);
        bookResponse.setResponseObject(Boolean.TRUE);
        bookResponse.setStatus(BookStatusCode.SUCCESS);
        return bookResponse;
    }

}
