package com.example.modelmapper.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.modelmapper.exception.InvalidEmailException;
import com.example.modelmapper.exception.InvalidPasswordException;

import org.springframework.stereotype.Component;

@Component
public class Validator {

  public String validatePassword(String s) throws InvalidPasswordException {
    Pattern pattern = Pattern.compile("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).*");
    Matcher matcher = pattern.matcher(s);
    if (!matcher.matches() || s.length() < 6) {
      throw new InvalidPasswordException("Invalid password");
    }
    return s;
  }

  public String validateEmail(String s) throws InvalidEmailException {
    if (!s.contains("@") || !s.contains(".")) {
      throw new InvalidEmailException("Email not valid!");
    }
    return s;
  }

  public String validateTrailer(String s) {
    if (s.length() != 11) {
      throw new IllegalArgumentException("Invalid trailer ID!");
    }
    return s;
  }

  public String validateTitle(String s) {
    if (s.length() < 3 || s.length() > 100
      || !Character.isUpperCase(s.charAt(0))) {
      throw new IllegalArgumentException("Invalid title!");
    }
    return s;
  }

  public Double validatePositive(String s) {
    double value = Double.parseDouble(s);
    if (value < 0) {
      throw new IllegalArgumentException("Cannot be negative");
    }
    return value;
  }

  public String validateThumbnail(String s) {
    Pattern pattern = Pattern.compile("^(http://|https://).+");
    Matcher matcher = pattern.matcher(s);
    if (!matcher.matches()) {
      throw new IllegalArgumentException("Thumbnail url invalid!");
    }
    return s;
  }

  public String validateDescription(String s) {
    if (s.length() < 20) {
      throw new IllegalArgumentException("Invalid description!");
    }
    return s;
  }
}
