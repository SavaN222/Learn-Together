package com.nakaradasava.learntogether.controller.auth;

import com.nakaradasava.learntogether.service.FriendService;
import com.nakaradasava.learntogether.service.student.StudentPostCommentService;
import com.nakaradasava.learntogether.service.studyfield.QuestionCommentService;
import com.nakaradasava.learntogether.service.university.UniversityPostCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String index() {
        return "auth/login";
    }
}