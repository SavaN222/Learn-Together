package com.nakaradasava.learntogether.controller.studyfield;

import com.nakaradasava.learntogether.entity.CommentStatus;
import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.entity.student.StudentPostComment;
import com.nakaradasava.learntogether.entity.studyfield.QuestionComment;
import com.nakaradasava.learntogether.entity.studyfield.QuestionStudy;
import com.nakaradasava.learntogether.service.studyfield.QuestionCommentService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class QuestionCommentController {

    private QuestionCommentService questionCommentService;
    private QuestionStudyService questionStudyService;

    @Autowired
    public QuestionCommentController(QuestionCommentService questionCommentService, QuestionStudyService questionStudyService) {
        this.questionCommentService = questionCommentService;
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
     * @param questionComment thymeleaf binding object
     * @param redirectAttributes flash message
     * @param questionId question post id which hold comments
     * @param student logged-in student
     * @return redirect to question post(same page) but with new comment
     */
    @PostMapping("/questions/comments/{questionId}")
    public String saveComment(@Valid @ModelAttribute(name = "commentObj") QuestionComment questionComment,
                              BindingResult bindingResult,
                              Model model,
                              RedirectAttributes redirectAttributes,
                              @PathVariable(name = "questionId") int questionId,
                              @AuthenticationPrincipal Student student,
                              HttpSession session) {

        QuestionStudy questionStudy = questionStudyService.findById(questionId);

        if (bindingResult.hasErrors()) {
            model.addAttribute("question", questionStudy);
            return "study_field/question";
        }

        questionComment.setQuestionStudy(questionStudy);
        questionComment.setStudent(student);

        if (questionComment.getStatus() == null) {
            questionComment.setStatus(CommentStatus.UNSEEN);
        }

        if (null != questionComment.getId()) {
            questionComment.setEdited(true);
        }

        questionCommentService.saveComment(questionComment);

        session.setAttribute("commentNotificationQuestion",
                questionCommentService.getNotificationsForQuestionPostComment(student.getId()));

        redirectAttributes.addFlashAttribute("success", "Comment posted");

        return "redirect:/questions/" + questionId;
    }

    @PostMapping("/question/comments/seen")
    public String seenComment(@RequestParam(name = "commentId") int commentId,
                              HttpServletRequest request,
                              HttpSession session,
                              @AuthenticationPrincipal Student student) {
        QuestionComment questionComment = questionCommentService.findCommentById(commentId);

        questionComment.setStatus(CommentStatus.SEEN);

        questionCommentService.saveComment(questionComment);

        session.setAttribute("commentNotificationQuestion",
                questionCommentService.getNotificationsForQuestionPostComment(student.getId()));

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @DeleteMapping("/questions/comments/delete/{commentId}")
    public String delete(@PathVariable int commentId,
                          RedirectAttributes redirectAttributes,
                          @RequestParam int questionId) {

        questionCommentService.deleteCommentById(commentId);

        redirectAttributes.addFlashAttribute("deleteSuccess", "Your comment is deleted");

        return "redirect:/questions/" + questionId;
    }

    @GetMapping("/questions/comments/edit/{commentId}")
    public String showEditForm(@PathVariable int commentId,
                               Model model,
                               @AuthenticationPrincipal Student student) {
        QuestionComment questionComment = questionCommentService.findCommentById(commentId);

        if (!questionComment.getStudent().getId().equals(student.getId())) {
            return "redirect:/questions/" + questionComment.getQuestionStudy().getId();
        }

        model.addAttribute("comment", questionComment);

        return "study_field/comment-update";
    }
}