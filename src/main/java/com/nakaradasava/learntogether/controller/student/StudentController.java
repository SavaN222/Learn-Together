package com.nakaradasava.learntogether.controller.student;

import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.entity.studyfield.QuestionStudy;
import com.nakaradasava.learntogether.service.student.StudentService;
import com.nakaradasava.learntogether.service.studyfield.QuestionStudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

import java.util.Optional;

@Controller
public class StudentController {
    private StudentService studentService;
    private QuestionStudyService questionStudyService;

    @Autowired
    public StudentController(StudentService studentService, QuestionStudyService questionStudyService) {
        this.studentService = studentService;
        this.questionStudyService = questionStudyService;
    }

    @GetMapping("/profile/{profileId}")
    public String showProfile(@PathVariable int profileId, Model model) {

        Optional<Student> student = studentService.findStudentById(profileId);
        
        if (student.isEmpty()) {
            return "redirect:/";
        }
        
        List<QuestionStudy> studentQuestions = questionStudyService.findQuestionsByStudent(student.get());

        model.addAttribute("student", student.get());
        model.addAttribute("questions", studentQuestions);

        return "student/student-profile";
    }
}
