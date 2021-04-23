package com.nakaradasava.learntogether.service.university;

import com.nakaradasava.learntogether.entity.CommentStatus;
import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.entity.studyfield.QuestionComment;
import com.nakaradasava.learntogether.entity.university.UniversityPostComment;
import com.nakaradasava.learntogether.repository.university.UniversityPostCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityPostCommentService {

    private UniversityPostCommentRepository universityPostCommentRepository;

    @Autowired
    public UniversityPostCommentService(UniversityPostCommentRepository universityPostCommentRepository) {
        this.universityPostCommentRepository = universityPostCommentRepository;
    }

    public void saveComment(UniversityPostComment universityPostComment) {
        universityPostCommentRepository.save(universityPostComment);
    }

    public void deleteCommentById(int id) {
        universityPostCommentRepository.deleteById(id);
    }

    public UniversityPostComment findCommentById(int id) {
      return universityPostCommentRepository.findById(id).get();
    }

    public List<UniversityPostComment> getNotificationsForUniversityPostComment(Student student) {
        return universityPostCommentRepository.findAllByStatusAndStudentNotOrderById(CommentStatus.UNSEEN, student);
    }
}