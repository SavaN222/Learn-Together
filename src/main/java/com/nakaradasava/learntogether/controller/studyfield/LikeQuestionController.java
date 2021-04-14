package com.nakaradasava.learntogether.controller.studyfield;

import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.entity.studyfield.LikeStudy;
import com.nakaradasava.learntogether.entity.studyfield.QuestionStudy;
import com.nakaradasava.learntogether.service.studyfield.LikeStudyService;
import com.nakaradasava.learntogether.service.studyfield.QuestionStudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class LikeQuestionController {

    private LikeStudyService likeStudyService;
    private QuestionStudyService questionStudyService;

    @Autowired
    public LikeQuestionController(LikeStudyService likeStudyService, QuestionStudyService questionStudyService) {
        this.likeStudyService = likeStudyService;
        this.questionStudyService = questionStudyService;
    }

    @PostMapping("/like")
    public String like(@ModelAttribute("like") LikeStudy like,
                     @AuthenticationPrincipal Student student,
                     @RequestParam(name = "questionId") int questionId) {

        QuestionStudy questionStudy = questionStudyService.findById(questionId);

        like.setQuestionStudy(questionStudy);
        like.setStudent(student);

        Optional<LikeStudy> likeExist = likeStudyService.findByQuestionStudyAndStudent(questionStudy, student);

        if (likeExist.isPresent()) {
            likeStudyService.dislike(likeExist.get().getId());
        } else {
            likeStudyService.like(like);
        }

        return "redirect:/question/" + questionId;
    }


}
