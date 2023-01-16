package com.peliculas.peliculas.excepciones;

import com.peliculas.peliculas.entidad.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Message> throwGeneralException(Exception ex) {
        return ResponseEntity.internalServerError()
                .body(new Message(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()));
    }

    @ExceptionHandler(MiException.class)
    public ResponseEntity<Message> throwMiException(MiException ex) {
        return ResponseEntity.internalServerError()
                .body(new Message(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()));
    }
}
