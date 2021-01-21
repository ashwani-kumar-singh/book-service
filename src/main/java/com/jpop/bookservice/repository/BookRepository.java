package com.jpop.bookservice.repository;

import com.jpop.bookservice.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing Book entity.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

}
