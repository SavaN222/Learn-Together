package com.nakaradasava.learntogether.controller.studyfield;

import com.nakaradasava.learntogether.entity.studyfield.QuestionStudy;
import com.nakaradasava.learntogether.entity.studyfield.StudyField;
import com.nakaradasava.learntogether.service.studyfield.QuestionStudyService;
import com.nakaradasava.learntogether.service.studyfield.StudyFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class StudyFieldController {

    private StudyFieldService studyFieldService;
    private QuestionStudyService questionStudyService;

    @Autowired
    public StudyFieldController(StudyFieldService studyFieldService,
                                QuestionStudyService questionStudyService) {
        this.studyFieldService = studyFieldService;
        this.questionStudyService = questionStudyService;
    }

    /**
     * Trim all white spaces for user inputs
     * @param dataBinder
     */
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    /**
     * If @RequestParam exist, return selected study field
     * If not exist, return select menu with available study fields
     * @param studyFieldId id for specific study field
     * @param model
     * @return study field(s)
     */
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
}
