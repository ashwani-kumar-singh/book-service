package com.jpop.bookservice.constant;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(doNotUseGetters = true)
public enum BookStatusCode {

  SUCCESS(1000, "SUCCESS"),
  FAILED(1002, "FAILED"),
  DATA_VALIDATION_FAILED(1003, "DATA VALIDATION FAILED"),
  DATABASE_ERROR(1005, "FAILED IN DATABASE CALL"),
  FAILED_IN_GETTING_RATING_ANALYTICS(1005, "FAILED IN GETTING RATING ANALYTICS");

  private final int code;
  private final String desc;

  BookStatusCode(int code, String desc) {
    this.code = code;
    this.desc = desc;
  }
}