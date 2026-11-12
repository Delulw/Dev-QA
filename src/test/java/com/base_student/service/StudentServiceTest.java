package com.base_student.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.base_student.dto.StudentResponseDto;
import com.base_student.exception.BusinessException;
import com.base_student.model.StudentModel;
import com.base_student.respository.StudentRepository;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    @InjectMocks
    private StudentService studentService;

    @Test
    void testCreate() {

    }

    @Test
    void testDeleteById() {

    }

    @Test
    void testFindAll() {

    }

    @Test
    @DisplayName("Should return dto when id exists")
    void testFindById() throws BusinessException {
        Integer id = 1;
        StudentModel studentModel = new StudentModel(1, "John", "Doe", "+52-111-12-34-56", "john.doe@mail.com");
        when(studentRepository.findById(id)).thenReturn(Optional.of(studentModel));
        StudentResponseDto studentResponseDto = studentService.findById(id);
        assertEquals(studentModel.getId(), studentResponseDto.id());
    }
}
