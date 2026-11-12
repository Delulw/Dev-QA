package com.base_student.model;

import com.base_student.dto.StudentResponseDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tools.jackson.databind.ObjectMapper;

@Document(collection = "students")
public class StudentModel {

    @Id
    private String id;
    private String name;
    private String lastName;
    private String phone;
    private String eMail;

    public StudentModel() {
    }

    public StudentModel(String id, String name, String lastName, String phone, String eMail) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.eMail = eMail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public StudentResponseDto toDto() {
        return new StudentResponseDto(
                this.id,
                this.name,
                this.lastName,
                this.phone,
                this.eMail);
    }

    @Override
    public String toString() {
        return new ObjectMapper().writeValueAsString(this);
    }
}