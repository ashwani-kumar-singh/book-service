package com.jpop.bookservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(doNotUseGetters = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookDTO {

    private Integer id;

    private String title;

    private String description;

    private String author;

    private String category;

    @JsonProperty(value = "total_pages")
    private int totalPages;

    private int cost;

    private String isbn;

    @JsonProperty(value = "publisher_id")
    private Integer publishedId;

    @JsonProperty(value = "published_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date publishedDate;

}
