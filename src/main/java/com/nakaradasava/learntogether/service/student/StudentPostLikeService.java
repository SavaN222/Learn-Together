package com.nakaradasava.learntogether.service.student;

import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.entity.student.StudentPost;
import com.nakaradasava.learntogether.entity.student.StudentPostLike;
import com.nakaradasava.learntogether.repository.student.StudentPostLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentPostLikeService {

    private StudentPostLikeRepository studentPostLikeRepository;

    @Autowired
    public StudentPostLikeService(StudentPostLikeRepository studentPostLikeRepository) {
        this.studentPostLikeRepository = studentPostLikeRepository;
    }

    public void like(StudentPostLike like) {
        studentPostLikeRepository.save(like);
    }

    public void dislike(int id) {
        studentPostLikeRepository.deleteById(id);
    }

    public Optional<StudentPostLike> findByPostAndStudent(StudentPost studentPost, Student student) {
        return studentPostLikeRepository.findByStudentPostAndStudent(studentPost, student);
    }
}