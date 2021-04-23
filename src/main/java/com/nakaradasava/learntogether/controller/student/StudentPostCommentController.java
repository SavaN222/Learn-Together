package com.nakaradasava.learntogether.controller.student;

import com.nakaradasava.learntogether.entity.CommentStatus;
import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.entity.student.StudentPost;
import com.nakaradasava.learntogether.entity.student.StudentPostComment;
import com.nakaradasava.learntogether.service.student.StudentPostCommentService;
import com.nakaradasava.learntogether.service.student.StudentPostService;
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
public class StudentPostCommentController {

   private StudentPostService studentPostService;
   private StudentPostCommentService studentPostCommentService;

   @Autowired
    public StudentPostCommentController(StudentPostService studentPostService, StudentPostCommentService studentPostCommentService) {
        this.studentPostService = studentPostService;
        this.studentPostCommentService = studentPostCommentService;
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

    @PostMapping("/student/comments/post/{postId}")
    public String saveStudentPostComment(
            @ModelAttribute(name = "commentObj") StudentPostComment studentPostComment,
            @PathVariable int postId,
            @AuthenticationPrincipal Student student,
            RedirectAttributes redirectAttributes,
            HttpSession session) {

        StudentPost studentPost = studentPostService.findPostById(postId);

        studentPostComment.setStudentPost(studentPost);
        studentPostComment.setStudent(student);

        if (studentPostComment.getStatus() == null) {
            studentPostComment.setStatus(CommentStatus.UNSEEN);
        }


        if (null != studentPostComment.getId()) {
            studentPostComment.setEdited(true);
        }

        studentPostCommentService.saveComment(studentPostComment);

        session.setAttribute("commentNotificationStudentPost",
                studentPostCommentService.getNotificationsForStudentPostComment(student.getId()));
        redirectAttributes.addFlashAttribute("successComment", "Comment posted");

        return "redirect:/student/post/" + postId;
    }

    @PostMapping("/student/post/comments/seen")
    public String seenComment(@RequestParam(name = "commentId") int commentId,
                              HttpServletRequest request,
                              HttpSession session,
                              @AuthenticationPrincipal Student student) {
        StudentPostComment studentPostComment = studentPostCommentService.findCommentById(commentId);

        studentPostComment.setStatus(CommentStatus.SEEN);

        studentPostCommentService.saveComment(studentPostComment);

        session.setAttribute("commentNotificationStudentPost",
                studentPostCommentService.getNotificationsForStudentPostComment(student.getId()));

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @DeleteMapping("/student/post/comments/delete/{commentId}")
    public String delete(@PathVariable int commentId,
                         RedirectAttributes redirectAttributes,
                         @RequestParam int postId) {

        studentPostCommentService.deleteCommentById(commentId);

        redirectAttributes.addFlashAttribute("deleteSuccess", "Your comment is deleted");

        return "redirect:/student/post/" + postId;
    }

    @GetMapping("/student/post/comments/edit/{commentId}")
    public String showEditForm(@PathVariable int commentId,
                               Model model,
                               @AuthenticationPrincipal Student student) {

        StudentPostComment studentPostComment = studentPostCommentService.findCommentById(commentId);

        if (!studentPostComment.getStudent().getId().equals(student.getId())) {
            return "redirect:/student/post/" + studentPostComment.getStudentPost().getId();
        }

        model.addAttribute("comment", studentPostComment);

        return "student/comment-update";
    }
}