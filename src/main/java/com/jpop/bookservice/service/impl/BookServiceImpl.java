package com.jpop.bookservice.service.impl;

import com.jpop.bookservice.constant.BookStatusCode;
import com.jpop.bookservice.entity.Book;
import com.jpop.bookservice.exception.BookServiceBaseException;
import com.jpop.bookservice.model.request.BookRequest;
import com.jpop.bookservice.model.response.BookResponse;
import com.jpop.bookservice.model.response.BookServiceResponse;
import com.jpop.bookservice.repository.BookRepository;
import com.jpop.bookservice.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
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
    public BookServiceResponse<BookResponse> getBookDetails(Integer bookId) {
        BookServiceResponse<BookResponse> bookServiceResponse = new BookServiceResponse<>(BookStatusCode.FAILED);
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if(bookOptional.isPresent()){
            BookResponse bookResponse = new BookResponse();
            BeanUtils.copyProperties(bookOptional.get(), bookResponse);
            bookServiceResponse.setResponseObject(bookResponse);
            bookServiceResponse.setStatus(BookStatusCode.SUCCESS);
        } else {
            throw new BookServiceBaseException(BookStatusCode.DATABASE_ERROR.getDesc());
        }
        return bookServiceResponse;
    }

    @Override
    public BookServiceResponse<Page<BookResponse>> getAllBooks(Pageable pageable) {
        BookServiceResponse<Page<BookResponse>> bookServiceResponse = new BookServiceResponse<>(BookStatusCode.FAILED);
        Page<Book> bookPage = bookRepository.findAll(pageable);
        List<BookResponse> bookResponseList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(bookPage.getContent())){
            bookPage.getContent().forEach( user -> {
                BookResponse userResponse = new BookResponse();
                BeanUtils.copyProperties(user , userResponse);
                bookResponseList.add(userResponse);
            });
        }
        bookServiceResponse.setResponseObject(new PageImpl<>(bookResponseList, pageable, bookResponseList.size()));
        bookServiceResponse.setStatus(BookStatusCode.SUCCESS);
        return bookServiceResponse;
    }

    @Override
    public BookServiceResponse<BookResponse> addBook(Integer loggedIn, BookRequest bookRequest) {
        BookServiceResponse<BookResponse> bookServiceResponse = new BookServiceResponse<>(BookStatusCode.FAILED);
        Book book = new Book();
        BeanUtils.copyProperties(bookRequest, book);
        book.setCreatedBy(loggedIn);
        BookResponse bookResponse =  new BookResponse();
        Book savedBook = bookRepository.save(book);
        BeanUtils.copyProperties(savedBook, bookResponse);
        bookServiceResponse.setResponseObject(bookResponse);
        bookServiceResponse.setStatus(BookStatusCode.SUCCESS);
        return bookServiceResponse;
    }

    @Override
    public BookServiceResponse<BookResponse> updateBook(Integer loggedIn, Integer bookId, BookRequest bookRequest) {
        BookServiceResponse<BookResponse> bookServiceResponse = new BookServiceResponse<>(BookStatusCode.FAILED);
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if(bookOptional.isPresent()){
            Book book = bookOptional.get();
            BeanUtils.copyProperties(bookRequest, book);
            book.setUpdatedBy(loggedIn);
            Book savedBook = bookRepository.save(book);
            BookResponse bookResponse = new BookResponse();
            BeanUtils.copyProperties(savedBook, bookResponse);
            bookServiceResponse.setResponseObject(bookResponse);
            bookServiceResponse.setStatus(BookStatusCode.SUCCESS);
        } else {
            throw new BookServiceBaseException(BookStatusCode.DATABASE_ERROR.getDesc());
        }
        return bookServiceResponse;
    }

    @Override
    public BookServiceResponse<Boolean> deleteBook(Integer bookId) {
        BookServiceResponse bookServiceResponse = new BookServiceResponse(Boolean.FALSE, BookStatusCode.FAILED);
        bookRepository.deleteById(bookId);
        bookServiceResponse.setResponseObject(Boolean.TRUE);
        bookServiceResponse.setStatus(BookStatusCode.SUCCESS);
        return bookServiceResponse;
    }

}
