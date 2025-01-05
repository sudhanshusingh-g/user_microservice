package org.example.userservice.advices;

import org.example.userservice.DTOs.ErrorResponse;
import org.example.userservice.exceptions.InvalidPasswordException;
import org.example.userservice.exceptions.InvalidTokenException;
import org.example.userservice.exceptions.UserEmailAlreadyExist;
import org.example.userservice.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
//    User email already exist exception
    @ExceptionHandler(UserEmailAlreadyExist.class)
    public ResponseEntity<org.example.userservice.DTOs.ErrorResponse> handleUserEmailAlreadyExist(UserEmailAlreadyExist ex) {
        ErrorResponse errorResponse=new ErrorResponse(ex.getMessage(), HttpStatus.CONFLICT.toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
    //    User password invalid exception
    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ErrorResponse> handleInvalidPassword( InvalidPasswordException ex) {
        ErrorResponse errorResponse=new ErrorResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED.toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
//User not found exception
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound( UserNotFoundException ex) {
        ErrorResponse errorResponse=new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

//    Invalid token exception
    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ErrorResponse> handleInvalidToken( InvalidTokenException ex) {
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
