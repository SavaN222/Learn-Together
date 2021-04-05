package com.nakaradasava.learntogether.controller;

import com.nakaradasava.learntogether.entity.ConfirmationToken;
import com.nakaradasava.learntogether.entity.Student;
import com.nakaradasava.learntogether.service.ConfirmationTokenService;
import com.nakaradasava.learntogether.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    /**
     * Trim all white spaces
     * @param dataBinder
     */
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("student", new Student());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute Student student,
                           BindingResult bindingResult,
                           Model model) {

        if (bindingResult.hasErrors()) {
            return "register";
        }

        Student studentExist = studentService.findByUsernameOrEmail(student.getUsername(), student.getEmail());
        System.out.println(studentExist);
        if (studentExist != null) {
            model.addAttribute("student", new Student());
            model.addAttribute("registrationError", "username or email already exists.");
            return "register";
        }

        studentService.registerStudent(student);

        return "redirect:/login";
    }

    @GetMapping("/register/confirm")
    public String confirmMail(@RequestParam("token") String token) {
        Optional<ConfirmationToken> optionalConfirmationToken =
                confirmationTokenService.findConfirmationTokenByToken(token);
        optionalConfirmationToken.ifPresent(studentService::confirmRegistration);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

//    @GetMapping("/test")
//    public String home(@AuthenticationPrincipal Student student, Model model) {
//        model.addAttribute("student", student);
//        return "test";
//    }
}
