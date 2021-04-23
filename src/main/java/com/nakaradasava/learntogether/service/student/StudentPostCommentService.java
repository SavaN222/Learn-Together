package com.nakaradasava.learntogether.service.student;

import com.nakaradasava.learntogether.entity.CommentStatus;
import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.entity.student.StudentPostComment;
import com.nakaradasava.learntogether.repository.student.StudentPostCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentPostCommentService {

    private StudentPostCommentRepository studentPostCommentRepository;

    @Autowired
    public StudentPostCommentService(StudentPostCommentRepository studentPostCommentRepository) {
        this.studentPostCommentRepository = studentPostCommentRepository;
    }

    public void saveComment(StudentPostComment studentPostComment) {
        studentPostCommentRepository.save(studentPostComment);
    }

    public void deleteCommentById(int id) {
        studentPostCommentRepository.deleteById(id);
    }

    public StudentPostComment findCommentById(int id) {
      return studentPostCommentRepository.findById(id).get();
    }

    public List<StudentPostComment> getNotificationsForStudentPostComment(int studentId) {
        return studentPostCommentRepository.findComments(studentId);
    }

}