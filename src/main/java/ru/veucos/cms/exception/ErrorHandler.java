package ru.veucos.cms.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Глобальный обработчик особых ситуаций
 * Генерит ответ http с требуемыми кодами ошибок
 */
@ControllerAdvice
@Log4j2
public class ErrorHandler {
    @ExceptionHandler(AppErrorException.class)
    public ResponseEntity<ServiceError> handleError(AppErrorException ex) {
        log.error(HttpStatus.CONFLICT + ": " + ex.getMessage());
        return generateError(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NoAuthenticatedException.class)
    public ResponseEntity<ServiceError> handleError(NoAuthenticatedException ex) {
        log.error(HttpStatus.UNAUTHORIZED + ": " + ex.getMessage());
        return generateError(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ServiceError> handleError(EntityNotFoundException ex) {
        log.error(HttpStatus.NOT_FOUND + ": " + ex.getMessage());
        return generateError(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ServiceError> handleError(NotFoundException ex) {
        log.error(HttpStatus.NOT_FOUND + ": " + ex.getMessage());
        return generateError(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<ServiceError> generateError(String message, HttpStatus httpStatus) {
        ServiceError serviceError = new ServiceError();
        serviceError.setStatus(httpStatus.value());
        serviceError.setTimeStamp(new Date().getTime());
        serviceError.setDetails(message);
        return ResponseEntity.status(httpStatus)
                .contentType(MediaType.APPLICATION_JSON)
                .body(serviceError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ServiceError> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            errors.add("\"" + ((FieldError) error).getField() + "\": " + error.getDefaultMessage());
        });
        log.error(HttpStatus.BAD_REQUEST + ": " + errors);
        return generateError(errors.toString(), HttpStatus.BAD_REQUEST);
    }
}
