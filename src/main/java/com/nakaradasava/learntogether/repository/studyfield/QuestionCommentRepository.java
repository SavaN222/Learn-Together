package com.nakaradasava.learntogether.repository.studyfield;

import com.nakaradasava.learntogether.entity.studyfield.QuestionComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionCommentRepository extends JpaRepository<QuestionComment, Integer> {

    @Query("SELECT comments FROM QuestionComment comments LEFT JOIN QuestionStudy qs ON comments.questionStudy.id = qs.id" +
            " WHERE comments.status = 'UNSEEN' AND comments.student.id <> ?1 AND qs.student.id = ?1")
    List<QuestionComment> findCommentsNotifications(int studentId);
}