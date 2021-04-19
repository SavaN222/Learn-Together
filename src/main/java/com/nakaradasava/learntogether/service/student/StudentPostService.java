package com.nakaradasava.learntogether.service.student;


import com.nakaradasava.learntogether.entity.student.StudentPost;
import com.nakaradasava.learntogether.repository.student.StudentPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentPostService {

    private StudentPostRepository studentPostRepository;

    @Autowired
    public StudentPostService(StudentPostRepository studentPostRepository) {
        this.studentPostRepository = studentPostRepository;
    }

    public void save(StudentPost studentPost) {
        studentPostRepository.save(studentPost);
    }
}
