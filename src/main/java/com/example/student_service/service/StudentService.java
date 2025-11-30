package com.example.student_service.service;
import com.example.student_service.model.Student;
import java.util.List;

public interface StudentService {
    Student addStudent(Student student);
    Student updateStudent(Long id, Student student);
    void deleteStudent(Long id);
    List<Student> getAllStudents();
    Student getStudentById(Long id);
    List<Student> searchByName(String name);
    List<Student> searchByUniversity(String universityName);
}
