package com.base_student.service;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.base_student.dto.ErrorMessageDto;
import com.base_student.dto.StudentRequestDto;
import com.base_student.dto.StudentResponseDto;
import com.base_student.exception.BusinessException;
import com.base_student.model.StudentModel;
import com.base_student.respository.StudentRepository;
import com.base_student.util.DatetimeUtil;

import static com.base_student.constants.Constants.RECORD_NOT_FOUND;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentResponseDto> findAll() {
        return studentRepository.findAll().stream().map(StudentModel::toDto).toList();
    }

    public StudentResponseDto findById(Integer id) throws BusinessException {
        return studentRepository.findById(id).orElseThrow(() -> {
            ErrorMessageDto errorMessageDto = new ErrorMessageDto(RECORD_NOT_FOUND, HttpStatus.NOT_FOUND,
                    DatetimeUtil.getTimestamptInMilliseconds());
            return new BusinessException(errorMessageDto);
        }).toDto();
    }

    public StudentResponseDto create(StudentRequestDto dto) {
        return studentRepository.save(dto.toModel()).toDto();
    }

    public void deleteById(Integer id) {
        studentRepository.deleteById(id);
    }

}
