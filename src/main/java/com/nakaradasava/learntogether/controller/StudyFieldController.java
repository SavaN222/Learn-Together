package com.nakaradasava.learntogether.controller;

import com.nakaradasava.learntogether.entity.QuestionStudy;
import com.nakaradasava.learntogether.entity.StudyField;
import com.nakaradasava.learntogether.service.QuestionStudyService;
import com.nakaradasava.learntogether.service.StudyFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String showAllStudyFields(@RequestParam(value = "studyField", required = false) Optional<Integer> studyFieldId,
            Model model) {

        if (studyFieldId.isPresent()) {
            List<QuestionStudy> questions = questionStudyService.findQuestionStudiesByStudyFieldId(studyFieldId.get());
            StudyField studyField = studyFieldService.findStudyFieldById(studyFieldId.get());

            model.addAttribute("studyField", studyField);
            model.addAttribute("questions", questions);
            model.addAttribute("question", new QuestionStudy());

            return "study-field";
        }

        List<StudyField> studyFields = studyFieldService.findStudyFields();

        model.addAttribute("studyFields", studyFields);

        return "study-fields";
    }

    @PostMapping("/study-fields")
    public String saveQuestion(@ModelAttribute("question") QuestionStudy questionStudy) {

        questionStudyService.saveQuestion(questionStudy);

        return "redirect:/study-fields?studyField=" + questionStudy.getStudyField().getId();
    }
    /**
     * Ovo je url link kad neko submituje formu -> http://localhost:8080/study-fields?studyField=2
     * if studyField postoji, pozovi kunkciju kloja vraca sva pitanja iz studyFielda sa Id u ovom slucaju 2
     * else vrati obican study fields
     * ako postoji return mu study-field html koji ima isti jumbotron ali ime study fielda i forma za ask question
     * ispod toga izlistaj sva pitanja
     * kad neko klikne na pitanje otvori MODAL ili stranicu novu
     * koemntari i lajkovi kao studentska kapica
     * favorites dugme
     */
}
