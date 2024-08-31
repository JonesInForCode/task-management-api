/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */

package com.JonesInForCode.task_management_api;

/**
 *
 * @author Shadow
 */

 import org.springframework.http.HttpStatus;
 import org.springframework.web.bind.annotation.ControllerAdvice;
 import org.springframework.web.bind.annotation.ExceptionHandler;
 import org.springframework.web.bind.annotation.ResponseStatus;
 import org.springframework.http.ResponseEntity;
 
 @ControllerAdvice
 public class GlobalExceptionHandler {
     @ExceptionHandler(TaskNotFoundException.class)
     @ResponseStatus(HttpStatus.NOT_FOUND)
     public ResponseEntity<ErrorResponse> handleTaskNotFoundException(TaskNotFoundException ex) {
         ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
         return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
     }
}
