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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.Option;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
                           @AuthenticationPrincipal Student student,
                           RedirectAttributes redirectAttributes) {

        Quiz quiz = new Quiz();
        quiz.setQuizName(title);
        quiz.setStudent(student);

        quizService.saveQuiz(quiz);

        redirectAttributes.addFlashAttribute("created", "Quiz created");

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
                                 @RequestParam(name = "quizId") Integer quizId,
                                 RedirectAttributes redirectAttributes) {

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

        redirectAttributes.addFlashAttribute("created", "Question is added");

        return "redirect:/list-quizzes";
    }

    @GetMapping("/quizzes")
    public String showAllQuizzes(Model model) {
        model.addAttribute("quizzes", quizService.findall());

        return "quiz/quizzes";
    }

    @GetMapping("/quiz/{quizId}/question")
    public String showQuestion(@PathVariable(name = "quizId") int quizId,
                               @RequestParam(name = "questionId") int questionId,
                               Model model) {

        Quiz quiz = quizService.findQuizById(quizId);

        Collections.shuffle(quiz.getQuestions().get(questionId).getAnswers());

        model.addAttribute("quiz", quiz);

        return "quiz/quiz";

    }

    @PostMapping("/quiz/{quizId}/question")
    public String nextQuestion(@PathVariable(name = "quizId") int quizId,
                               @RequestParam(name = "questionId") int questionId,
                               @RequestParam(name = "answerId") Optional<Integer> answerOptional,
                               RedirectAttributes redirectAttributes,
                               HttpServletRequest request) {

        if (answerOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute("answerEmpty", "Answer cannot be empty");
            String referer = request.getHeader("Referer");
            return "redirect:" + referer;
        }

        int answerId = answerOptional.get();

        Answer answer = answersService.findById(answerId);
        Quiz quiz = quizService.findQuizById(quizId);

        List<Question> questions = quiz.getQuestions();

        if (answer.getValue() == null) {
            redirectAttributes.addFlashAttribute("wrong", "WRONG ANSWER");
        } else {
            redirectAttributes.addFlashAttribute("correct", "CORRECT ANSWER");

        }

        if (questions.size()-1 <= questionId) {
            redirectAttributes.addFlashAttribute("finish", "Quiz finish");
            return "redirect:/quizzes";
        }

        return "redirect:/quiz/" + quizId + "/question?questionId=" + (questionId + 1);

    }
}