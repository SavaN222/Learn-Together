package com.nakaradasava.learntogether.controller.student;

import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.entity.student.StudentPost;
import com.nakaradasava.learntogether.entity.student.StudentPostLike;
import com.nakaradasava.learntogether.service.student.StudentPostLikeService;
import com.nakaradasava.learntogether.service.student.StudentPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class StudentPostLikeController {

   private StudentPostLikeService studentPostLikeService;
   private StudentPostService studentPostService;

   @Autowired
    public StudentPostLikeController(StudentPostLikeService studentPostLikeService, StudentPostService studentPostService) {
        this.studentPostLikeService = studentPostLikeService;
        this.studentPostService = studentPostService;
    }

    @PostMapping("/student/post/like")
    public String like(@ModelAttribute("like") StudentPostLike like,
                     @AuthenticationPrincipal Student student,
                     @RequestParam(name = "postId") int postId) {

        StudentPost studentPost = studentPostService.findPostById(postId);

        like.setStudentPost(studentPost);
        like.setStudent(student);

        Optional<StudentPostLike> likeExist = studentPostLikeService.findByPostAndStudent(studentPost, student);

        if (likeExist.isPresent()) {
            studentPostLikeService.dislike(likeExist.get().getId());
        } else {
            studentPostLikeService.like(like);
        }

        return "redirect:/#post" + postId;
    }
}