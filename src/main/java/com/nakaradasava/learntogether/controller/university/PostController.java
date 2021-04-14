package com.nakaradasava.learntogether.controller.university;

import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.entity.university.Post;
import com.nakaradasava.learntogether.entity.university.University;
import com.nakaradasava.learntogether.service.university.PostService;
import com.nakaradasava.learntogether.service.university.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PostController {

    private PostService postService;
    private UniversityService universityService;

    @Autowired
    public PostController(PostService postService, UniversityService universityService) {
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
}
