package com.nakaradasava.learntogether.controller.quiz;

import com.nakaradasava.learntogether.entity.quiz.Quiz;
import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.service.quiz.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuizController {

    private QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
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

    @GetMapping("/quiz/{quizId}/add-question")
    public String createQuestion() {

        return "quiz/create-question";
    }
}
