package com.nakaradasava.learntogether.service.quiz;

import com.nakaradasava.learntogether.entity.quiz.Question;
import com.nakaradasava.learntogether.repository.quiz.QuizQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizQuestionService {

    private QuizQuestionRepository quizQuestionRepository;

    @Autowired
    public QuizQuestionService(QuizQuestionRepository quizQuestionRepository) {
        this.quizQuestionRepository = quizQuestionRepository;
    }

    public void saveQuestion(Question question) {
        quizQuestionRepository.save(question);
    }

}
