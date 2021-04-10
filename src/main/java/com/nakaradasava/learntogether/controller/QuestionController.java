package com.nakaradasava.learntogether.controller;

import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.entity.studyfield.CommentStudy;
import com.nakaradasava.learntogether.entity.studyfield.QuestionStudy;
import com.nakaradasava.learntogether.entity.studyfield.StudyField;
import com.nakaradasava.learntogether.service.studyfield.CommentStudyService;
import com.nakaradasava.learntogether.service.studyfield.QuestionStudyService;
import com.nakaradasava.learntogether.service.studyfield.StudyFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class QuestionController {

    private QuestionStudyService questionStudyService;
    private StudyFieldService studyFieldService;

    @Autowired
    public QuestionController(QuestionStudyService questionStudyService, StudyFieldService studyFieldService) {
        this.questionStudyService = questionStudyService;
        this.studyFieldService = studyFieldService;
    }

    @GetMapping("/question/{id}")
    public String show(@PathVariable int id,
                              Model model) {
        QuestionStudy questionStudy = questionStudyService.findById(id);

        model.addAttribute("question", questionStudy);
        model.addAttribute("commentObj", new CommentStudy());

        return "study_field/question";
    }

    @PostMapping("/question/{id}")
    public String store(@PathVariable int id,
                               @ModelAttribute("question") QuestionStudy questionStudy,
                               @AuthenticationPrincipal Student student,
                               RedirectAttributes redirectAttributes) {

        StudyField studyField = studyFieldService.findStudyFieldById(id);

        questionStudy.setStudyField(studyField);
        questionStudy.setStudent(student);

        questionStudyService.saveQuestion(questionStudy);

        redirectAttributes.addFlashAttribute("success", "Your question is posted");

        return "redirect:/study-fields?studyField=" + id;
    }

    @DeleteMapping("/question/delete/{id}")
    public String destroy(@PathVariable int id,
                          @RequestBody int studyFieldId) {

        questionStudyService.deleteById(id);

        return "redirect:/study-fields?studyField=" + studyFieldId;

    }


}
