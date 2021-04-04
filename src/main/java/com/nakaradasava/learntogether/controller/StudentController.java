package com.nakaradasava.learntogether.controller;

import com.nakaradasava.learntogether.entity.ConfirmationToken;
import com.nakaradasava.learntogether.entity.Student;
import com.nakaradasava.learntogether.service.ConfirmationTokenService;
import com.nakaradasava.learntogether.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Optional;

@Controller
public class StudentController {

    private StudentService studentService;
    private ConfirmationTokenService confirmationTokenService;

    @Autowired
    public StudentController(StudentService studentService, ConfirmationTokenService confirmationTokenService) {
        this.studentService = studentService;
        this.confirmationTokenService = confirmationTokenService;
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("student", new Student());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Student student) {
        studentService.registerStudent(student);

        return "redirect:/login";
    }

    @GetMapping("/register/confirm")
    public String confirmMail(@RequestParam("token") String token) {
        Optional<ConfirmationToken> optionalConfirmationToken =
                confirmationTokenService.findConfirmationTokenByToken(token);
        optionalConfirmationToken.ifPresent(studentService::confirmRegistration);
        return "login";
    }

//    @GetMapping("/test")
//    public String home(@AuthenticationPrincipal Student student, Model model) {
//        model.addAttribute("student", student);
//        return "test";
//    }
}
