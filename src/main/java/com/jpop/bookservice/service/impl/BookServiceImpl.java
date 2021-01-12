package com.jpop.bookservice.service.impl;

import com.jpop.bookservice.constant.BookStatusCode;
import com.jpop.bookservice.entity.Book;
import com.jpop.bookservice.exception.BookServiceBaseException;
import com.jpop.bookservice.model.request.BookRequest;
import com.jpop.bookservice.model.response.BookResponse;
import com.jpop.bookservice.repository.BookRepository;
import com.jpop.bookservice.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Validated
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public BookResponse getBookDetails(Integer bookId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        BookResponse bookResponse;
        if(bookOptional.isPresent()){
            bookResponse = new BookResponse();
            BeanUtils.copyProperties(bookOptional.get(), bookResponse);
        } else {
            throw new BookServiceBaseException(BookStatusCode.DATABASE_ERROR.getDesc());
        }
        return bookResponse;
    }

    @Override
    public List<BookResponse> getAllBooks() {
        List<Book> bookPage = bookRepository.findAll();
        List<BookResponse> bookResponseList = new ArrayList<>();
        bookPage.forEach( user -> {
            BookResponse userResponse = new BookResponse();
            BeanUtils.copyProperties(user , userResponse);
            bookResponseList.add(userResponse);
        });
        return bookResponseList;
    }

    @Override
    public BookResponse addBook(Integer loggedIn, BookRequest bookRequest) {
        Book book = new Book();
        BeanUtils.copyProperties(bookRequest, book);
        book.setCreatedBy(loggedIn);
        BookResponse bookResponse =  new BookResponse();
        Book savedBook = bookRepository.save(book);
        BeanUtils.copyProperties(savedBook, bookResponse);
        return bookResponse;
    }

    @Override
    public BookResponse updateBook(Integer loggedIn, Integer bookId, BookRequest bookRequest) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        BookResponse bookResponse;
        if(bookOptional.isPresent()){
            Book book = bookOptional.get();
            BeanUtils.copyProperties(bookRequest, book);
            book.setUpdatedBy(loggedIn);
            Book savedBook = bookRepository.save(book);
            bookResponse = new BookResponse();
            BeanUtils.copyProperties(savedBook, bookResponse);
        } else {
            throw new BookServiceBaseException(BookStatusCode.DATABASE_ERROR.getDesc());
        }
        return bookResponse;
    }

    @Override
    public boolean deleteBook(Integer bookId) {
        bookRepository.deleteById(bookId);
        return true;
    }

}
