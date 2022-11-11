package com.example.modelmapper.exception;

public class InvalidCommandException extends Exception {
  public InvalidCommandException() {
  }

  public InvalidCommandException(String message) {
    super(message);
  }

  public InvalidCommandException(String message, Throwable cause) {
    super(message, cause);
  }

  public InvalidCommandException(Throwable cause) {
    super(cause);
  }
}
