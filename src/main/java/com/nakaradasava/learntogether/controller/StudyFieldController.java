package com.nakaradasava.learntogether.controller;

import com.nakaradasava.learntogether.entity.studyfield.QuestionStudy;
import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.entity.studyfield.StudyField;
import com.nakaradasava.learntogether.service.studyfield.QuestionStudyService;
import com.nakaradasava.learntogether.service.studyfield.StudyFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class StudyFieldController {

    private StudyFieldService studyFieldService;
    private QuestionStudyService questionStudyService;

    @Autowired
    public StudyFieldController(StudyFieldService studyFieldService, QuestionStudyService questionStudyService) {
        this.studyFieldService = studyFieldService;
        this.questionStudyService = questionStudyService;
    }

    @GetMapping("/study-fields")
    public String showAllStudyFields(@RequestParam(name = "studyField", required = false) Optional<Integer> studyFieldId,
                                     Model model) {

        if (studyFieldId.isPresent()) {
            List<QuestionStudy> questions = questionStudyService.findQuestionStudiesByStudyFieldId(studyFieldId.get());
            StudyField studyField = studyFieldService.findStudyFieldById(studyFieldId.get());

            model.addAttribute("studyField", studyField);
            model.addAttribute("questions", questions);
            model.addAttribute("question", new QuestionStudy());

            return "study_field/study-field";
        }

        List<StudyField> studyFields = studyFieldService.findStudyFields();

        model.addAttribute("studyFields", studyFields);

        return "study_field/study-fields";
    }

    @PostMapping("/study-fields/save")
    public String saveQuestion(@RequestParam(name = "studyField", required = true) int studyFieldId,
                               @ModelAttribute("question") QuestionStudy questionStudy,
                               @AuthenticationPrincipal Student student,
                               RedirectAttributes redirectAttributes) {

        StudyField studyField = studyFieldService.findStudyFieldById(studyFieldId);

        questionStudy.setStudyField(studyField);
        questionStudy.setStudent(student);

        questionStudyService.saveQuestion(questionStudy);

        redirectAttributes.addFlashAttribute("success", "Your question is posted");

        return "redirect:/study-fields?studyField=" + studyFieldId;
    }
    /**
     * Uradi display svih questiona
     * svaki question nek ide na posebnu stranicu sa komentarima za taj question
     * uradi komentare lajkove a favorites tj follow topic cemo tek za kasnije
     * uradi edit i delete
     */
}
