package com.Test.CRUD.Test.CRUD.Services;

import com.Test.CRUD.Test.CRUD.Entities.Student;
import com.Test.CRUD.Test.CRUD.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Optional<Student> updateStudent(Long id, Student updatedStudent) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()) {
            studentOptional.get().setName(updatedStudent.getName());
            studentOptional.get().setSurname(updatedStudent.getSurname());
            Student studentUpdated = studentRepository.save(studentOptional.get());
            return Optional.of(studentUpdated);
        } else {
            return Optional.empty();
        }
    }

    public Optional<Student> updateIsWorking(Long id, boolean working) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()) {
            studentOptional.get().setWorking(working);
            studentRepository.save(studentOptional.get());
        }
        return studentOptional;
    }

    public Optional<Student> deleteStudentById(Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()) {
            studentRepository.delete(studentOptional.get());
        } else {
            return Optional.empty();
        }
        return studentOptional;
    }
}