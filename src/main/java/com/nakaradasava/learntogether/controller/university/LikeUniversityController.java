package com.nakaradasava.learntogether.controller.university;

import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.entity.studyfield.LikeStudy;
import com.nakaradasava.learntogether.entity.studyfield.QuestionStudy;
import com.nakaradasava.learntogether.entity.university.LikePost;
import com.nakaradasava.learntogether.entity.university.Post;
import com.nakaradasava.learntogether.service.studyfield.LikeStudyService;
import com.nakaradasava.learntogether.service.studyfield.QuestionStudyService;
import com.nakaradasava.learntogether.service.university.LikeService;
import com.nakaradasava.learntogether.service.university.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class LikeUniversityController {

    private LikeService likeService;
    private PostService postService;

    @Autowired
    public LikeUniversityController(LikeService likeService, PostService postService) {
        this.likeService = likeService;
        this.postService = postService;
    }

    @PostMapping("/university/like")
    public String like(@ModelAttribute("like") LikePost like,
                     @AuthenticationPrincipal Student student,
                     @RequestParam(name = "postId") int postId) {

        Post post = postService.findPostById(postId);

        like.setPost(post);
        like.setStudent(student);

        Optional<LikePost> likeExist =  likeService.findByPostAndStudent(post, student);

        if (likeExist.isPresent()) {
            likeService.dislike(likeExist.get().getId());
        } else {
            likeService.like(like);
        }

        return "redirect:/university/" + post.getUniversity().getId();
    }


}
