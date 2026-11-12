package com.base_student.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.base_student.util.ValidationUtil;
import com.base_student.dto.StudentRequestDto;
import com.base_student.dto.StudentResponseDto;
import com.base_student.exception.BusinessException;
import com.base_student.service.StudentService;

@Controller
@RequestMapping("${api.student.url}")
public class StudentController {

    private final StudentService studentService;
    private final ValidationUtil validationUtil;

    public StudentController(StudentService studentService, ValidationUtil validationUtil) {
        this.studentService = studentService;
        this.validationUtil = validationUtil;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody StudentRequestDto studentDto) throws BusinessException {
        validationUtil.validate(studentDto);
        StudentResponseDto rsDto = studentService.create(studentDto);
        return new ResponseEntity<>(rsDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/find-by-id")
    public ResponseEntity<?> findById(@RequestParam(value = "id", required = false) Integer id)
            throws BusinessException {
        return new ResponseEntity<>(studentService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteById(@RequestParam(value = "id", required = false) Integer id) {
        studentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
