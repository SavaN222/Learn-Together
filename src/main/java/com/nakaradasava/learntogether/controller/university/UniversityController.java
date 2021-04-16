package com.nakaradasava.learntogether.controller.university;

import com.nakaradasava.learntogether.entity.university.UniversityPostLike;
import com.nakaradasava.learntogether.entity.university.UniversityPost;
import com.nakaradasava.learntogether.entity.university.University;
import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.service.university.UniversityPostService;
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
    private UniversityPostService universityPostService;

    @Autowired
    public UniversityController(UniversityService universityService, UniversityPostService universityPostService) {
        this.universityService = universityService;
        this.universityPostService = universityPostService;
    }

    @GetMapping("/university/{id}")
    public String showUniversity(@PathVariable int id,
                                 @AuthenticationPrincipal Student student,
                                 Model model) {

        if (student.getUniversity().getId() != id) {
            return "redirect:/";
        }

        University university = universityService.findUniversity(id);
        List<UniversityPost> universityPosts = universityPostService.findPostsByStudent(student);

        model.addAttribute("university", university);
        model.addAttribute("post", new UniversityPost());
        model.addAttribute("like", new UniversityPostLike());
        model.addAttribute("studentPosts", universityPosts);

        return "university/university";
    }
}