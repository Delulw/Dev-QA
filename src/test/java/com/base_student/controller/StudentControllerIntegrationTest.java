package com.base_student.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.base_student.dto.StudentRequestDto;
import com.base_student.dto.StudentResponseDto;
import com.base_student.exception.GlobalExceptionHandler;
import com.base_student.exception.ResourceNotFoundException;
import com.base_student.service.StudentService;
import com.base_student.util.ValidationUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(StudentController.class)
@Import({ ValidationUtil.class, GlobalExceptionHandler.class })
class StudentControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StudentService studentService;

    @Test
    void createStudentReturnsSerializedStudent() throws Exception {
        StudentResponseDto student = new StudentResponseDto(
                "student-1", "Ana", "Lopez", "5551234", "ana@example.com");
        when(studentService.create(any(StudentRequestDto.class))).thenReturn(student);

        mockMvc.perform(post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "id": "student-1",
                          "name": "Ana",
                          "lastName": "Lopez",
                          "phone": "5551234",
                          "eMail": "ana@example.com"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("student-1"))
                .andExpect(jsonPath("$.name").value("Ana"))
                .andExpect(jsonPath("$.eMail").value("ana@example.com"));

        verify(studentService).create(any(StudentRequestDto.class));
    }

    @Test
    void createStudentWithInvalidEmailReturnsBadRequest() throws Exception {
        mockMvc.perform(post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "name": "Ana",
                          "eMail": "invalid-email"
                        }
                        """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("The email is not in a valid format"))
                .andExpect(jsonPath("$.httpStatus").value("400 BAD_REQUEST"))
                .andExpect(jsonPath("$.timestamp").isNumber());

        verifyNoInteractions(studentService);
    }

    @Test
    void findAllReturnsSerializedStudents() throws Exception {
        when(studentService.findAll()).thenReturn(List.of(
                new StudentResponseDto("student-1", "Ana", "Lopez", "5551234", "ana@example.com")));

        mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("student-1"))
                .andExpect(jsonPath("$[0].name").value("Ana"));

        verify(studentService).findAll();
    }

    @Test
    void findByIdReturnsSerializedStudent() throws Exception {
        when(studentService.findById("student-1")).thenReturn(
                new StudentResponseDto("student-1", "Ana", "Lopez", "5551234", "ana@example.com"));

        mockMvc.perform(get("/api/students/find-by-id").param("id", "student-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("student-1"))
                .andExpect(jsonPath("$.eMail").value("ana@example.com"));

        verify(studentService).findById("student-1");
    }

    @Test
    void findByIdForMissingStudentReturnsNotFoundJson() throws Exception {
        when(studentService.findById("missing"))
                .thenThrow(new ResourceNotFoundException("Estudiante no encontrado con el ID: missing"));

        mockMvc.perform(get("/api/students/find-by-id").param("id", "missing"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Estudiante no encontrado con el ID: missing"))
                .andExpect(jsonPath("$.httpStatus").value("404 NOT_FOUND"))
                .andExpect(jsonPath("$.timestamp").isNumber());
    }

    @Test
    void deleteStudentReturnsOk() throws Exception {
        mockMvc.perform(delete("/api/students").param("id", "student-1"))
                .andExpect(status().isOk());

        verify(studentService).deleteById("student-1");
    }

    @Test
    void deleteMissingStudentReturnsNotFoundJson() throws Exception {
        doThrow(new ResourceNotFoundException("Estudiante no encontrado con el ID: missing"))
                .when(studentService).deleteById("missing");

        mockMvc.perform(delete("/api/students").param("id", "missing"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Estudiante no encontrado con el ID: missing"))
                .andExpect(jsonPath("$.httpStatus").value("404 NOT_FOUND"));
    }
}
