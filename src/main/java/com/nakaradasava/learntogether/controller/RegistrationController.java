package com.nakaradasava.learntogether.controller;

import com.nakaradasava.learntogether.entity.*;
import com.nakaradasava.learntogether.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class RegistrationController {

    private StudentService studentService;
    private RegistrationService registrationService;
    private ConfirmationTokenService confirmationTokenService;
    private CollegeService collegeService;
    private StudyFieldService studyService;

    @Autowired
    public RegistrationController(StudentService studentService, RegistrationService registrationService,
                                  ConfirmationTokenService confirmationTokenService,
                                  CollegeService collegeService,
                                  StudyFieldService studyService) {
        this.studentService = studentService;
        this.registrationService = registrationService;
        this.confirmationTokenService = confirmationTokenService;
        this.collegeService = collegeService;
        this.studyService = studyService;
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
        List<StudyField> studyFields = studyService.findStudyFields();

        model.addAttribute("student", new RegistrationStudent());
        model.addAttribute("colleges", colleges);
        model.addAttribute("studyFields", studyFields);

        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("student") RegistrationStudent student,
                           BindingResult bindingResult,
                           Model model,
                           RedirectAttributes redirectAttributes) {
        List<College> colleges = collegeService.findColleges();
        List<StudyField> studyFields = studyService.findStudyFields();

        if (bindingResult.hasErrors()) {
            model.addAttribute("colleges", colleges);
            model.addAttribute("studyFields", studyFields);
            return "register";
        }

        Student studentExist = studentService.findByUsernameOrEmail(student.getUsername(), student.getEmail());
        if (studentExist != null) {
            model.addAttribute("student", new RegistrationStudent());
            model.addAttribute("colleges", colleges);
            model.addAttribute("studyFields", studyFields);
            model.addAttribute("registrationError", "username or email already exists.");
            return "register";
        }

        String profileDirectory = "/images/profile_pictures/";
        student.setProfilePic("Male".equals(student.getGender()) ?
                profileDirectory + "male.png" : profileDirectory + "female.png");

        registrationService.registerStudent(student);

        redirectAttributes.addFlashAttribute("confirmTokenMsg", "You are register, but you must" +
                " confirm your mail( " + student.getEmail() + " ) to login");
        return "redirect:/login";
    }

    @GetMapping("/register/confirm")
    public String confirmMail(@RequestParam("token") String token) {
        Optional<ConfirmationToken> optionalConfirmationToken =
                confirmationTokenService.findConfirmationTokenByToken(token);
        optionalConfirmationToken.ifPresent(registrationService::confirmRegistration);
        return "redirect:/login";
    }

//    @GetMapping("/test")
//    public String home(@AuthenticationPrincipal Student student, Model model) {
//        model.addAttribute("student", student);
//        return "test";
//    }
}
