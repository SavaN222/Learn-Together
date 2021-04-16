package com.nakaradasava.learntogether.controller.studyfield;

import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.entity.studyfield.QuestionComment;
import com.nakaradasava.learntogether.entity.studyfield.QuestionLike;
import com.nakaradasava.learntogether.entity.studyfield.QuestionStudy;
import com.nakaradasava.learntogether.entity.studyfield.StudyField;
import com.nakaradasava.learntogether.service.studyfield.QuestionStudyService;
import com.nakaradasava.learntogether.service.studyfield.StudyFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class QuestionController {

    private QuestionStudyService questionStudyService;
    private StudyFieldService studyFieldService;

    @Autowired
    public QuestionController(QuestionStudyService questionStudyService, StudyFieldService studyFieldService) {
        this.questionStudyService = questionStudyService;
        this.studyFieldService = studyFieldService;
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

    @GetMapping("/questions/{questionId}")
    public String show(@PathVariable int questionId,
                              Model model) {
        QuestionStudy questionStudy = questionStudyService.findById(questionId);

        model.addAttribute("question", questionStudy);
        model.addAttribute("commentObj", new QuestionComment());
        model.addAttribute("like", new QuestionLike());

        return "study_field/question";
    }

    @PostMapping("/questions/{questionId}")
    public String saveQuestion(@PathVariable int questionId,
                               @Valid @ModelAttribute("question") QuestionStudy questionStudy,
                               BindingResult bindingResult,
                               @AuthenticationPrincipal Student student,
                               RedirectAttributes redirectAttributes) {

        StudyField studyField = studyFieldService.findStudyFieldById(questionId);

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("questionCreateError",
                    "Question title or content cannot be null");

            return "redirect:/study-fields";
        }

        questionStudy.setStudyField(studyField);
        questionStudy.setStudent(student);

        String saveMsg = "Your question is posted";

        if (null != questionStudy.getId()) {
            questionStudy.setEdited(true);
            saveMsg = "Your question is edited";
        }

        questionStudyService.saveQuestion(questionStudy);

        redirectAttributes.addFlashAttribute("success", saveMsg);

        return "redirect:/study-fields?studyField=" + questionId;
    }

    @DeleteMapping("/questions/delete/{questionId}")
    public String delete(@PathVariable int questionId,
                          RedirectAttributes redirectAttributes,
                          @RequestParam int studyFieldId) {

        questionStudyService.deleteById(questionId);

        redirectAttributes.addFlashAttribute("deleteSuccess", "Your question is deleted");

        return "redirect:/study-fields?studyField=" + studyFieldId;

    }

    @GetMapping("/questions/edit/{questionId}")
    public String showEditForm(@PathVariable int questionId,
                               Model model,
                               @AuthenticationPrincipal Student student) {
        QuestionStudy questionStudy = questionStudyService.findById(questionId);

        if (!questionStudy.getStudent().getId().equals(student.getId())) {
            return "redirect:/questions/" + questionStudy.getId();
        }

        model.addAttribute("question", questionStudy);

        return "study_field/question-update";
    }
}