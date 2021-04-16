package com.nakaradasava.learntogether.controller.student;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class StudentController {

    @GetMapping("/profile/{studentId}")
    public String myProfile(@PathVariable int studentId) {

        return "student/student-profile";
    }
}
