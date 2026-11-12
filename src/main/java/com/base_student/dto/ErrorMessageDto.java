package com.base_student.dto;

import org.springframework.http.HttpStatus;

public record ErrorMessageDto(String message, HttpStatus httpStatus, Long timestamp) {
}
