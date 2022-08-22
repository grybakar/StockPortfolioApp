package com.example.finalProject.exception;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    final String FIELDS_INVALID_MSG = "One or more field are invalid";

    /**
     * A method to handle Position not found exception using SPRING exception handler
     *
     * @return ResponseEntity with a created error message with useful info.
     */

    @ExceptionHandler({PositionNotFoundException.class})
    public ResponseEntity<Object> handlePositionNotFoundException(PositionNotFoundException notFoundException,
                                                                  WebRequest request) {

        LOGGER.error("PositionNotFoundException: {}", notFoundException.getMessage());
        HttpStatus notFound = HttpStatus.NOT_FOUND;

        ErrorMessage errorMessage = ErrorMessage.builder()
                .httpStatus(notFound)
                .message(notFoundException.getLocalizedMessage())
                .path(request.getDescription(false))
                .build();

        return new ResponseEntity<>(errorMessage, notFound);
    }

    @ExceptionHandler(NoValidSymbolProvidedException.class)
    public ResponseEntity<Object> handleNoValidSymbolProvidedException(NoValidSymbolProvidedException ex,
                                                                       WebRequest request) {
        LOGGER.error("NoValidSymbolProvidedException: {}", ex.getMessage());

        ErrorMessage errorMessage = ErrorMessage.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .build();

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        List<String> errors = fieldErrors
                .stream()
                .map(fieldError -> fieldError.getField() + " : " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        final String debugMessage = exception.getCause() == null
                ? exception.toString() : exception.getCause().getMessage();

        ErrorMessage errorMessage = ErrorMessage.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message(FIELDS_INVALID_MSG)
                .path(request.getDescription(false))
                .subErrors(errors)
                .build();

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

}
