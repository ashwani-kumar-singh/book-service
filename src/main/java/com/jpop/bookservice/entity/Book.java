package com.jpop.bookservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(doNotUseGetters = true)
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "author")
    private String author;

    @Column(name = "category")
    private String category;

    @Column(name = "total_pages")
    private int totalPages;

    @Column(name = "cost")
    private int cost;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "published_date")
    private Date publishedDate;

    @Column(name = "publisher_id")
    private Integer publishedId;

    @Column(name = "created_at")
    @CreatedDate
    private Date createdAt;

    @Column(name = "update_at")
    @LastModifiedDate
    private Date updatedAt;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "updated_by")
    private Long updatedBy;

}
