package com.nakaradasava.learntogether.service.studyfield;

import com.nakaradasava.learntogether.entity.studyfield.CommentStudy;
import com.nakaradasava.learntogether.repository.studyfield.CommentStudyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentStudyService {

    private CommentStudyRepository commentStudyRepository;

    @Autowired
    public CommentStudyService(CommentStudyRepository commentStudyRepository) {
        this.commentStudyRepository = commentStudyRepository;
    }

    public List<CommentStudy> findCommentsByQuestionId(int questionId) {
        return commentStudyRepository.findCommentStudyByQuestionStudyId(questionId);
    }

    public void saveComment(CommentStudy commentStudy) {
        commentStudyRepository.save(commentStudy);
    }
}
