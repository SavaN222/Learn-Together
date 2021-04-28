package com.nakaradasava.learntogether.controller;

import com.nakaradasava.learntogether.entity.api.Quote;
import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.entity.student.StudentPost;
import com.nakaradasava.learntogether.entity.student.StudentPostLike;
import com.nakaradasava.learntogether.service.FriendService;
import com.nakaradasava.learntogether.service.student.StudentPostCommentService;
import com.nakaradasava.learntogether.service.studyfield.QuestionCommentService;
import com.nakaradasava.learntogether.service.university.UniversityPostCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private FriendService friendService;
    private StudentPostCommentService studentPostCommentService;
    private QuestionCommentService questionCommentService;
    private UniversityPostCommentService universityPostCommentService;

    @Autowired
    public HomeController(FriendService friendService, StudentPostCommentService studentPostCommentService,
                          QuestionCommentService questionCommentService,
                          UniversityPostCommentService universityPostCommentService) {
        this.friendService = friendService;
        this.studentPostCommentService = studentPostCommentService;
        this.questionCommentService = questionCommentService;
        this.universityPostCommentService = universityPostCommentService;
    }

    /**
     * Home page with quote api
     * @param model
     * @return home page
     */
    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal Student student, HttpSession session) {
        RestTemplate restTemplate = new RestTemplate();
        Quote quote;

        try {
            quote = restTemplate.getForObject("https://api.quotable.io/random", Quote.class);
        } catch (Exception exception) {
            quote = new Quote("Dalai Lama", "Share your knowledge. It is a way to achieve immortality.");
        }

        model.addAttribute("quote", quote);
        model.addAttribute("studentPostObj", new StudentPost());
        model.addAttribute("friendsPosts", friendService.friendsPosts(friendService.getFriends(student.getId())));
        model.addAttribute("like", new StudentPostLike());

        session.setAttribute("requesters", friendService.getFriendRequesters(student.getId()));

        session.setAttribute("commentNotificationStudentPost",
                studentPostCommentService.getNotificationsForStudentPostComment(student.getId()));

        session.setAttribute("commentNotificationQuestion",
                questionCommentService.getNotificationsForQuestionPostComment(student.getId()));

        session.setAttribute("commentNotificationUniversity",
                universityPostCommentService.getNotificationsForUniversityPostComment(student.getId()));

        return "index";
    }
}