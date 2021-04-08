package com.nakaradasava.learntogether.controller;

import com.nakaradasava.learntogether.entity.StudyField;
import com.nakaradasava.learntogether.service.StudyFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Controller
public class StudyFieldController {

    private StudyFieldService studyFieldService;

    @Autowired
    public StudyFieldController(StudyFieldService studyFieldService) {
        this.studyFieldService = studyFieldService;
    }

    @GetMapping("/study-fields")
    public String showAllStudyFields(Model model) {

        List<StudyField> studyFields = studyFieldService.findStudyFields();

        model.addAttribute("studyFields", studyFields);

        return "study-fields";
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
