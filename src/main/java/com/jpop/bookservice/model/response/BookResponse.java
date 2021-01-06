package com.jpop.bookservice.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.jpop.bookservice.constant.BookStatusCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString(doNotUseGetters = true)
@JsonIgnoreProperties(ignoreUnknown = true, value = {"status"}, allowGetters = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookResponse<T> {

  private BookStatusCode status;
  private T responseObject;

  @JsonIgnore
  private HttpStatus statusCode;

  public BookResponse() {
    this.statusCode = HttpStatus.OK;
  }

  public BookResponse(T responseObject) {
    this.responseObject = responseObject;
    this.statusCode = HttpStatus.OK;
  }

  public BookResponse(T responseObject, BookStatusCode status) {
    this.responseObject = responseObject;
    this.status = status;
    this.statusCode = HttpStatus.OK;
  }

  public BookResponse(BookStatusCode status) {
    this.status = status;
    this.statusCode = HttpStatus.OK;
  }

}
