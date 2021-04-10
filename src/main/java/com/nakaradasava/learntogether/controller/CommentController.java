package com.nakaradasava.learntogether.controller;

import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.entity.studyfield.CommentStudy;
import com.nakaradasava.learntogether.entity.studyfield.QuestionStudy;
import com.nakaradasava.learntogether.service.studyfield.CommentStudyService;
import com.nakaradasava.learntogether.service.studyfield.QuestionStudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CommentController {

    private CommentStudyService commentStudyService;
    private QuestionStudyService questionStudyService;

    @Autowired
    public CommentController(CommentStudyService commentStudyService, QuestionStudyService questionStudyService) {
        this.commentStudyService = commentStudyService;
        this.questionStudyService = questionStudyService;
    }

    @PostMapping("/comments/{id}")
    public String saveQuestion(@ModelAttribute(name = "commentObj") CommentStudy commentStudy,
                               RedirectAttributes redirectAttributes,
                               @PathVariable(name = "id") int id,
                               @AuthenticationPrincipal Student student) {

        QuestionStudy questionStudy = questionStudyService.findById(id);

        commentStudy.setQuestionStudy(questionStudy);
        commentStudy.setStudent(student);

        commentStudyService.saveComment(commentStudy);

        redirectAttributes.addFlashAttribute("success", "Comment posted");

        return "redirect:/question/" + id;
    }

}
