package com.nakaradasava.learntogether.controller.student;

import com.nakaradasava.learntogether.entity.Friend;
import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.entity.studyfield.QuestionStudy;
import com.nakaradasava.learntogether.entity.studyfield.StudyField;
import com.nakaradasava.learntogether.entity.university.University;
import com.nakaradasava.learntogether.entity.university.UniversityCity;
import com.nakaradasava.learntogether.service.FriendService;
import com.nakaradasava.learntogether.service.student.StudentService;
import com.nakaradasava.learntogether.service.studyfield.QuestionStudyService;
import com.nakaradasava.learntogether.service.studyfield.StudyFieldService;
import com.nakaradasava.learntogether.service.university.UniversityCityService;
import com.nakaradasava.learntogether.service.university.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

import java.util.Optional;

@Controller
public class StudentController {
    private StudentService studentService;
    private QuestionStudyService questionStudyService;
    private UniversityCityService universityCityService;
    private StudyFieldService studyFieldService;
    private UniversityService universityService;
    private FriendService friendService;

    @Autowired
    public StudentController(StudentService studentService,
                             QuestionStudyService questionStudyService,
                             UniversityCityService universityCityService,
                             StudyFieldService studyFieldService,
                             UniversityService universityService,
                             FriendService friendService) {
        this.studentService = studentService;
        this.questionStudyService = questionStudyService;
        this.universityCityService = universityCityService;
        this.studyFieldService = studyFieldService;
        this.universityService = universityService;
        this.friendService = friendService;
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

    @GetMapping("/profile/{profileId}")
    public String showProfile(@PathVariable int profileId,
                              @AuthenticationPrincipal Student loggedStudent,
                              Model model) {

        Optional<Student> student = studentService.findStudentById(profileId);

        if (student.isEmpty()) {
            return "redirect:/";

        }
        int lowerId, higherId;

        if (loggedStudent.getId() > student.get().getId()) {
            lowerId = student.get().getId();
            higherId = loggedStudent.getId();
        } else {
            lowerId = loggedStudent.getId();
            higherId = student.get().getId();
        }

        Student lowerStudent = studentService.findStudentById(lowerId).get();
        Student higherStudent = studentService.findStudentById(higherId).get();
        Optional<Friend> requestExist = friendService.findByStudentLowerAndStudentHigher(lowerStudent, higherStudent);

        if (requestExist.isPresent()) {
            model.addAttribute("friendshipStatus", requestExist.get().getStatus());
        }

        List<University> universities = universityService.findUniversities();
        List<StudyField> studyFields = studyFieldService.findStudyFields();
        List<UniversityCity> cities = universityCityService.findCities();
        List<QuestionStudy> studentQuestions = questionStudyService.findQuestionsByStudent(student.get());

        model.addAttribute("student", student.get());
        model.addAttribute("questions", studentQuestions);
        model.addAttribute("universities", universities);
        model.addAttribute("studyFields", studyFields);
        model.addAttribute("cities", cities);
        model.addAttribute("friend", new Friend());

        return "student/student-profile";
    }

    @PostMapping("/profile/{profileId}")
    public String updateProfile(@ModelAttribute(name = "student") Student student,
                                @PathVariable int profileId,
                                RedirectAttributes redirectAttributes,
                                @RequestParam(name = "profileImage") MultipartFile profileImage) throws IOException {

        Student studentInfo = studentService.findStudentById(profileId).get();

        if (!student.getUsername().equals(studentInfo.getUsername())) {
            Optional<Student> studentExist = studentService.findByUsername(student.getUsername());

            if (studentExist.isPresent()) {
                redirectAttributes.addFlashAttribute("usernameErr", "Student with this username already exist");
                return "redirect:/profile/" + profileId;
            }
        }

        if (student.getUsername().length() < 3 || student.getUsername().length() > 45) {
            redirectAttributes.addFlashAttribute("usernameErr", "Username must be at least 3 characters and not longer than 45");
            return "redirect:/profile/" + profileId;
        }

        if (student.getDescription().length() > 128) {
            redirectAttributes.addFlashAttribute("descriptionErr", "Description can't be longer than 128 chars...");
            return "redirect:/profile/" + profileId;
        }

        studentService.updateStudent(student, studentInfo, profileImage);

        redirectAttributes.addFlashAttribute("updateProfile", "Successfully profile update, please login again!");
        return "redirect:/login?logout";
    }
}
