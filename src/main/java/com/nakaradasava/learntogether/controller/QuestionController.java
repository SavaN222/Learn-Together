package com.nakaradasava.learntogether.controller;

import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.entity.studyfield.CommentStudy;
import com.nakaradasava.learntogether.entity.studyfield.QuestionStudy;
import com.nakaradasava.learntogether.service.studyfield.CommentStudyService;
import com.nakaradasava.learntogether.service.studyfield.QuestionStudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class QuestionController {

    private QuestionStudyService questionStudyService;
    private CommentStudyService commentStudyService;

    @Autowired
    public QuestionController(QuestionStudyService questionStudyService, CommentStudyService commentStudyService) {
        this.questionStudyService = questionStudyService;
        this.commentStudyService = commentStudyService;
    }

    @GetMapping("/question/{id}")
    public String getQuestion(@PathVariable int id,
                              Model model) {
        QuestionStudy questionStudy = questionStudyService.findById(id);
        List<CommentStudy> comments = commentStudyService.findCommentsByQuestionId(id);
        int commentsNumber = commentStudyService.countCommentStudiesByQuestionStudyId(id);

        model.addAttribute("question", questionStudy);
        model.addAttribute("comments", comments);
        model.addAttribute("commentObj", new CommentStudy());
        model.addAttribute("commentsNumber", commentsNumber);

        return "study_field/question";
    }

    @PostMapping("/question/save/{id}")
    public String saveQuestion(@ModelAttribute(name = "commentObj") CommentStudy commentStudy,
                               RedirectAttributes redirectAttributes,
                               @PathVariable(name = "id") int questionId,
                               @AuthenticationPrincipal Student student) {

        QuestionStudy questionStudy = questionStudyService.findById(questionId);

        commentStudy.setQuestionStudy(questionStudy);
        commentStudy.setStudent(student);

        commentStudyService.saveComment(commentStudy);

        redirectAttributes.addFlashAttribute("success", "Comment posted");

        return "redirect:/question/" + questionId;
    }
}
