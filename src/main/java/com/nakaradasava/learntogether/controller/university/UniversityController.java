package com.nakaradasava.learntogether.controller.university;

import com.nakaradasava.learntogether.entity.university.LikePost;
import com.nakaradasava.learntogether.entity.university.Post;
import com.nakaradasava.learntogether.entity.university.University;
import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.service.university.PostService;
import com.nakaradasava.learntogether.service.university.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@Controller
public class UniversityController {
    private UniversityService universityService;
    private PostService postService;

    @Autowired
    public UniversityController(UniversityService universityService, PostService postService) {
        this.universityService = universityService;
        this.postService = postService;
    }

    @GetMapping("/university/{id}")
    public String showUniversity(@PathVariable int id,
                                 @AuthenticationPrincipal Student student,
                                 Model model) {

        if (student.getUniversity().getId() != id) {
            return "redirect:/";
        }

        University university = universityService.findUniversity(id);
        List<Post> posts = postService.findPostsByStudent(student);

        model.addAttribute("university", university);
        model.addAttribute("post", new Post());
        model.addAttribute("like", new LikePost());
        model.addAttribute("studentPosts", posts);

        return "university/university";

    }
}
