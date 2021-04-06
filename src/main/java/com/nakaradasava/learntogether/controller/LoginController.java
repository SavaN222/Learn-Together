package com.nakaradasava.learntogether.controller;

import com.nakaradasava.learntogether.entity.Student;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String home(@AuthenticationPrincipal Student student, Model model) {
        model.addAttribute("profilePic", "/images/profile_pictures/" + student.getProfilePic());
        return "index";
    }
}
