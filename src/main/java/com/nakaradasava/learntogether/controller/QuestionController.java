package com.nakaradasava.learntogether.controller;

import com.nakaradasava.learntogether.entity.studyfield.CommentStudy;
import com.nakaradasava.learntogether.entity.studyfield.QuestionStudy;
import com.nakaradasava.learntogether.service.studyfield.CommentStudyService;
import com.nakaradasava.learntogether.service.studyfield.QuestionStudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

        model.addAttribute("question", questionStudy);
        model.addAttribute("comments", comments);

        return "study_field/question";
    }
}
