package com.base_student.exception;

import com.base_student.dto.ErrorMessageDto;

public class BusinessException extends RuntimeException {

    private final ErrorMessageDto errorMessageDto;

    public BusinessException(ErrorMessageDto errorMessageDto) {
        super(errorMessageDto.message());
        this.errorMessageDto = errorMessageDto;
    }

    public ErrorMessageDto getErrorMessageDto() {
        return errorMessageDto;
    }

}
