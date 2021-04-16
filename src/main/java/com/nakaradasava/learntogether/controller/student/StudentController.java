package com.nakaradasava.learntogether.controller.student;

import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class StudentController {
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/profile/{profileId}")
    public String myProfile(@PathVariable int profileId, Model model) {

        Optional<Student> student = studentService.findStudentById(profileId);

        if (student.isEmpty()) {
            return "redirect:/";
        }

        model.addAttribute("student", student.get());

        return "student/student-profile";
    }
}
