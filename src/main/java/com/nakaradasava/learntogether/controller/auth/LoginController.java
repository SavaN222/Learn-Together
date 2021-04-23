package com.nakaradasava.learntogether.controller.auth;

import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.service.FriendService;
import com.nakaradasava.learntogether.service.student.StudentPostCommentService;
import com.nakaradasava.learntogether.service.studyfield.QuestionCommentService;
import com.nakaradasava.learntogether.service.university.UniversityPostCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    private FriendService friendService;
    private StudentPostCommentService studentPostCommentService;
    private QuestionCommentService questionCommentService;
    private UniversityPostCommentService universityPostCommentService;

    @Autowired
    public LoginController(FriendService friendService,
                           StudentPostCommentService studentPostCommentService,
                           QuestionCommentService questionCommentService,
                           UniversityPostCommentService universityPostCommentService) {
        this.friendService = friendService;
        this.studentPostCommentService = studentPostCommentService;
        this.questionCommentService = questionCommentService;
        this.universityPostCommentService = universityPostCommentService;
    }

    @GetMapping("/login")
    public String index() {
        return "auth/login";
    }

    /**
     * Set notification session
     * @return redirect to homepage
     */
    @GetMapping("/processLogin")
    private String processLogin(HttpSession session, @AuthenticationPrincipal Student student) {

        session.setAttribute("requesters", friendService.getFriendRequesters(student.getId()));

        session.setAttribute("commentNotificationStudentPost",
                studentPostCommentService.getNotificationsForStudentPostComment(student.getId()));

        session.setAttribute("commentNotificationQuestion",
                questionCommentService.getNotificationsForQuestionPostComment(student));

        session.setAttribute("commentNotificationUniversity",
                universityPostCommentService.getNotificationsForUniversityPostComment(student));

        return "redirect:/";
    }
}