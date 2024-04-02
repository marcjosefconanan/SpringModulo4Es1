package com.Test.CRUD.Test.CRUD.Controllers;

import com.Test.CRUD.Test.CRUD.Entities.Student;
import com.Test.CRUD.Test.CRUD.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    public ResponseEntity<Student> addStudent(@RequestBody Student student){
        Student studentAdded = studentService.createStudent(student);
        return ResponseEntity.ok().body(studentAdded);
    }

    @GetMapping("/returnAll")
    public ResponseEntity<List<Student>> getAllStudent(){
        List<Student> studentsView = studentService.getAllStudents();
        return ResponseEntity.ok().body(studentsView);
    }

    @GetMapping("/return/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id){
        Optional<Student> studentOptional = studentService.getStudentById(id);
        if(studentOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(studentOptional.get());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudentById(@RequestBody Student student,@PathVariable Long id){
        Optional<Student> studentOptional = studentService.updateStudent(id,student);
        if(studentOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(studentOptional.get());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id){
        Optional<Student> studentDaCancellare = studentService.deleteStudentById(id);
        if(studentDaCancellare.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(studentDaCancellare.get());
    }

    @PatchMapping("/setStato/{id}")
    public ResponseEntity<Optional<Student>> updateIsWorking(@PathVariable Long id, @RequestParam boolean working) {
        Optional<Student> studentDaModificare = studentService.updateIsWorking(id,working);
        return ResponseEntity.ok().body(studentDaModificare);
    }
}
