package com.nakaradasava.learntogether.controller.university;

import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.entity.university.CommentPost;
import com.nakaradasava.learntogether.entity.university.Post;
import com.nakaradasava.learntogether.service.university.CommentPostService;
import com.nakaradasava.learntogether.service.university.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
}
