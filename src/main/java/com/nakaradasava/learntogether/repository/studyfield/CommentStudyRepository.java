package com.nakaradasava.learntogether.repository.studyfield;

import com.nakaradasava.learntogether.entity.studyfield.CommentStudy;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface CommentStudyRepository extends JpaRepository<CommentStudy, Integer> {
    List<CommentStudy> findCommentStudyByQuestionStudyId(int questionId);
    int countCommentStudiesByQuestionStudyId(int questionId);
}
