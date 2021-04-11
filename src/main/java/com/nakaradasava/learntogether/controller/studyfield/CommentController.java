package com.nakaradasava.learntogether.controller.studyfield;

import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.entity.studyfield.CommentStudy;
import com.nakaradasava.learntogether.entity.studyfield.QuestionStudy;
import com.nakaradasava.learntogether.service.studyfield.CommentStudyService;
import com.nakaradasava.learntogether.service.studyfield.QuestionStudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/comments/{questionId}")
    public String saveComment(@ModelAttribute(name = "commentObj") CommentStudy commentStudy,
                               RedirectAttributes redirectAttributes,
                               @PathVariable(name = "questionId") int questionId,
                               @AuthenticationPrincipal Student student) {

        QuestionStudy questionStudy = questionStudyService.findById(questionId);

        commentStudy.setQuestionStudy(questionStudy);
        commentStudy.setStudent(student);

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