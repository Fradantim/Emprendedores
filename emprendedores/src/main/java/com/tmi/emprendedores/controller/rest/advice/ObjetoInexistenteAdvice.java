package com.tmi.emprendedores.controller.rest.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.tmi.emprendedores.exception.ObjetoInexistenteException;

@ControllerAdvice
class ObjetoInexistenteAdvice {

  @ResponseBody
  @ExceptionHandler(ObjetoInexistenteException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String employeeNotFoundHandler(ObjetoInexistenteException ex) {
    return ex.getMessage();
  }
}