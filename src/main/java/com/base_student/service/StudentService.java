package com.base_student.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.base_student.dto.StudentRequestDto;
import com.base_student.dto.StudentResponseDto;
import com.base_student.exception.ResourceNotFoundException;
import com.base_student.model.StudentModel;
import com.base_student.respository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentResponseDto> findAll() {
        return studentRepository.findAll().stream().map(StudentModel::toDto).toList();
    }

    public StudentResponseDto findById(String id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado con el ID: " + id))
                .toDto();
    }

    public StudentResponseDto create(StudentRequestDto dto) {
        return studentRepository.save(dto.toModel()).toDto();
    }

    public void deleteById(String id) {
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Estudiante no encontrado con el ID: " + id);
        }
        studentRepository.deleteById(id);
    }

}
