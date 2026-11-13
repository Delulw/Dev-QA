package com.base_student.exception;

import com.base_student.dto.ErrorMessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MissingServletRequestParameterException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorMessageDto> handleBusinessException(BusinessException ex) {
        ErrorMessageDto errorDto = ex.getErrorMessageDto();
        return new ResponseEntity<>(errorDto, errorDto.httpStatus());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessageDto> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorMessageDto errorDto = new ErrorMessageDto(
            ex.getMessage(),
            HttpStatus.NOT_FOUND,
            System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorMessageDto> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        ErrorMessageDto errorDto = new ErrorMessageDto(
            "El cuerpo de la solicitud está vacío o contiene JSON inválido",
            HttpStatus.BAD_REQUEST,
            System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorMessageDto> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        ErrorMessageDto errorDto = new ErrorMessageDto(
            "El parámetro '" + ex.getName() + "' tiene un valor inválido",
            HttpStatus.BAD_REQUEST,
            System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorMessageDto> handleMissingRequestParameter(MissingServletRequestParameterException ex) {
        ErrorMessageDto errorDto = new ErrorMessageDto(
            "Falta el parámetro requerido '" + ex.getParameterName() + "'",
            HttpStatus.BAD_REQUEST,
            System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }
}
