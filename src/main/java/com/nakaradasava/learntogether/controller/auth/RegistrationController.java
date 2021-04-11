package com.nakaradasava.learntogether.controller.auth;

import com.nakaradasava.learntogether.entity.*;
import com.nakaradasava.learntogether.entity.student.RegistrationStudent;
import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.entity.studyfield.StudyField;
import com.nakaradasava.learntogether.entity.token.ConfirmationToken;
import com.nakaradasava.learntogether.service.*;
import com.nakaradasava.learntogether.service.student.RegistrationService;
import com.nakaradasava.learntogether.service.student.StudentService;
import com.nakaradasava.learntogether.service.studyfield.StudyFieldService;
import com.nakaradasava.learntogether.service.token.ConfirmationTokenService;
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
    private UniversityService universityService;
    private StudyFieldService studyService;

    @Autowired
    public RegistrationController(StudentService studentService, RegistrationService registrationService,
                                  ConfirmationTokenService confirmationTokenService,
                                  UniversityService universityService,
                                  StudyFieldService studyService) {
        this.studentService = studentService;
        this.registrationService = registrationService;
        this.confirmationTokenService = confirmationTokenService;
        this.universityService = universityService;
        this.studyService = studyService;
    }

    /**
     * Trim all white spaces for user inputs
     * @param dataBinder
     */
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    /**
     * Show registration page with list of all universities and study fields
     * @param model model for thymeleaf binding
     * @return registration page
     */
    @GetMapping("/register")
    public String index(Model model) {
        List<University> universities = universityService.findUniversities();
        List<StudyField> studyFields = studyService.findStudyFields();

        model.addAttribute("student", new RegistrationStudent());
        model.addAttribute("universities", universities);
        model.addAttribute("studyFields", studyFields);

        return "auth/register";
    }

    /**
     * Method perform validation and creating new student, if validation failed, return error message.
     * Send email with confirmation token, set default image(depends on gender)
     * @param student thymeleaf binding object for registration
     * @param bindingResult validation errors
     * @param model model
     * @param redirectAttributes if student register send flash message to login page
     * @return if validation has errors return register page, otherwise redirect to login
     */
    @PostMapping("/register")
    public String saveUser(@Valid @ModelAttribute("student") RegistrationStudent student,
                           BindingResult bindingResult,
                           Model model,
                           RedirectAttributes redirectAttributes) {
        List<University> universities = universityService.findUniversities();
        List<StudyField> studyFields = studyService.findStudyFields();

        if (bindingResult.hasErrors()) {
            model.addAttribute("universities", universities);
            model.addAttribute("studyFields", studyFields);
            return "auth/register";
        }

        Student studentExist = studentService.findByUsernameOrEmail(student.getUsername(), student.getEmail());
        if (studentExist != null) {
            model.addAttribute("student", new RegistrationStudent());
            model.addAttribute("universities", universities);
            model.addAttribute("studyFields", studyFields);
            model.addAttribute("registrationError", "username or email already exists.");
            return "auth/register";
        }

        String profileDirectory = "/images/profile_pictures/";
        student.setProfilePic("Male".equals(student.getGender()) ?
                profileDirectory + "male.png" : profileDirectory + "female.png");

        registrationService.registerStudent(student);

        redirectAttributes.addFlashAttribute("confirmTokenMsg", "You are register, but you must" +
                " confirm your mail( " + student.getEmail() + " ) to login");
        return "redirect:/login";
    }

    /**
     * Confirm token and enabled student
     * @param token registration token
     * @return redirect to login page from email
     */
    @GetMapping("/register/confirm")
    public String confirmMail(@RequestParam("token") String token) {
        Optional<ConfirmationToken> optionalConfirmationToken =
                confirmationTokenService.findConfirmationTokenByToken(token);
        optionalConfirmationToken.ifPresent(registrationService::confirmRegistration);
        return "redirect:/login";
    }
}