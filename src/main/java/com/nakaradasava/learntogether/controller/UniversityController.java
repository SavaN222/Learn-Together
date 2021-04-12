package com.nakaradasava.learntogether.controller;

import com.nakaradasava.learntogether.entity.University;
import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UniversityController {
    private UniversityService universityService;

    @Autowired
    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @GetMapping("/university/{id}")
    public String showUniversity(@PathVariable int id,
                                 @AuthenticationPrincipal Student student,
                                 Model model) {

        if (student.getUniversity().getId() != id) {
            return "redirect:/";
        }

        University university = universityService.findUniversity(id);
        model.addAttribute("university", university);

        return "university";

    }
}
