package com.nakaradasava.learntogether.controller.university;

import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.entity.university.Post;
import com.nakaradasava.learntogether.entity.university.University;
import com.nakaradasava.learntogether.service.university.PostService;
import com.nakaradasava.learntogether.service.university.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PostUniversityContoller {

    private PostService postService;
    private UniversityService universityService;

    @Autowired
    public PostUniversityContoller(PostService postService, UniversityService universityService) {
        this.postService = postService;
        this.universityService = universityService;
    }

    @PostMapping("/post/{universityId}")
    public String savePost(@PathVariable int universityId,
                         @ModelAttribute Post post,
                         @AuthenticationPrincipal Student student,
                         RedirectAttributes redirectAttributes) {

        University university = universityService.findUniversity(universityId);

        post.setStudent(student);
        post.setUniversity(university);

        postService.savePost(post);

        redirectAttributes.addFlashAttribute("postMsg", "Post Created");

        return "redirect:/university/" + universityId;
    }

    @DeleteMapping("/post/delete/{postId}")
    public String deletePostById(@PathVariable int postId,
                                 @RequestParam int universityId,
                                 RedirectAttributes redirectAttributes) {

        postService.deletePostById(postId);

        redirectAttributes.addFlashAttribute("deletePostMsg", "Post deleted!");

        return "redirect:/university/" + universityId;
    }

    @GetMapping("/post/edit/{postId}")
    public String showEditForm(@PathVariable int postId,
                               Model model,
                               @AuthenticationPrincipal Student student) {

        Post post = postService.findPostById(postId);

        if (!student.getId().equals(post.getStudent().getId())) {
            return "redirect:/university/" + post.getUniversity().getId();
        }

        model.addAttribute("post", post);

        return "university/edit-post";
    }
}
