package com.example2.demo.services;

import com.example2.demo.entities.Student;
import com.example2.demo.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Transactional(readOnly = true)
    public List<Student> getStudent() {
        return studentRepository.findAll();
    }

    @Transactional(propagation = Propagation.NESTED)
    public void saveStudent(Student student) {
        studentRepository.save(student);
    }


    @Transactional(propagation = Propagation.NESTED)
    public void deleteStudent(String id) {
        boolean exist = studentRepository.existsById(id);
        if(!exist) throw new IllegalStateException("Student dont found");
        studentRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Student> getEmail(String email) {
        if(email.trim().isEmpty()) throw new IllegalStateException("CADENA VACIA");
        return studentRepository.getEmail(email);
    }
}
