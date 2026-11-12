package com.base_student.util;

import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.base_student.dto.ErrorMessageDto;
import com.base_student.dto.StudentRequestDto;
import com.base_student.exception.BusinessException;

import static com.base_student.constants.Constants.MAIL_NOT_VALID_FORMAT;
import static com.base_student.constants.Constants.NAME_MUST_NOT_BE_EMPTY;

import java.util.regex.Pattern;

@Component
public class ValidationUtil {

    public void validate(StudentRequestDto studentDto) throws BusinessException {

        if (Strings.isBlank(studentDto.eMail())
                || !Pattern.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
                        studentDto.eMail())) {
            throw new BusinessException(new ErrorMessageDto(MAIL_NOT_VALID_FORMAT, HttpStatus.BAD_REQUEST,
                    DatetimeUtil.getTimestamptInMilliseconds()));
        }

        if (Strings.isBlank(studentDto.name())) {
            throw new BusinessException(new ErrorMessageDto(NAME_MUST_NOT_BE_EMPTY, HttpStatus.BAD_REQUEST,
                    DatetimeUtil.getTimestamptInMilliseconds()));
        }

    }

}
