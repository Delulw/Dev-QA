package com.base_student.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> businessExceptionHandler(BusinessException businessException) {
        return new ResponseEntity<>(businessException.getErrorMessageDto(),
                businessException.getErrorMessageDto().httpStatus());
    }

}
