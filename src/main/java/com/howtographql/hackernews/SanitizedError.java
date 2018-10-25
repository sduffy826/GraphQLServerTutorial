package com.howtographql.hackernews;

import com.fasterxml.jackson.annotation.JsonIgnore;
import graphql.ExceptionWhileDataFetching;

public class SanitizedError extends ExceptionWhileDataFetching {
  public SanitizedError(ExceptionWhileDataFetching theException) {
    super(theException.getException());
  }
  
  @Override
  @JsonIgnore
  public Throwable getException() {
    return super.getException();
  }

}
