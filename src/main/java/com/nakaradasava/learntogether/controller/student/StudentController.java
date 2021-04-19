package com.nakaradasava.learntogether.controller.student;

import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.entity.studyfield.QuestionStudy;
import com.nakaradasava.learntogether.entity.studyfield.StudyField;
import com.nakaradasava.learntogether.entity.university.University;
import com.nakaradasava.learntogether.entity.university.UniversityCity;
import com.nakaradasava.learntogether.service.student.StudentService;
import com.nakaradasava.learntogether.service.studyfield.QuestionStudyService;
import com.nakaradasava.learntogether.service.studyfield.StudyFieldService;
import com.nakaradasava.learntogether.service.university.UniversityCityService;
import com.nakaradasava.learntogether.service.university.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
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

    @Autowired
    public StudentController(StudentService studentService, QuestionStudyService questionStudyService, UniversityCityService universityCityService, StudyFieldService studyFieldService, UniversityService universityService) {
        this.studentService = studentService;
        this.questionStudyService = questionStudyService;
        this.universityCityService = universityCityService;
        this.studyFieldService = studyFieldService;
        this.universityService = universityService;
    }

    @GetMapping("/profile/{profileId}")
    public String showProfile(@PathVariable int profileId, Model model) {

        Optional<Student> student = studentService.findStudentById(profileId);
        List<University> universities = universityService.findUniversities();
        List<StudyField> studyFields = studyFieldService.findStudyFields();
        List<UniversityCity> cities = universityCityService.findCities();

        if (student.isEmpty()) {
            return "redirect:/";
        }
        
        List<QuestionStudy> studentQuestions = questionStudyService.findQuestionsByStudent(student.get());

        model.addAttribute("student", student.get());
        model.addAttribute("questions", studentQuestions);
        model.addAttribute("universities", universities);
        model.addAttribute("studyFields", studyFields);
        model.addAttribute("cities", cities);

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

        studentService.updateStudent(student, studentInfo, profileImage);

        redirectAttributes.addFlashAttribute("updateProfile", "Successfully profile update, please login again!");
        return "redirect:/login?logout";
    }
}
