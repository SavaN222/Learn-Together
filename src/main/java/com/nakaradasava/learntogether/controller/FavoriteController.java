package com.nakaradasava.learntogether.controller;

import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.entity.studyfield.QuestionLike;
import com.nakaradasava.learntogether.entity.studyfield.QuestionStudy;
import com.nakaradasava.learntogether.entity.university.UniversityPost;
import com.nakaradasava.learntogether.entity.university.UniversityPostLike;
import com.nakaradasava.learntogether.service.studyfield.QuestionLikeService;
import com.nakaradasava.learntogether.service.university.UniversityPostLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
/**
 * List favorite(liked) questions and university posts
 */
public class FavoriteController {
    private UniversityPostLikeService universityPostLikeService;
    private QuestionLikeService questionLikeService;

    @Autowired
    public FavoriteController(UniversityPostLikeService universityPostLikeService,
                              QuestionLikeService questionLikeService) {
        this.universityPostLikeService = universityPostLikeService;
        this.questionLikeService = questionLikeService;
    }

    @GetMapping("/favorites")
    public String showFavorites(@AuthenticationPrincipal Student student, Model model) {

        List<UniversityPostLike>  universityPostLikes = universityPostLikeService.findStudentFavoritePosts(student);
        List<QuestionLike> questionLikes = questionLikeService.findStudentFavoriteQuestions(student);

        List<UniversityPost> universityPosts = universityPostLikes
                .stream()
                .map(UniversityPostLike::getUniversityPost)
                .collect(Collectors.toList());

        List<QuestionStudy> questions = questionLikes
                .stream()
                .map(QuestionLike::getQuestionStudy)
                .collect(Collectors.toList());

        model.addAttribute("universityPosts", universityPosts);
        model.addAttribute("questions", questions);

        return "favorites";
    }
}
