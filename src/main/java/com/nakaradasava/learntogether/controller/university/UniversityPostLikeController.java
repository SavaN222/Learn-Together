package com.nakaradasava.learntogether.controller.university;

import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.entity.university.UniversityPostLike;
import com.nakaradasava.learntogether.entity.university.UniversityPost;
import com.nakaradasava.learntogether.service.university.UniversityPostLikeService;
import com.nakaradasava.learntogether.service.university.UniversityPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class UniversityPostLikeController {

    private UniversityPostLikeService universityPostLikeService;
    private UniversityPostService universityPostService;

    @Autowired
    public UniversityPostLikeController(UniversityPostLikeService universityPostLikeService, UniversityPostService universityPostService) {
        this.universityPostLikeService = universityPostLikeService;
        this.universityPostService = universityPostService;
    }

    @PostMapping("/university/like")
    public String like(@ModelAttribute("like") UniversityPostLike like,
                     @AuthenticationPrincipal Student student,
                     @RequestParam(name = "postId") int postId) {

        UniversityPost universityPost = universityPostService.findPostById(postId);

        like.setUniversityPost(universityPost);
        like.setStudent(student);

        Optional<UniversityPostLike> likeExist =  universityPostLikeService.findByPostAndStudent(universityPost, student);

        if (likeExist.isPresent()) {
            universityPostLikeService.dislike(likeExist.get().getId());
        } else {
            universityPostLikeService.like(like);
        }

        return "redirect:/university/" + universityPost.getUniversity().getId();
    }
}