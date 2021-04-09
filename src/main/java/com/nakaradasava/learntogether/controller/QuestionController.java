package com.nakaradasava.learntogether.controller;

import com.nakaradasava.learntogether.entity.QuestionStudy;
import com.nakaradasava.learntogether.service.QuestionStudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {

    private QuestionStudyService questionStudyService;

    @Autowired
    public QuestionController(QuestionStudyService questionStudyService) {
        this.questionStudyService = questionStudyService;
    }

    @GetMapping("/question/{id}")
    public String getQuestion(@PathVariable int id, Model model) {
        QuestionStudy questionStudy = questionStudyService.findById(id);

        model.addAttribute("question", questionStudy);

        return "question";
    }
}
