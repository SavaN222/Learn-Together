package com.nakaradasava.learntogether.controller.quiz;

import com.nakaradasava.learntogether.entity.quiz.Answer;
import com.nakaradasava.learntogether.entity.quiz.Question;
import com.nakaradasava.learntogether.entity.quiz.Quiz;
import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.service.quiz.AnswerService;
import com.nakaradasava.learntogether.service.quiz.QuizQuestionService;
import com.nakaradasava.learntogether.service.quiz.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class QuizController {

    private QuizService quizService;
    private AnswerService answersService;
    private QuizQuestionService quizQuestionService;

    @Autowired
    public QuizController(QuizService quizService, AnswerService answersService, QuizQuestionService quizQuestionService) {
        this.quizService = quizService;
        this.answersService = answersService;
        this.quizQuestionService = quizQuestionService;
    }

    /**
     * Show quizzes which created by logged-in student and form to create new quiz
     */
    @GetMapping("/list-quizzes")
    public String showStudentQuizzes(@AuthenticationPrincipal Student student,
                                        Model model) {

        model.addAttribute("quizzes", quizService.getStudentQuizzes(student));

        return "quiz/list-quizzes";
    }

    @PostMapping("/create-quiz")
    public String saveQuiz(@RequestParam(name = "title") String title,
                           @AuthenticationPrincipal Student student) {

        Quiz quiz = new Quiz();
        quiz.setQuizName(title);
        quiz.setStudent(student);

        quizService.saveQuiz(quiz);

        return "redirect:/list-quizzes";
    }

    /**
     * Save question for quiz and answers for question
     */
    @PostMapping("/quiz/create/question")
    public String createQuestion(@RequestParam(name = "title") String title,
                                 @RequestParam(name = "answer1") String answer1,
                                 @RequestParam(name = "answer2") String answer2,
                                 @RequestParam(name = "answer3") String answer3,
                                 @RequestParam(name = "answer4") String answer4,
                                 @RequestParam(name = "correctAnswer") Integer correctAnswer,
                                 @RequestParam(name = "quizId") Integer quizId) {

        Question question = new Question();
        question.setTitle(title);
        question.setQuiz(quizService.findQuizById(quizId));

        List<Answer> answers = answersService.prepareAnswers(answer1, answer2, answer3, answer4, correctAnswer);

        question.setAnswers(answers);
        quizQuestionService.saveQuestion(question);

        // save answers in database
        for (Answer answer : answers) {
            answer.setQuestion(question);
            answersService.save(answer);
        }

        return "redirect:/list-quizzes";
    }
}