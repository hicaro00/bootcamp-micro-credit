package com.lizana.microservicecredit.application.expetion;

public class CustomExeption extends RuntimeException {
  public CustomExeption(String message) {
    super(message);
  }
}
