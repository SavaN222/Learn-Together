package com.nakaradasava.learntogether.controller.studyfield;

import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.entity.studyfield.CommentStudy;
import com.nakaradasava.learntogether.entity.studyfield.QuestionStudy;
import com.nakaradasava.learntogether.service.studyfield.CommentStudyService;
import com.nakaradasava.learntogether.service.studyfield.QuestionStudyService;
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
public class CommentQuestionController {

    private CommentStudyService commentStudyService;
    private QuestionStudyService questionStudyService;

    @Autowired
    public CommentQuestionController(CommentStudyService commentStudyService, QuestionStudyService questionStudyService) {
        this.commentStudyService = commentStudyService;
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
     * Save/Update comment
     * @param commentStudy thymeleaf binding object
     * @param redirectAttributes flash message
     * @param questionId question post id which hold comments
     * @param student logged-in student
     * @return redirect to question post(same page) but with new comment
     */
    @PostMapping("/comments/{questionId}")
    public String saveComment(@Valid @ModelAttribute(name = "commentObj") CommentStudy commentStudy,
                               BindingResult bindingResult,
                               Model model,
                               RedirectAttributes redirectAttributes,
                               @PathVariable(name = "questionId") int questionId,
                               @AuthenticationPrincipal Student student) {

        QuestionStudy questionStudy = questionStudyService.findById(questionId);

        if (bindingResult.hasErrors()) {
            model.addAttribute("question", questionStudy);
            return "study_field/question";
        }

        commentStudy.setQuestionStudy(questionStudy);
        commentStudy.setStudent(student);

        if (null != commentStudy.getId()) {
            commentStudy.setEdited(true);
        }

        commentStudyService.saveComment(commentStudy);

        redirectAttributes.addFlashAttribute("success", "Comment posted");

        return "redirect:/question/" + questionId;
    }

    @DeleteMapping("/comment/delete/{commentId}")
    public String delete(@PathVariable int commentId,
                          RedirectAttributes redirectAttributes,
                          @RequestParam int questionId) {

        commentStudyService.deleteCommentById(commentId);

        redirectAttributes.addFlashAttribute("deleteSuccess", "Your comment is deleted");

        return "redirect:/question/" + questionId;
    }

    @GetMapping("/comment/edit/{commentId}")
    public String showEditForm(@PathVariable int commentId,
                               Model model,
                               @AuthenticationPrincipal Student student) {
        CommentStudy commentStudy = commentStudyService.findCommentById(commentId);

        if (!commentStudy.getStudent().getId().equals(student.getId())) {
            return "redirect:/question/" + commentStudy.getQuestionStudy().getId();
        }

        model.addAttribute("comment", commentStudy);

        return "study_field/comment-update";
    }
}