package com.jpop.bookservice.service.impl;

import com.jpop.bookservice.constant.BookStatusCode;
import com.jpop.bookservice.entity.Book;
import com.jpop.bookservice.exception.BookServiceBaseException;
import com.jpop.bookservice.model.BookRequest;
import com.jpop.bookservice.model.BookDTO;
import com.jpop.bookservice.repository.BookRepository;
import com.jpop.bookservice.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Validated
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public BookDTO getBookDetails(Integer bookId) {
        log.debug("Request to get Book details for user id: {}", bookId);
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        BookDTO bookResponse;
        if(bookOptional.isPresent()){
            bookResponse = new BookDTO();
            BeanUtils.copyProperties(bookOptional.get(), bookResponse);
        } else {
            throw new BookServiceBaseException(BookStatusCode.DATABASE_ERROR.getDesc());
        }
        return bookResponse;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        log.debug("Request to get all Book details");
        List<Book> bookPage = bookRepository.findAll();
        List<BookDTO> bookResponseList = new ArrayList<>();
        bookPage.forEach( user -> {
            BookDTO userResponse = new BookDTO();
            BeanUtils.copyProperties(user , userResponse);
            bookResponseList.add(userResponse);
        });
        return bookResponseList;
    }

    @Override
    public BookDTO createBook(Integer loggedIn, BookRequest bookRequest) {
        log.debug("Request to save Book details for request: {} by user :{}", bookRequest, loggedIn);
        Book book = new Book();
        BeanUtils.copyProperties(bookRequest, book);
        book.setCreatedBy(loggedIn);
        BookDTO bookResponse =  new BookDTO();
        Book savedBook = bookRepository.save(book);
        BeanUtils.copyProperties(savedBook, bookResponse);
        return bookResponse;
    }

    @Override
    public BookDTO updateBook(Integer loggedIn, Integer bookId, BookRequest bookRequest) {
        log.debug("Request to update Book details for book id:{} with update request: {} by user :{}",
                bookId, bookRequest, loggedIn);
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        BookDTO bookResponse;
        if(bookOptional.isPresent()){
            Book book = bookOptional.get();
            BeanUtils.copyProperties(bookRequest, book);
            book.setUpdatedBy(loggedIn);
            Book savedBook = bookRepository.save(book);
            bookResponse = new BookDTO();
            BeanUtils.copyProperties(savedBook, bookResponse);
        } else {
            throw new BookServiceBaseException(BookStatusCode.DATABASE_ERROR.getDesc());
        }
        return bookResponse;
    }

    @Override
    public void deleteBook(Integer bookId) {
        log.debug("Request to delete Book details for user id : {}", bookId);
        bookRepository.deleteById(bookId);
    }

}
