package com.nakaradasava.learntogether.service.quiz;

import com.nakaradasava.learntogether.entity.quiz.Quiz;
import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.repository.quiz.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    private QuizRepository quizRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public void saveQuiz(Quiz quiz) {
        quizRepository.save(quiz);
    }

    public List<Quiz> getStudentQuizzes(Student student) {
        return quizRepository.findAllByStudent(student);
    }
}
