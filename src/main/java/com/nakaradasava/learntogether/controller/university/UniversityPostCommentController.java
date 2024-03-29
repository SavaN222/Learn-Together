package com.nakaradasava.learntogether.controller.university;

import com.nakaradasava.learntogether.entity.CommentStatus;
import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.entity.university.UniversityPostComment;
import com.nakaradasava.learntogether.entity.university.UniversityPost;
import com.nakaradasava.learntogether.service.university.UniversityPostCommentService;
import com.nakaradasava.learntogether.service.university.UniversityPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UniversityPostCommentController {

    private UniversityPostCommentService universityPostCommentService;
    private UniversityPostService universityPostService;

    @Autowired
    public UniversityPostCommentController(UniversityPostCommentService universityPostCommentService, UniversityPostService universityPostService) {
        this.universityPostCommentService = universityPostCommentService;
        this.universityPostService = universityPostService;
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

    @PostMapping("/university/comments/post/{postId}")
    public String saveUniversityPostComment(
            @ModelAttribute(name = "commentObj") UniversityPostComment universityPostComment,
            @PathVariable int postId,
            @AuthenticationPrincipal Student student,
            RedirectAttributes redirectAttributes,
            HttpSession session) {

        UniversityPost universityPost = universityPostService.findPostById(postId);

        universityPostComment.setUniversityPost(universityPost);
        universityPostComment.setStudent(student);

        if (universityPostComment.getStatus() == null) {
            universityPostComment.setStatus(CommentStatus.UNSEEN);
        }

        if (null != universityPostComment.getId()) {
            universityPostComment.setEdited(true);
        }

        universityPostCommentService.saveComment(universityPostComment);

        session.setAttribute("commentNotificationUniversity",
                universityPostCommentService.getNotificationsForUniversityPostComment(student.getId()));

        redirectAttributes.addFlashAttribute("successComment", "Comment posted");

        return "redirect:/university/post/" + postId;
    }

    @PostMapping("/university/post/comment/seen")
    public String seenComment(@RequestParam(name = "commentId") int commentId,
                              HttpServletRequest request,
                              HttpSession session,
                              @AuthenticationPrincipal Student student) {
        UniversityPostComment universityPostComment = universityPostCommentService.findCommentById(commentId);

        universityPostComment.setStatus(CommentStatus.SEEN);

        universityPostCommentService.saveComment(universityPostComment);

        session.setAttribute("commentNotificationUniversity",
                universityPostCommentService.getNotificationsForUniversityPostComment(student.getId()));

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @DeleteMapping("/university/post/comments/delete/{commentId}")
    public String delete(@PathVariable int commentId,
                         RedirectAttributes redirectAttributes,
                         @RequestParam int postId) {

        universityPostCommentService.deleteCommentById(commentId);

        redirectAttributes.addFlashAttribute("deleteSuccess", "Your comment is deleted");

        return "redirect:/university/post/" + postId;
    }

    @GetMapping("/university/post/comments/edit/{commentId}")
    public String showEditForm(@PathVariable int commentId,
                               Model model,
                               @AuthenticationPrincipal Student student) {
        UniversityPostComment universityPostComment = universityPostCommentService.findCommentById(commentId);

        if (!universityPostComment.getStudent().getId().equals(student.getId())) {
            return "redirect:/university/post/" + universityPostComment.getUniversityPost().getId();
        }

        model.addAttribute("comment", universityPostComment);

        return "university/comment-update";
    }
}