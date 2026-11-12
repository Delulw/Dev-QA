package com.base_student.respository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.base_student.model.StudentModel;

@Repository
public interface StudentRepository extends MongoRepository<StudentModel, String> {
}
