package com.nakaradasava.learntogether.controller;

import com.nakaradasava.learntogether.entity.College;
import com.nakaradasava.learntogether.entity.ConfirmationToken;
import com.nakaradasava.learntogether.entity.Student;
import com.nakaradasava.learntogether.service.CollegeService;
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
import java.util.List;
import java.util.Optional;

@Controller
public class StudentController {

    private StudentService studentService;
    private ConfirmationTokenService confirmationTokenService;
    private CollegeService collegeService;

    @Autowired
    public StudentController(StudentService studentService, ConfirmationTokenService confirmationTokenService,
                             CollegeService collegeService) {
        this.studentService = studentService;
        this.confirmationTokenService = confirmationTokenService;
        this.collegeService = collegeService;
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
        List<College> colleges = collegeService.findColleges();

        model.addAttribute("student", new Student());
        model.addAttribute("colleges", colleges);

        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute Student student,
                           BindingResult bindingResult,
                           Model model) {
        List<College> colleges = collegeService.findColleges();

        if (bindingResult.hasErrors()) {
            model.addAttribute("colleges", colleges);
            return "register";
        }

        Student studentExist = studentService.findByUsernameOrEmail(student.getUsername(), student.getEmail());
        System.out.println(studentExist);
        if (studentExist != null) {
            model.addAttribute("student", new Student());
            model.addAttribute("colleges", colleges);
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

    @GetMapping("/home")
    public String home() {
        return "index";
    }

//    @GetMapping("/test")
//    public String home(@AuthenticationPrincipal Student student, Model model) {
//        model.addAttribute("student", student);
//        return "test";
//    }
}
