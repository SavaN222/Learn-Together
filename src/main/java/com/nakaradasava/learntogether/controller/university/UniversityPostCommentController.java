package com.nakaradasava.learntogether.controller.university;

import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.entity.studyfield.CommentStudy;
import com.nakaradasava.learntogether.entity.university.CommentPost;
import com.nakaradasava.learntogether.entity.university.Post;
import com.nakaradasava.learntogether.service.university.CommentPostService;
import com.nakaradasava.learntogether.service.university.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UniversityPostCommentController {

    private CommentPostService commentPostService;
    private PostService postService;

    @Autowired
    public UniversityPostCommentController(CommentPostService commentPostService, PostService postService) {
        this.commentPostService = commentPostService;
        this.postService = postService;
    }

    @PostMapping("/university/comments/post/{postId}")
    public String saveUniversityPostComment(
            @ModelAttribute(name = "commentObj")CommentPost commentPost,
            @PathVariable int postId,
            @AuthenticationPrincipal Student student,
            RedirectAttributes redirectAttributes) {

        Post post = postService.findPostById(postId);

        commentPost.setPost(post);
        commentPost.setStudent(student);

        if (null != commentPost.getId()) {
            commentPost.setEdited(true);
        }

        commentPostService.saveComment(commentPost);

        redirectAttributes.addFlashAttribute("successComment", "Comment posted");

        return "redirect:/university/post/" + postId;
    }

    @DeleteMapping("/university/post/comments/delete/{commentId}")
    public String delete(@PathVariable int commentId,
                         RedirectAttributes redirectAttributes,
                         @RequestParam int postId) {

        commentPostService.deleteCommentById(commentId);

        redirectAttributes.addFlashAttribute("deleteSuccess", "Your comment is deleted");

        return "redirect:/university/post/" + postId;
    }

    @GetMapping("/university/post/comments/edit/{commentId}")
    public String showEditForm(@PathVariable int commentId,
                               Model model,
                               @AuthenticationPrincipal Student student) {
        CommentPost commentPost = commentPostService.findCommentById(commentId);

        if (!commentPost.getStudent().getId().equals(student.getId())) {
            return "redirect:/university/post/" + commentPost.getPost().getId();
        }

        model.addAttribute("comment", commentPost);

        return "university/comment-update";
    }
}
