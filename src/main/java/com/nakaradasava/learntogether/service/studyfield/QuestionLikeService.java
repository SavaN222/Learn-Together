package com.nakaradasava.learntogether.service.studyfield;

import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.entity.studyfield.QuestionLike;
import com.nakaradasava.learntogether.entity.studyfield.QuestionStudy;
import com.nakaradasava.learntogether.repository.studyfield.QuestionLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionLikeService {

    private QuestionLikeRepository questionLikeRepository;

    @Autowired
    public QuestionLikeService(QuestionLikeRepository questionLikeRepository) {
        this.questionLikeRepository = questionLikeRepository;
    }

    public void like(QuestionLike like) {
        questionLikeRepository.save(like);
    }

    public void dislike(int id) {
        questionLikeRepository.deleteById(id);
    }

    public Optional<QuestionLike> findByQuestionStudyAndStudent(QuestionStudy questionStudy, Student student) {
        return questionLikeRepository.findByQuestionStudyAndStudent(questionStudy, student);
    }
}
