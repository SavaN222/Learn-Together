package com.nakaradasava.learntogether.controller.studyfield;

import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.entity.studyfield.QuestionLike;
import com.nakaradasava.learntogether.entity.studyfield.QuestionStudy;
import com.nakaradasava.learntogether.service.studyfield.QuestionLikeService;
import com.nakaradasava.learntogether.service.studyfield.QuestionStudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class QuestionLikeController {

    private QuestionLikeService questionLikeService;
    private QuestionStudyService questionStudyService;

    @Autowired
    public QuestionLikeController(QuestionLikeService questionLikeService, QuestionStudyService questionStudyService) {
        this.questionLikeService = questionLikeService;
        this.questionStudyService = questionStudyService;
    }

    @PostMapping("/like")
    public String like(@ModelAttribute("like") QuestionLike like,
                     @AuthenticationPrincipal Student student,
                     @RequestParam(name = "questionId") int questionId) {

        QuestionStudy questionStudy = questionStudyService.findById(questionId);

        like.setQuestionStudy(questionStudy);
        like.setStudent(student);

        Optional<QuestionLike> likeExist = questionLikeService.findByQuestionStudyAndStudent(questionStudy, student);

        if (likeExist.isPresent()) {
            questionLikeService.dislike(likeExist.get().getId());
        } else {
            questionLikeService.like(like);
        }

        return "redirect:/questions/" + questionId;
    }


}
