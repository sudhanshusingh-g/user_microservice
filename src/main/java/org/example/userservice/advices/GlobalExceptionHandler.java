package org.example.userservice.advices;

import org.example.userservice.DTOs.ErrorResponse;
import org.example.userservice.exceptions.InvalidPasswordException;
import org.example.userservice.exceptions.UserEmailAlreadyExist;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
//    User email already exist exception
    @ExceptionHandler(UserEmailAlreadyExist.class)
    public ResponseEntity<ErrorResponse> handleUserEmailAlreadyExist( UserEmailAlreadyExist ex) {
        ErrorResponse errorResponse=new ErrorResponse(ex.getMessage(), HttpStatus.CONFLICT.toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
    //    User password invalid exception
    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ErrorResponse> handleInvalidPassword( InvalidPasswordException ex) {
        ErrorResponse errorResponse=new ErrorResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED.toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
//    Generic exceptions handling
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException( Exception ex ) {
        ErrorResponse errorResponse=new ErrorResponse("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR.toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
