package com.devsuperior.clients.resources.exceptions;

import com.devsuperior.clients.services.exceptions.DatabaseException;
import com.devsuperior.clients.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandartError> entityNotFound(ResourceNotFoundException exception, HttpServletRequest httpServletRequest) {
        StandartError error = new StandartError();
        HttpStatus reqStatus = HttpStatus.NOT_FOUND;
        error.setTimestamp(Instant.now());
        error.setStatus(reqStatus.value());
        error.setError("Resource not found");
        error.setMessage(exception.getMessage());
        error.setPath(httpServletRequest.getRequestURI());
        return ResponseEntity.status(reqStatus).body(error);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandartError> database(DatabaseException exception, HttpServletRequest httpServletRequest) {
        StandartError error = new StandartError();
        HttpStatus reqStatus = HttpStatus.BAD_REQUEST;
        error.setTimestamp(Instant.now());
        error.setStatus(reqStatus.value());
        error.setError("Database error");
        error.setMessage(exception.getMessage());
        error.setPath(httpServletRequest.getRequestURI());
        return ResponseEntity.status(reqStatus).body(error);
    }
}
