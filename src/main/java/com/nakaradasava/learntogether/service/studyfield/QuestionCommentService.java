package com.nakaradasava.learntogether.service.studyfield;

import com.nakaradasava.learntogether.entity.CommentStatus;
import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.entity.student.StudentPostComment;
import com.nakaradasava.learntogether.entity.studyfield.QuestionComment;
import com.nakaradasava.learntogether.repository.studyfield.QuestionCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionCommentService {

    private QuestionCommentRepository questionCommentRepository;

    @Autowired
    public QuestionCommentService(QuestionCommentRepository questionCommentRepository) {
        this.questionCommentRepository = questionCommentRepository;
    }

    public void saveComment(QuestionComment questionComment) {
        questionCommentRepository.save(questionComment);
    }

    public void deleteCommentById(int id) {
        questionCommentRepository.deleteById(id);
    }

    public QuestionComment findCommentById(int id) {
      return questionCommentRepository.findById(id).get();
    }

    public List<QuestionComment> getNotificationsForQuestionPostComment(int studentId) {
        return questionCommentRepository.findComments(studentId);
    }
}