package com.base_student.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.base_student.dto.StudentRequestDto;
import com.base_student.dto.StudentResponseDto;
import com.base_student.exception.ResourceNotFoundException;
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
        StudentRequestDto request = new StudentRequestDto(
                null, "Jane", "Smith", "+52-222-33-44-55", "jane.smith@mail.com");
        StudentModel savedModel = new StudentModel(
                "student-2", "Jane", "Smith", "+52-222-33-44-55", "jane.smith@mail.com");
        when(studentRepository.save(any(StudentModel.class))).thenReturn(savedModel);

        StudentResponseDto result = studentService.create(request);

        assertEquals(savedModel.toDto(), result);
        ArgumentCaptor<StudentModel> modelCaptor = ArgumentCaptor.forClass(StudentModel.class);
        verify(studentRepository).save(modelCaptor.capture());
        assertEquals(request.name(), modelCaptor.getValue().getName());
        assertEquals(request.lastName(), modelCaptor.getValue().getLastName());
        assertEquals(request.eMail(), modelCaptor.getValue().geteMail());
    }

    @Test
    void testDeleteById() {
        String id = "student-1";
        when(studentRepository.existsById(id)).thenReturn(true);

        studentService.deleteById(id);

        verify(studentRepository).existsById(id);
        verify(studentRepository).deleteById(id);
    }

    @Test
    void testFindAll() {
        StudentModel first = new StudentModel(
                "student-1", "John", "Doe", "+52-111-12-34-56", "john.doe@mail.com");
        StudentModel second = new StudentModel(
                "student-2", "Jane", "Smith", "+52-222-33-44-55", "jane.smith@mail.com");
        when(studentRepository.findAll()).thenReturn(List.of(first, second));

        List<StudentResponseDto> result = studentService.findAll();

        assertEquals(List.of(first.toDto(), second.toDto()), result);
        verify(studentRepository).findAll();
    }

    @Test
    @DisplayName("Should return dto when id exists")
    void testFindById() {
        String id = "student-1";
        StudentModel studentModel = new StudentModel(id, "John", "Doe", "+52-111-12-34-56", "john.doe@mail.com");
        when(studentRepository.findById(id)).thenReturn(Optional.of(studentModel));
        StudentResponseDto studentResponseDto = studentService.findById(id);
        assertEquals(studentModel.getId(), studentResponseDto.id());
    }

    @Test
    @DisplayName("Should throw not found when id does not exist")
    void testFindByIdNotFound() {
        String id = "missing-student";
        when(studentRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> studentService.findById(id));
    }

    @Test
    @DisplayName("Should not delete when id does not exist")
    void testDeleteByIdNotFound() {
        String id = "missing-student";
        when(studentRepository.existsById(id)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> studentService.deleteById(id));
        verify(studentRepository, never()).deleteById(id);
    }
}
