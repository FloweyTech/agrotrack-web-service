package com.floweytech.agrotrack.platform.shared.interfaces.rest;

import com.floweytech.agrotrack.platform.shared.interfaces.rest.resources.MessageResource;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MessageResource> handleIllegalArgument(IllegalArgumentException ex){
        String message = messageSource.getMessage("error.illegal.argument",
                new Object[]{ex.getMessage()},
                LocaleContextHolder.getLocale());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new MessageResource(message));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageResource> handleAllUnhandledExceptions(Exception ex){
        String message = messageSource.getMessage("error.internal.server",
                new Object[]{ex.getMessage()},
                LocaleContextHolder.getLocale());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new MessageResource(message));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<MessageResource> handleEntityNotFound(EntityNotFoundException ex){
        String message = messageSource.getMessage("error.entity.not.found",
                new Object[]{ex.getMessage()},
                LocaleContextHolder.getLocale());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new MessageResource(message));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<MessageResource> handleAccessDenied(AccessDeniedException ex){
        String message = messageSource.getMessage("error.access.denied",
                new Object[]{ex.getMessage()},
                LocaleContextHolder.getLocale());
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new MessageResource(message));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<MessageResource> handleInvalidJson(HttpMessageNotReadableException ex){
        String message = messageSource.getMessage("error.invalid.json",
                new Object[]{ex.getMessage()},
                LocaleContextHolder.getLocale());
        return ResponseEntity.badRequest().body(new MessageResource(message));
    }

}
